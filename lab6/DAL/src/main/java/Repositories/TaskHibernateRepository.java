package Repositories;

import Entities.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskHibernateRepository extends HibernateRepository<Task> implements TaskRepository {
    @Override
    protected Iterable<Task> getAllImpl(Session session) {
        return session.createQuery("From Task").list();
    }

    @Override
    public Task getImpl(Session session, int id) {
        return session.get(Task.class, id);
    }

    @Override
    public void deleteImpl(Session session, int id) {
        session.delete(session.get(Task.class, id));
    }
}
