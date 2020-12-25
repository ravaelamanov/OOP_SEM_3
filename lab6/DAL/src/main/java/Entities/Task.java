package Entities;

import Infrastructure.IEntity;
import org.hibernate.annotations.GenericGenerator;
import util.TaskState;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private Employee employee;

    @ManyToOne
    private DailyReport report;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    @Temporal(value = TemporalType.DATE)
    private Date creation_date;

    public Task() {
        name = "";
        description = "";
        employee = null;
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

    public Employee getEmployee() {
        return employee;
    }

    public String getDescription() {
        return description;
    }

    public DailyReport getReport() {
        return report;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }


    public void setReport(DailyReport report) {
        this.report = report;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
