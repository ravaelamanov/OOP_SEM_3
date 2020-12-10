package Repositories;

import Entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeHibernateRepository extends HibernateRepository<Employee> implements EmployeeRepository {
    @Override
    protected Iterable<Employee> getAllImpl(Session session) {
        return session.createQuery("From Employee").list();
    }

    @Override
    public Employee getImpl(Session session, int id) {
        return session.get(Employee.class, id);
    }


    @Override
    public void deleteImpl(Session session, int id) {
        session.delete(session.get(Employee.class, id));
    }
}
