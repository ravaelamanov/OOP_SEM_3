package util;

public interface AbstractRepositoryFactory {
    Repositories.TaskRepository createTaskRepository();

    Repositories.EmployeeRepository createEmployeeRepository();

    Repositories.TaskChangeRepository createTaskChangeRepository();

    Repositories.DailyReportRepository createDailyReportRepository();
}
