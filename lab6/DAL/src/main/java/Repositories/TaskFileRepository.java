package Repositories;

import Entities.Task;
import Infrastructure.IRepository;

public class TaskFileRepository implements IRepository<Task> {
    @Override
    public Iterable<Task> getAll() {
        return null;
    }

    @Override
    public Task get(int id) {
        return null;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(int id) {

    }
}
