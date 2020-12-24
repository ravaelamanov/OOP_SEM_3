package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyReport {
    private int Id;
    private List<Task> accomplishedTasks;
    private Employee employee;
    private Date creationDate;

    public DailyReport() {
        accomplishedTasks = new ArrayList<>();
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getId() {
        return Id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Task> getAccomplishedTasks() {
        return accomplishedTasks;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAccomplishedTasks(List<Task> accomplishedTasks) {
        this.accomplishedTasks = accomplishedTasks;
    }
}
