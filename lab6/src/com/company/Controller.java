package com.company;

import BLLServicies.EmployeeManagementSystem;
import BLLServicies.TaskManagementSystem;

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
}
