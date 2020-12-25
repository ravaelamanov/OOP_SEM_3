package com.company;
import BLLServicies.EmployeeManagementSystem;
import BLLServicies.EntityConverter;
import BLLServicies.ReportManagementSystem;
import BLLServicies.TaskManagementSystem;
import util.AbstractRepositoryFactory;
import util.HibernateRepositoryFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        init();
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] splitCommand = command.split(" ");
            switch (splitCommand[0]) {
                case "addtask" : {
                    if (splitCommand.length < 3) {
                        System.out.println("Not enought arguments to add task");
                    }
                    else {
                        String name = splitCommand[1];
                        String description = splitCommand[2];
                        controller.addTask(new DTO.Task(name, description));
                    }

                    break;
                }
                case "addemp" : {
                    if (splitCommand.length < 2) {
                        System.out.println("Not enought arguments to add employee");
                    }
                    else {
                        String name = splitCommand[1];
                        controller.addEmployee(new DTO.Employee(name));
                    }
                    break;
                }
                case "assign" : {
                    if (splitCommand.length < 3) {
                        System.out.println("Not enought arguments to assign task");
                    }
                    int employeeID = Integer.parseInt(splitCommand[1]);
                    int taskID = Integer.parseInt(splitCommand[2]);
                    controller.assignTask(employeeID, taskID);
                }
                default: System.out.println("Unknown command!");
            }

        }
    }

    public static void init() {
        AbstractRepositoryFactory factory = new HibernateRepositoryFactory();
        TaskManagementSystem taskManagementSystem = new TaskManagementSystem(factory);
        EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem(factory);
        ReportManagementSystem reportManagementSystem = new ReportManagementSystem(factory);
        EntityConverter entityConverter = new EntityConverter(factory);
    }
}
