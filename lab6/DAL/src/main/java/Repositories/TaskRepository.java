package Repositories;

import Entities.DailyReport;
import Entities.Employee;
import Entities.Task;
import Infrastructure.IRepository;

import java.util.Collection;

public interface TaskRepository extends IRepository<Task> {
    default Collection<Task> getByEmployee(Employee employee) {
        return find(task -> task.getEmployee().getID() == employee.getID());
    }

    default Collection<Task> getByReport(DailyReport report) {
        return find(task -> task.getReport().getID() == report.getID());
    }
}
