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
    private Integer ID;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Employee master;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Employee> slaves;

    @OneToMany(mappedBy = "employee", targetEntity = Entities.DailyReport.class, cascade = CascadeType.ALL)
    private List<DailyReport> dailyReports;

    @OneToMany(mappedBy = "employee", targetEntity = Entities.Task.class, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Employee() {
        name = "";
        master = null;
        slaves = new ArrayList<>();
        dailyReports = new ArrayList<>();
        tasks = new ArrayList<>();
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

    public List<DailyReport> getDailyReports() {
        return dailyReports;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setDailyReports(List<DailyReport> dailyReports) {
        this.dailyReports = dailyReports;
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

    public void addSlave(Employee employee) {
        slaves.add(employee);
    }
}
