package Entities;

import javax.persistence.*;

@Entity
@Access(value = AccessType.FIELD)
public class EmployeeChange extends TaskChange {

    @ManyToOne
    private Employee employee;

    public EmployeeChange() {
        employee = null;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
