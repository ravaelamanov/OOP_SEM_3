package util;

import DTO.Employee;
import DTO.Task;
import DTO.TaskChange;
import TaskChanges.Comment;
import TaskChanges.EmployeeChange;
import TaskChanges.StateChange;

import java.util.stream.Collectors;

public class EntityConverter {
    public static Task convert(Entities.Task task) {
        Task bllTask = new Task();

        bllTask.setId(task.getID());
        bllTask.setName(task.getName());
        bllTask.setDescription(task.getDescription());
        bllTask.setEmployee(convert(task.getEmployee()));
        bllTask.setState(task.getState());
        bllTask.setCreationDate(task.getCreation_date());
        bllTask.setChanges(task.getChanges().stream().map(EntityConverter::convert).collect(Collectors.toList()));
        //bllTask.setReport(task.getReport());

        return bllTask;
    }

    public static Entities.Task convert(Task task) {
        Entities.Task dalTask = new Entities.Task();

        dalTask.setID(task.getId());
        dalTask.setName(task.getName());
        dalTask.setDescription(task.getDescription());
        dalTask.setEmployee(convert(task.getEmployee()));
        dalTask.setState(task.getState());
        dalTask.setCreation_date(task.getCreationDate());
        //dalTask.setReport(task.getReport());

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

    public static TaskChange convert(Entities.TaskChange dalChange) {
        TaskChange bllChange = null;
        if (dalChange instanceof Entities.CommentChange) {
            bllChange = convert((Entities.CommentChange) dalChange);
        }
        else if (dalChange instanceof Entities.StateChange) {
            bllChange = convert((Entities.StateChange) dalChange);
        }
        else if (dalChange instanceof Entities.EmployeeChange) {
            bllChange = convert((Entities.EmployeeChange) dalChange);
        }
        bllChange.setId(dalChange.getID());
        bllChange.setCreationDate(dalChange.getCreationDate());
        bllChange.setTask(convert(dalChange.getTask()));
        return bllChange;
    }

    private static Comment convert(Entities.CommentChange comment) {
        Comment bllComment = new Comment();
        bllComment.setComment(comment.getComment());
        return bllComment;
    }

    private static StateChange convert(Entities.StateChange stateChange) {
        StateChange bllStateChange = new StateChange();
        bllStateChange.setState(stateChange.getState());
        return bllStateChange;
    }

    private static EmployeeChange convert(Entities.EmployeeChange employeeChange) {
        EmployeeChange bllEmployeeChange = new EmployeeChange();
        bllEmployeeChange.setEmployee(convert(employeeChange.getEmployee()));
        return bllEmployeeChange;
    }
}
