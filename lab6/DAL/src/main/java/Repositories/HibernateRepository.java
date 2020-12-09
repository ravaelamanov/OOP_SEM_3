package Repositories;

import Infrastructure.IEntity;
import Infrastructure.IRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
}
