package DTO;

import TaskChanges.Comment;
import TaskChanges.EmployeeChange;
import TaskChanges.StateChange;
import util.TaskState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private int Id;
    private String name;
    private String description;
    private Employee employee;
    private TaskState state;
    private DailyReport report;
    private Date creationDate;
    private List<TaskChange> changes;

    public Task() {
        changes = new ArrayList<>();
    }

    public void addComment(String comment) {
        TaskChange taskChange = new Comment(comment);
        addChange(taskChange);
    }

    public void changeState(TaskState state) {
        setState(state);
        TaskChange taskChange = new StateChange(state);
        addChange(taskChange);
    }

    public void changeEmployee(Employee employee) {
        this.employee.deleteTask(this.getId());
        employee.addTask(this);
        setEmployee(employee);
        TaskChange taskChange = new EmployeeChange(employee);
        addChange(taskChange);
    }

    private void addChange(TaskChange change) {
        change.setCreationDate(new Date());
        changes.add(change);
    }

    public int getId() {
        return Id;
    }

    public Employee getEmployee() {
        return employee;
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

    public DailyReport getReport() {
        return report;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<TaskChange> getChanges() {
        return changes;
    }

    public void setChanges(List<TaskChange> changes) {
        this.changes = changes;
    }

    public void setReport(DailyReport report) {
        this.report = report;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
