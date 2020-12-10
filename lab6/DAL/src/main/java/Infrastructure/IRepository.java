package Infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface IRepository<T extends IEntity> {
    Collection<T> getAll();

    T get(int id);

    void add(T entity);

    void update(T entity);

    void delete(int id);

    default Collection<T> find(Predicate<T> predicate) {
        Collection<T> entities = getAll();
        List<T> ret = new ArrayList<>();
        for (T entity : entities) {
            if (predicate.test(entity)) {
                ret.add(entity);
            }
        }
        return ret;
    }

}
