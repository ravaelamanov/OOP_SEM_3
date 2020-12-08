import Entities.Employee;
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

            session.save(teamLead);
            session.save(slave1);
            session.save(slave2);

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
