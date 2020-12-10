package Repositories;

import Entities.Task;
import org.hibernate.Session;

import java.util.Collection;


public class TaskHibernateRepository extends HibernateRepository<Task> implements TaskRepository {
    @Override
    protected Collection<Task> getAllImpl(Session session) {
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
