package DTO;

import java.util.List;

public class Employee {
    private String name;
    Employee master;
    List<Employee> slaves;

    public void setMaster(Employee master) {
        this.master = master;
    }
}
