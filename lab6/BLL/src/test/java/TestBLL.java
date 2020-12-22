import DTO.Employee;
import Repositories.EmployeeHibernateRepository;
import Repositories.TaskHibernateRepository;

public class TestBLL {
    public static void main(String[] args) {
        try {
            TaskManagementSystem taskManagementSystem = new TaskManagementSystem();
            taskManagementSystem.setEmployeeRepository(new EmployeeHibernateRepository());
            taskManagementSystem.setTaskRepository(new TaskHibernateRepository());

            Employee teamLead = new Employee();
            teamLead.setMaster(null);
            teamLead.setName("Team lead");
            taskManagementSystem.createEmployee(teamLead);

            Employee employee1 = new Employee();
            employee1.setName("Emp 1");
            taskManagementSystem.createEmployee(employee1);

            Employee employee2 = new Employee();
            employee2.setName("Emp 2");
            taskManagementSystem.createEmployee(employee2);

            System.out.println("1");
            teamLead.addSlave(employee1);
            System.out.println("1");
            taskManagementSystem.updateEmployee(teamLead);
            System.out.println("1");
            teamLead.addSlave(employee2);
            System.out.println("1");
            taskManagementSystem.updateEmployee(teamLead);
            System.out.println("1");

        }
        catch (Exception exception) {
            System.out.println(exception.toString());
        }

    }
}
