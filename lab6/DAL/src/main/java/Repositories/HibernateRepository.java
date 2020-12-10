package Repositories;

import Infrastructure.IEntity;
import Infrastructure.IRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collection;

public abstract class HibernateRepository<T extends IEntity> implements IRepository<T> {
    protected static SessionFactory sessionFactory;

    protected HibernateRepository() {
        try {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Couldn't initialize session factory! " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public Collection<T> getAll() {
        Collection<T> ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = getAllImpl(session);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    protected abstract Collection<T> getAllImpl(Session session);

    @Override
    public T get(int id) {
        T ret = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ret = getImpl(session, id);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return ret;
    }

    abstract public T getImpl(Session session, int id);

    @Override
    public void add(T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(T entity) {
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

            deleteImpl(session, id);

            transaction.commit();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public abstract void deleteImpl(Session session, int id);
}
