package TaskChanges;

import DTO.Employee;
import DTO.TaskChange;

public class EmployeeChange extends TaskChange {
    private Employee employee;

    public EmployeeChange() {}

    public EmployeeChange(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
