package Entities;

import Infrastructure.IEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Daily_Reports")
@Access(value = AccessType.FIELD)
public class DailyReport implements IEntity {
    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int ID;

    @ManyToOne
    private Employee employee;

    @Column(name = "creation_date")
    @Temporal(value = TemporalType.DATE)
    Date creationDate;

    public DailyReport() {
    }


    @Override
    public int getID() {
        return ID;
    }


    public Employee getEmployee() {
        return employee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
