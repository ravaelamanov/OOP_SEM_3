package Entities;


import Infrastructure.IEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_changes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TaskChange implements IEntity {

    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int ID;

    @ManyToOne
    private Task task;

    @Column(name = "creation_date")
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;

    public TaskChange() {
        creationDate = null;
        task = null;
    }

    @Override
    public int getID() {
        return ID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
