package DTO;

import util.TaskState;

import java.util.Date;

public class Task {
    int Id;
    String name;
    String description;
    Employee employee;
    TaskState state;
    DailyReport report;
    Date creationDate;

    public Task() {}

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
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
