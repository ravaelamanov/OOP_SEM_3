package Repositories;

import Entities.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskHibernateRepository extends HibernateRepository<Task> {
    @Override
    public Iterable<Task> getAll() {
        List ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = session.createQuery("From Task").list();

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    @Override
    public Task get(int id) {
        Task ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = session.get(Task.class, id);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    @Override
    public void add(Task entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Task entity) {
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

            session.delete(session.get(Task.class, id));

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
