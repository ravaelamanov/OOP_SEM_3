package Infrastructure;

public interface IRepository<T extends IEntity> {
    Iterable<T> getAll();

    T get(int id);

    void add(T entity);

    void update(T entity);

    void delete(int id);

}
