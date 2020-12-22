package DTO;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    int Id;
    private String name;
    Employee master;
    List<Employee> slaves;
    List<Task> tasks;

    public Employee() {
        master = null;
        slaves = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addSlave(Employee employee) {
         if (slaves.stream().noneMatch(employee1 -> employee1.getId() == employee.getId())) {
             slaves.add(employee);
             employee.setMaster(employee);
         }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public String getName() {
        return name;
    }

    public List<Employee> getSlaves() {
        return slaves;
    }

    public Employee getMaster() {
        return master;
    }

    public int getId() {
        return Id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setId(int id) {
        Id = id;
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
