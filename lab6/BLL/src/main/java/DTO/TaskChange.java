package DTO;

import java.time.LocalDate;
import java.util.Date;

public abstract class TaskChange {
    protected int Id;
    protected Task task;
    protected Date creationDate;



    public Date getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return Id;
    }

    public Task getTask() {
        return task;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
