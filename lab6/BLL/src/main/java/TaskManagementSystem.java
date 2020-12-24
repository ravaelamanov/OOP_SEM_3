import DTO.Employee;
import DTO.Task;
import DTO.TaskChange;
import util.AbstractRepositoryFactory;
import util.TaskState;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManagementSystem extends BLLService {

    public TaskManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    public Task get(int id) {
        Entities.Task dalTask = taskRepository.get(id);
        return EntityConverter.convert(dalTask);
    }

    public List<Task> getByCreationDate(Date date) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getCreation_date() == date);
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public List<Task> getByLastModifiedDate(Date date) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getChanges().stream().anyMatch(change -> change.getCreationDate() == date));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public List<Task> getByEmployee(Employee employee) {
        Entities.Employee dalEmployee = EntityConverter.convert(employee);
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getEmployee().getID() == dalEmployee.getID());
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public List<Task> getIfModifiedBy(Employee employee) {
        Collection<Entities.Task> dalTasks = null;// = taskRepository.find(task -> task.getChanges().stream().anyMatch(change -> change.));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public List<Task> getSlavesTasks(Employee employee) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> employee.getSlaves().stream().anyMatch(slave -> slave.getId() == task.getEmployee().getID()));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public void createTask(Task task) {
        editTaskState(task, TaskState.OPEN);
        task.setCreationDate(new Date());
        Entities.Task dalTask = EntityConverter.convert(task);
        taskRepository.add(dalTask);
        task.setId(dalTask.getID());
    }

    public void editTaskState(Task task, TaskState state) {
        TaskChange taskChange = task.changeState(state);
        addTaskChange(taskChange);
        updateTask(task);
    }

    public void addComment(Task task, String comment) {
        TaskChange taskChange = task.addComment(comment);
        addTaskChange(taskChange);
        updateTask(task);
    }

    public void changeEmployee(Task task, Employee employee) {
        TaskChange taskChange = task.changeEmployee(employee);
        editTaskState(task, TaskState.ACTIVE);
        addTaskChange(taskChange);
        updateTask(task);
    }

    private void addTaskChange(TaskChange taskChange) {
        taskChange.setCreationDate(new Date());
        Entities.TaskChange dalTaskChange = EntityConverter.convert(taskChange);
        taskChangeRepository.add(dalTaskChange);
        taskChange.setId(dalTaskChange.getID());
    }

    private void updateTask(Task task) {
        taskRepository.update(EntityConverter.convert(task));
    }
}
