package com.company;

import DTO.DailyReport;
import DTO.Employee;
import util.TaskState;

import java.util.Date;

public class Task {
    private Integer Id;
    private String name;
    private String description;
    private Employee employee;
    private DailyReport dailyReport;
    private TaskState state;
    private Date creationDate;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DailyReport getDailyReport() {
        return dailyReport;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return Id;
    }

    public TaskState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
