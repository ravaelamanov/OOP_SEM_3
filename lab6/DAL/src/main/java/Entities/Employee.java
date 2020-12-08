package Entities;

import Infrastructure.IEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name =  "Employees")
public class Employee implements IEntity {

    @Id
    @GeneratedValue
    private int ID;

    @Column
    private String name;

    @Column
    private Employee master;

    @OneToMany(mappedBy = "employee")
    private List<Employee> slaves;

    public Employee() {
        name = "";
        master = null;
        slaves = new ArrayList<>();
    }

    public Employee(String name) {
        this.name = name;
        master = null;
        slaves = new ArrayList<>();
    }

    @Override
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Employee getMaster() {
        return master;
    }

    public List<Employee> getSlaves() {
        return slaves;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMaster(Employee master) {
        this.master = master;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlaves(List<Employee> slaves) {
        this.slaves = slaves;
    }
}
