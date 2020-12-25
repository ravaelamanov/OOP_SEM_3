package com.company;

import BLLServicies.EmployeeManagementSystem;
import BLLServicies.TaskManagementSystem;

import java.util.Collection;
import java.util.Date;

public class Controller {
    public void addTask(DTO.Task task) {
        TaskManagementSystem.createTask(task);
    }

    public void addEmployee(DTO.Employee employee) {
        EmployeeManagementSystem.createEmployee(employee);
    }

    public void assignTask(int employeeID, int taskID) {
        DTO.Employee employee = EmployeeManagementSystem.get(employeeID);
        DTO.Task task = TaskManagementSystem.get(taskID);
        assignTask(employee, task);
    }

    public void assignTask(DTO.Employee employee, DTO.Task task) {
         TaskManagementSystem.changeEmployee(task, employee);
    }

    public void solveTask(int employeeID, int taskID) throws Exception {
        DTO.Employee employee = EmployeeManagementSystem.get(employeeID);
        DTO.Task task = TaskManagementSystem.get(taskID);
        solveTask(employee, task);
    }

    public void solveTask(DTO.Employee employee, DTO.Task task) throws Exception {
        EmployeeManagementSystem.solveTask(employee, task);
    }

    public void addSlave(int masterID, int slaveID) {
        DTO.Employee master = EmployeeManagementSystem.get(masterID);
        DTO.Employee slave = EmployeeManagementSystem.get(slaveID);
        addSlave(master, slave);
    }

    public void addSlave(DTO.Employee master, DTO.Employee slave) {
        EmployeeManagementSystem.addSlave(master, slave);
    }

    public DTO.Task get(int id) {
        return TaskManagementSystem.get(id);
    }

    public Collection<DTO.Task> getByCreationDate(Date date) {
        return TaskManagementSystem.getByCreationDate(date);
    }

    public Collection<DTO.Task> getByEmployee(int employeeID) {
        DTO.Employee employee = EmployeeManagementSystem.get(employeeID);
        return getByEmployee(employee);
    }

    public Collection<DTO.Task> getByEmployee(DTO.Employee employee) {
        return TaskManagementSystem.getByEmployee(employee);
    }

    public Collection<DTO.Task> getSlavesTasks(int employeeID) {
        DTO.Employee employee = EmployeeManagementSystem.get(employeeID);
        return getSlavesTasks(employee);
    }

    public Collection<DTO.Task> getSlavesTasks(DTO.Employee employee) {
        return TaskManagementSystem.getSlavesTasks(employee);
    }
}
