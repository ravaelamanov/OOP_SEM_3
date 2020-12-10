import DTO.Employee;
import DTO.Task;
import Repositories.TaskRepository;
import util.TaskState;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class TaskManagementSystem {
    TaskRepository taskRepository;

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(int id) {
        Entities.Task dalTask = taskRepository.get(id);
        Task task = null;
        //convert
        return task;
    }

    public List<Task> getByCreationDate(final Date date) {
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getCreation_date() == date);
        List<Task> tasks = null; // = convert dalTasks
        return tasks;
    }

    public List<Task> getByLastModifiedDate(Date date) {
        Collection<Entities.Task> dalTasks = taskRepository.getAll();
        //??? where to filter by date
        List<Task> tasks = null; // = convert dalTasks

        return tasks;
    }

    public List<Task> getByEmployee(Employee employee) {
        //convert DTO employee to DAL
        Entities.Employee dalEmployee = null;
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getEmployee().getID() == dalEmployee.getID());
        List<Task> tasks = null; // = convert dalTasks
        return tasks;
    }

    public List<Task> getIfModifiedBy(Employee employee) {
        return null;
    }

    public void createTask(String name, String description) {

    }

    public void editTaskState(Task task, TaskState state) {

    }

    public void addComment(Task task, String comment) {

    }

    public void changeEmployee(Task task, Employee employee) {

    }
}
