import Entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    static SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public static void main(String[] args) {
        try {
            ArrayList<Employee> employees = createEmployees();
            ArrayList<Task> tasks = createTasks();
            ArrayList<TaskChange> changes = createChanges();
            DailyReport report = new DailyReport();

            changes.get(0).setTask(tasks.get(0));
            changes.get(1).setTask(tasks.get(0));

            tasks.get(0).setChanges(changes);
            tasks.get(0).setReport(report);
            tasks.get(0).setEmployee(employees.get(0));
            tasks.get(0).setChanges(changes);

            report.setEmployee(employees.get(0));

            employees.get(0).setTasks(tasks);
            employees.get(0).setDailyReports(Arrays.asList(report));

            saveList(employees);
            saveList(changes);
            saveList(tasks);

            saveObj(report);

            factory.close();
        }
        catch (Exception exception) {
            System.out.println(exception.toString());
            exception.printStackTrace(System.out);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static ArrayList<Employee> createEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee teamlead = new Employee(); teamlead.setName("teamlead");
        Employee slave1 = new Employee(); slave1.setName("slave1");
        Employee slave2 = new Employee(); slave2.setName("slave2");
        teamlead.setSlaves(Arrays.asList(slave1, slave2));
        slave1.setMaster(teamlead);
        slave2.setMaster(teamlead);

        employees.add(teamlead);
        employees.add(slave1);
        employees.add(slave2);

        return employees;
    }

    public static ArrayList<Task> createTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        Task task1 = new Task(); task1.setName("task1");
        Task task2 = new Task(); task2.setName("task2");

        tasks.add(task1); tasks.add(task2);

        return tasks;
    }

    public static ArrayList<TaskChange> createChanges() {
        ArrayList<TaskChange> changes = new ArrayList<>();

        CommentChange comment1 = new CommentChange(); comment1.setComment("this is comment1");
        CommentChange comment2 = new CommentChange(); comment2.setComment("this is comment2");

        changes.add(comment1);
        changes.add(comment2);

        return changes;
    }

    public static void saveList(List<?> list) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        for (Object o : list) {
            session.save(o);
        }

        transaction.commit();
        session.close();
    }

    public static void saveObj(Object obj) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(obj);

        transaction.commit();
        session.close();
    }
}
