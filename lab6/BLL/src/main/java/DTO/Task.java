package DTO;

import TaskChanges.Comment;
import TaskChanges.EmployeeChange;
import TaskChanges.StateChange;
import util.TaskState;

import java.util.Date;

public class Task {
    private int Id;
    private String name;
    private String description;
    private Employee employee;
    private DailyReport dailyReport;
    private TaskState state;
    private Date creationDate;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TaskChange addComment(String comment) {
        TaskChange taskChange = new Comment(comment);
        addChange(taskChange);
        return taskChange;
    }

    public TaskChange changeState(TaskState state) {
        setState(state);
        TaskChange taskChange = new StateChange(state);
        addChange(taskChange);
        return taskChange;
    }

    public TaskChange changeEmployee(Employee employee) {
        this.employee = employee;
        employee.addTask(this);
        TaskChange taskChange = new EmployeeChange(employee);
        addChange(taskChange);
        return taskChange;
    }

    private void addChange(TaskChange change) {
        change.setCreationDate(new Date());
        change.setTask(this);
    }

    public int getId() {
        return Id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public TaskState getState() {
        return state;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReport dailyReport) {
        this.dailyReport = dailyReport;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Id + " " + name + " " + description + " " + employee.getName() + " " + state.toString() + " " + creationDate.toString();
    }
}
