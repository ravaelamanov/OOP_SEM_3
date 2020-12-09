package Repositories;

import Entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeHibernateRepository extends HibernateRepository<Employee> {
    @Override
    public Iterable<Employee> getAll() {
        List ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = session.createQuery("From Employee").list();

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    @Override
    public Employee get(int id) {
        Employee ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = session.get(Employee.class, id);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    @Override
    public void add(Employee entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Employee entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(session.get(Employee.class, id));

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
