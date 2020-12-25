package BLLServicies;

import DTO.DailyReport;
import DTO.Employee;
import DTO.Task;
import Repositories.EmployeeRepository;
import util.AbstractRepositoryFactory;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManagementSystem extends BLLService{

    private static EmployeeRepository employeeRepository;

    public EmployeeManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    @Override
    protected void initFactories(AbstractRepositoryFactory factory) {
        employeeRepository = factory.createEmployeeRepository();
    }

    public static Employee get(int Id) {
        return EntityConverter.convert(employeeRepository.get(Id));
    }

    public static void createEmployee(Employee employee) {
        DailyReport report = new DailyReport();

        Entities.Employee dalEmployee = EntityConverter.convert(employee);
        employeeRepository.add(dalEmployee);
        employee.setId(dalEmployee.getID());

        report.setEmployee(employee);
        ReportManagementSystem.createDailyReport(report);
    }

    public static void updateEmployee(Employee employee) {
        employeeRepository.update(EntityConverter.convert(employee));
    }

    public static void addSlave(Employee master, Employee slave) {
        master.addSlave(slave);
        updateEmployee(slave);
    }

    public static void solveTask(Employee employee, Task task) throws Exception {
        if (task.getEmployee().getId() != employee.getId()){
            throw new Exception("No task " + task.getId() + " for employee " + employee.getId());
        }
        TaskManagementSystem.solveTask(task);
    }

    public static void commentTask(Employee employee, Task task, String comment) throws Exception {
        if (task.getEmployee().getId() != employee.getId()){
            throw new Exception("No task " + task.getId() + " for employee " + employee.getId());
        }
        TaskManagementSystem.addComment(task, comment);
    }

    public static List<Employee> getSlaves(Employee employee) {
        return employeeRepository.getSlaves(EntityConverter.convert(employee)).stream().map(EntityConverter::convert).collect(Collectors.toList());
    }
}
