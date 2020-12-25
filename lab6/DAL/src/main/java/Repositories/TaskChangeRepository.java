package Repositories;

import Entities.Task;
import Entities.TaskChange;
import Infrastructure.IRepository;

import java.util.Collection;

public interface TaskChangeRepository extends IRepository<TaskChange> {
    default Collection<TaskChange> getByTask(Task task) {
        return find(taskChange -> taskChange.getTask().getID() == task.getID());
    }
}
