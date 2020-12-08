package Entities;

import Infrastructure.IEntity;
import org.hibernate.annotations.GenericGenerator;
import util.TaskState;
import javax.persistence.*;

@Entity
@Table(name = "Tasks")
@Access(value = AccessType.FIELD)
public class Task implements IEntity {
    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int ID;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    private Employee responsibleEmployee;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    public Task() {
        name = "";
        description = "";
        responsibleEmployee = null;
        state = TaskState.OPEN;
    }

    @Override
    public int getID() {
        return ID;
    }

    public TaskState getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public Employee getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public String getDescription() {
        return description;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public void setResponsibleEmployee(Employee responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
        setState(TaskState.ACTIVE);
    }
}
