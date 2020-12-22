import DTO.Employee;
import DTO.Task;
import Repositories.EmployeeRepository;
import Repositories.TaskRepository;
import util.EntityConverter;
import util.TaskState;

import javax.swing.text.html.parser.Entity;
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
        Collection<Entities.Task> dalTasks = null;// = taskRepository.find(task -> task.getChanges().stream().anyMatch(change -> change.));
        return dalTasks.stream().map(EntityConverter::convert).collect(Collectors.toList());
    }

    public void createTask(Task task) {
        Entities.Task dalTask = EntityConverter.convert(task);
        taskRepository.add(dalTask);
        task.setId(dalTask.getID());
    }

    public void editTaskState(Task task, TaskState state) {
        task.changeState(state);
        taskRepository.update(EntityConverter.convert(task));
    }

    public void addComment(Task task, String comment) {
        task.addComment(comment);
        taskRepository.update(EntityConverter.convert(task));
    }

    public void changeEmployee(Task task, Employee employee) {
        Employee oldEmployee = EntityConverter.convert(taskRepository.get(task.getId()).getEmployee());
        task.changeEmployee(employee);

        taskRepository.update(EntityConverter.convert(task));
        employeeRepository.update(EntityConverter.convert(oldEmployee));
        employeeRepository.update(EntityConverter.convert(employee));
    }

    public void createEmployee(Employee employee) {
        Entities.Employee dalEmployee = EntityConverter.convert(employee);
        employeeRepository.add(dalEmployee);
        employee.setId(dalEmployee.getID());
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(EntityConverter.convert(employee));
    }
}
