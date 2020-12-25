import DTO.Employee;
import DTO.Task;
import util.AbstractRepositoryFactory;
import util.HibernateRepositoryFactory;

public class TestBLL {
    public static void main(String[] args) throws Exception {
        AbstractRepositoryFactory factory = new HibernateRepositoryFactory();
        TaskManagementSystem taskManagementSystem = new TaskManagementSystem(factory);
        EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem(factory);
        ReportManagementSystem reportManagementSystem = new ReportManagementSystem(factory);
        EntityConverter entityConverter = new EntityConverter(factory);

        fillDB();



    }

    public static void fillDB() {
        Employee teamLead = new Employee();
        teamLead.setName("Team lead");
        EmployeeManagementSystem.createEmployee(teamLead);

        Employee employee1 = new Employee();
        employee1.setName("Emp 1");
        EmployeeManagementSystem.createEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Emp 2");
        EmployeeManagementSystem.createEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setName("Emp 3");
        EmployeeManagementSystem.createEmployee(employee3);

        Employee employee4 = new Employee();
        employee4.setName("Emp 4");
        EmployeeManagementSystem.createEmployee(employee4);

        EmployeeManagementSystem.addSlave(teamLead, employee1);
        EmployeeManagementSystem.addSlave(teamLead, employee2);
        EmployeeManagementSystem.addSlave(employee1, employee3);
        EmployeeManagementSystem.addSlave(employee1, employee4);

        Task task1 = new Task(); task1.setName("task 1"); task1.setDescription("This is task 1");
        TaskManagementSystem.createTask(task1);

        Task task2 = new Task(); task2.setName("task 2"); task2.setDescription("This is task 2");
        TaskManagementSystem.createTask(task2);

        Task task3 = new Task(); task3.setName("task 3"); task3.setDescription("This is task 3");
        TaskManagementSystem.createTask(task3);

        Task task4 = new Task(); task4.setName("task 4"); task4.setDescription("This is task 4");
        TaskManagementSystem.createTask(task4);
    }
}
