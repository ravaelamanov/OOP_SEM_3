package Repositories;

import Entities.TaskChange;
import org.hibernate.Session;

import java.util.Collection;

public class TaskChangeHibernateRepository extends HibernateRepository<TaskChange> implements TaskChangeRepository {
    @Override
    protected Collection<TaskChange> getAllImpl(Session session) {
        return session.createQuery("From TaskChange").list();
    }

    @Override
    public TaskChange getImpl(Session session, int id) {
        return session.get(TaskChange.class, id);
    }

    @Override
    public void deleteImpl(Session session, int id) {
        session.delete(session.get(TaskChange.class, id));
    }
}
