package DTO;

import java.util.Date;

public class DailyReport {
    private int Id;
    private Employee employee;
    private Date creationDate;

    public DailyReport() {
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


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
