package Repositories;

import Entities.Employee;
import org.hibernate.Session;

import java.util.Collection;

public class EmployeeHibernateRepository extends HibernateRepository<Employee> implements EmployeeRepository {
    @Override
    protected Collection<Employee> getAllImpl(Session session) {
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
