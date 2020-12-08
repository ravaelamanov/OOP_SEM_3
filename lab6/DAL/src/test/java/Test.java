import Entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            Employee teamLead = new Employee("Team Lead");
            Employee slave1 = new Employee("Slave 1");
            Employee slave2 = new Employee("Slave 2");
            teamLead.setSlaves(Arrays.asList(slave1, slave2));

            transaction.commit();
            session.save(teamLead);
            session.close();
            factory.close();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
