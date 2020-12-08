import Entities.Employee;
import Entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            Employee teamLead = new Employee("TeamLead");
            Employee slave1 = new Employee("Slave1");
            Employee slave2 = new Employee("Slave2");
            teamLead.setSlaves(Arrays.asList(slave1, slave2));
            slave1.setMaster(teamLead);
            slave2.setMaster(teamLead);
            Task task1 = new Task(); task1.setName("task1"); task1.setDescription("this is task1"); task1.setResponsibleEmployee(slave1);
            Task task2 = new Task(); task2.setName("task2"); task2.setDescription("this is task2"); task2.setResponsibleEmployee(slave2);


            session.save(teamLead);
            session.save(slave1);
            session.save(slave2);
            session.save(task1);
            session.save(task2);

            transaction.commit();
            session.close();

            session = factory.openSession();
            transaction = session.beginTransaction();

            List employees = session.createQuery("From Employee").list();

            for(Object it : employees) {
                Employee employee = (Employee) it;
                String msg = "";
                msg += employee.getID() + " " + employee.getName() + " Master: ";
                if (employee.getMaster() != null) {
                    msg += employee.getMaster().getName() + " ";
                }
                msg += "Slaves: ";
                for (Employee slave : employee.getSlaves()) {
                    msg += slave.getName() + " ";
                }
                System.out.println(msg);
            }

            transaction.commit();
            session.close();
            factory.close();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
