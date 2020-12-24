package DTO;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    int Id;
    private String name;
    Integer masterID;
    List<Employee> slaves;
    List<Task> tasks;

    public Employee() {
        masterID = null;
        slaves = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addSlave(Employee employee) {
         if (slaves.stream().noneMatch(employee1 -> employee1.getId() == employee.getId())) {
             slaves.add(employee);
             employee.setMasterID(this.getId());
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

    public Integer getMasterID() {
        return masterID;
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

    public void setMasterID(Integer masterID) {
        this.masterID = masterID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlaves(List<Employee> slaves) {
        this.slaves = slaves;
    }
}
