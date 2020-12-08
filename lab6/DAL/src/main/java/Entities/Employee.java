package Entities;

import Infrastructure.IEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employees")
@Access(value = AccessType.FIELD)
public class Employee implements IEntity {
    @Id
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int ID;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Employee master;

//    @OneToMany(mappedBy = "ID", cascade = CascadeType.ALL, orphanRemoval = true)
    @ElementCollection
    @CollectionTable(name = "Slaves")
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
