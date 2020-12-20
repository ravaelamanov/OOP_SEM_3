import DTO.Employee;
import DTO.Task;
import Repositories.EmployeeRepository;
import Repositories.TaskRepository;
import util.EntityConverter;
import util.TaskState;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManagementSystem {
    TaskRepository taskRepository;
    EmployeeRepository employeeRepository;

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
        Collection<Entities.Task> dalTasks = taskRepository.find(task -> task.getChanges().stream().anyMatch(change -> change.));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public void createTask(Task task) {
        taskRepository.add(EntityConverter.convert(task));
    }

    public void editTaskState(Task task, TaskState state) {
        task.setState(state);
        taskRepository.update(EntityConverter.convert(task));
    }

    public void addComment(Task task, String comment) {
        //add comment to task
        taskRepository.update(EntityConverter.convert(task));
    }

    public void changeEmployee(Task task, Employee employee) {
        Employee oldEmployee = EntityConverter.convert(taskRepository.get(task.getId()).getEmployee());
        oldEmployee.deleteTask(task.getId());
        employee.addTask(task);
        task.setEmployee(employee);

        taskRepository.update(EntityConverter.convert(task));
        employeeRepository.update(EntityConverter.convert(oldEmployee));
        employeeRepository.update(EntityConverter.convert(employee));
    }
}
