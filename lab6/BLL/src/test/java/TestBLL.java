import DTO.Employee;
import DTO.Task;
import util.AbstractRepositoryFactory;
import util.HibernateRepositoryFactory;

public class TestBLL {
    public static void main(String[] args) {
        AbstractRepositoryFactory factory = new HibernateRepositoryFactory();
        TaskManagementSystem taskManagementSystem = new TaskManagementSystem(factory);
        EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem(factory);

        Employee teamLead = new Employee();
        teamLead.setName("Team lead");
        employeeManagementSystem.createEmployee(teamLead);

        Employee employee1 = new Employee();
        employee1.setName("Emp 1");
        employeeManagementSystem.createEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Emp 2");
        employeeManagementSystem.createEmployee(employee2);

        employeeManagementSystem.addSlave(teamLead, employee1);
        employeeManagementSystem.addSlave(teamLead, employee2);

        Task task = new Task(); task.setName("task 1"); task.setDescription("This is task 1");
        taskManagementSystem.createTask(task);
        taskManagementSystem.changeEmployee(task, employee1);
    }
}
