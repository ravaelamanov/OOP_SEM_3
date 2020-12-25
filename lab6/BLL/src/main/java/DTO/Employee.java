package DTO;

public class Employee {
    private Integer Id;
    private String name;
    private Integer masterID;

    public Employee() {
        masterID = null;
    }

    public Employee(String name) {
        this.name = name;
    }

    public void addTask(Task task) {
    }

    public void addSlave(Employee employee) {
        employee.setMasterID(this.getId());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public Integer getMasterID() {
        return masterID;
    }

    public void setMasterID(Integer masterID) {
        this.masterID = masterID;
    }


    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
