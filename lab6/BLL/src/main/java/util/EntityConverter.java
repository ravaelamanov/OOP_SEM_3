package util;

import DTO.Employee;
import DTO.Task;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter {
    public static Task convert(Entities.Task task) {
        Task bllTask = new Task();

        bllTask.setId(task.getID());
        bllTask.setName(task.getName());
        bllTask.setDescription(task.getDescription());
        bllTask.setEmployee(convert(task.getEmployee()));
        bllTask.setState(task.getState());

        return bllTask;
    }

    public static Entities.Task convert(Task task) {
        Entities.Task dalTask = new Entities.Task();

        dalTask.setID(task.getId());
        dalTask.setName(task.getName());
        dalTask.setDescription(task.getDescription());
        dalTask.setEmployee(convert(task.getEmployee()));
        dalTask.setState(task.getState());

        return dalTask;
    }

    public static Employee convert(Entities.Employee employee) {
        if (employee == null) return null;

        Employee bllEmployee = new Employee();

        bllEmployee.setId(employee.getID());
        bllEmployee.setName(employee.getName());
        bllEmployee.setMaster(convert(employee.getMaster()));
        bllEmployee.setSlaves(employee.getSlaves().stream().map(EntityConverter::convert).collect(Collectors.toList()));
        bllEmployee.setTasks(employee.getTasks().stream().map(EntityConverter::convert).collect(Collectors.toList()));

        return bllEmployee;
    }

    public static Entities.Employee convert(Employee employee) {
        if (employee == null) return null;

        Entities.Employee dalEmployee = new Entities.Employee();

        dalEmployee.setID(employee.getId());
        dalEmployee.setName(employee.getName());
        dalEmployee.setMaster(convert(employee.getMaster()));
        dalEmployee.setSlaves(employee.getSlaves().stream().map(EntityConverter::convert).collect(Collectors.toList()));
        dalEmployee.setTasks(employee.getTasks().stream().map(EntityConverter::convert).collect(Collectors.toList()));

        return dalEmployee;
    }
}
