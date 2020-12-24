package util;

import DTO.DailyReport;
import DTO.Employee;
import DTO.Task;
import DTO.TaskChange;
import Repositories.EmployeeRepository;
import Repositories.TaskRepository;
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

    public static Entities.Task convert(Task task, EmployeeRepository employeeRepository) {
        Entities.Task dalTask = new Entities.Task();

        dalTask.setID(task.getId());
        dalTask.setName(task.getName());
        dalTask.setDescription(task.getDescription());
        Entities.Employee employee = null;
        if (task.getEmployee() != null) {
            employee = employeeRepository.get(task.getEmployee().getId());
        }
        dalTask.setEmployee(employee);
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
        if (employee.getMaster() != null) {
            bllEmployee.setMasterID(employee.getMaster().getID());
        }
        bllEmployee.setSlaves(employee.getSlaves().stream().map(EntityConverter::convert).collect(Collectors.toList()));
        bllEmployee.setTasks(employee.getTasks().stream().map(EntityConverter::convert).collect(Collectors.toList()));

        return bllEmployee;
    }

    public static Entities.Employee convert(Employee employee, EmployeeRepository employeeRepository) {
        if (employee == null) return null;


        Entities.Employee dalEmployee = new Entities.Employee();

        dalEmployee.setID(employee.getId());
        dalEmployee.setName(employee.getName());
        Entities.Employee master = null;
        if (employee.getMasterID() != null) {
            master = employeeRepository.get(employee.getMasterID());
        }
        dalEmployee.setMaster(master);
        dalEmployee.setSlaves(employee.getSlaves().stream().map(employee1 -> convert(employee1, employeeRepository)).collect(Collectors.toList()));
        dalEmployee.setTasks(employee.getTasks().stream().map(task -> convert(task, employeeRepository)).collect(Collectors.toList()));

        return dalEmployee;
    }

    public static TaskChange convert(Entities.TaskChange dalChange) {
        TaskChange bllChange = null;
        if (dalChange instanceof Entities.CommentChange) {
            bllChange = convert((Entities.CommentChange) dalChange);
        } else if (dalChange instanceof Entities.StateChange) {
            bllChange = convert((Entities.StateChange) dalChange);
        } else if (dalChange instanceof Entities.EmployeeChange) {
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

    public static Entities.TaskChange convert(TaskChange bllChange, EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        Entities.TaskChange dalChange = null;
        if (bllChange instanceof Comment) {
            dalChange = convert((Comment) bllChange);
        } else if (bllChange instanceof StateChange) {
            dalChange = convert((StateChange) bllChange);
        } else if (bllChange instanceof EmployeeChange) {
            dalChange = convert((EmployeeChange) bllChange, employeeRepository);
        }
        dalChange.setID(bllChange.getId());
        dalChange.setCreationDate(bllChange.getCreationDate());
        dalChange.setTask(taskRepository.get(bllChange.getTask().getId()));
        return dalChange;
    }

    private static Entities.CommentChange convert(Comment comment) {
        Entities.CommentChange dalComment = new Entities.CommentChange();
        dalComment.setComment(comment.getComment());
        return dalComment;
    }

    private static Entities.StateChange convert(StateChange stateChange) {
        Entities.StateChange dalStateChange = new Entities.StateChange();
        dalStateChange.setState(stateChange.getState());
        return dalStateChange;
    }

    private static Entities.EmployeeChange convert(EmployeeChange employeeChange, EmployeeRepository employeeRepository) {
        Entities.EmployeeChange dalEmployeeChange = new Entities.EmployeeChange();
        dalEmployeeChange.setEmployee(convert(employeeChange.getEmployee(), employeeRepository));
        return dalEmployeeChange;
    }

    public static Entities.DailyReport convert(DailyReport dailyReport, EmployeeRepository employeeRepository) {
        Entities.DailyReport dalDailyReport = new Entities.DailyReport();

        dalDailyReport.setID(dailyReport.getId());
        dalDailyReport.setEmployee(convert(dailyReport.getEmployee(), employeeRepository));
        dalDailyReport.setCreationDate(dailyReport.getCreationDate());
        dalDailyReport.setAccomplishedTasks(dailyReport.getAccomplishedTasks().stream().map(task -> EntityConverter.convert(task, employeeRepository)).collect(Collectors.toList()));

        return dalDailyReport;
    }

    public static DailyReport convert(Entities.DailyReport dailyReport) {
        DailyReport bllDailyReport = new DailyReport();

        bllDailyReport.setId(dailyReport.getID());
        bllDailyReport.setEmployee(convert(dailyReport.getEmployee()));
        bllDailyReport.setCreationDate(dailyReport.getCreationDate());
        bllDailyReport.setAccomplishedTasks(dailyReport.getAccomplishedTasks().stream().map(EntityConverter::convert).collect(Collectors.toList()));

        return bllDailyReport;

    }
}
