import DTO.Employee;
import DTO.Task;
import DTO.TaskChange;
import Repositories.TaskChangeRepository;
import Repositories.TaskRepository;
import util.AbstractRepositoryFactory;
import util.TaskState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManagementSystem extends BLLService {
    private static TaskRepository taskRepository;
    private static TaskChangeRepository taskChangeRepository;

    public TaskManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    @Override
    protected void initFactories(AbstractRepositoryFactory factory) {
        taskRepository = factory.createTaskRepository();
        taskChangeRepository = factory.createTaskChangeRepository();
    }

    public static Task get(int id) {
        Entities.Task dalTask = taskRepository.get(id);
        return EntityConverter.convert(dalTask);
    }

    public static List<Task> getByCreationDate(Date date) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getCreationDate() == date);
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public static List<Task> getByLastModifiedDate(Date date) {
        Collection<Entities.TaskChange> changes = taskChangeRepository.find(taskChange -> taskChange.getCreationDate().equals(date));
        Collection<Entities.Task> dalTasks = new ArrayList<>();
        for (Entities.TaskChange change : changes) {
            if (dalTasks.stream().noneMatch(task -> task.getID() == change.getTask().getID())) {
                dalTasks.add(change.getTask());
            }
        }
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public static List<Task> getByEmployee(Employee employee) {
        Entities.Employee dalEmployee = EntityConverter.convert(employee);
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getEmployee().getID() == dalEmployee.getID());
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public static List<Task> getIfModifiedBy(Employee employee) {
        Collection<Entities.Task> dalTasks = null;// = taskRepository.find(task -> task.getChanges().stream().anyMatch(change -> change.));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public static List<Task> getSlavesTasks(Employee employee) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> employee.getSlaves().stream().anyMatch(slave -> slave.getId() == task.getEmployee().getID()));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public static void createTask(Task task) {
        task.setCreationDate(new Date());
        Entities.Task dalTask = EntityConverter.convert(task);
        taskRepository.add(dalTask);
        task.setId(dalTask.getID());
        editTaskState(task, TaskState.OPEN);
    }

    public static void solveTask(Task task) throws Exception {
        if (task.getEmployee() == null) {
            throw new Exception("solve task exception");
        }
        task.setDailyReport(task.getEmployee().getDailyReport());
        editTaskState(task, TaskState.RESOLVED);
    }

    public static void editTaskState(Task task, TaskState state) {
        TaskChange taskChange = task.changeState(state);
        addTaskChange(taskChange);
        updateTask(task);
    }

    public static void addComment(Task task, String comment) {
        TaskChange taskChange = task.addComment(comment);
        addTaskChange(taskChange);
        updateTask(task);
    }

    public static void changeEmployee(Task task, Employee employee) {
        TaskChange taskChange = task.changeEmployee(employee);
        editTaskState(task, TaskState.ACTIVE);
        addTaskChange(taskChange);
        updateTask(task);
    }

    private static void addTaskChange(TaskChange taskChange) {
        taskChange.setCreationDate(new Date());
        Entities.TaskChange dalTaskChange = EntityConverter.convert(taskChange);
        taskChangeRepository.add(dalTaskChange);
        taskChange.setId(dalTaskChange.getID());
    }

    private static void updateTask(Task task) {
        taskRepository.update(EntityConverter.convert(task));
    }
}
