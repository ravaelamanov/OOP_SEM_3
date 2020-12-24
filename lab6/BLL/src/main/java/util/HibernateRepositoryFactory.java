package util;

import Repositories.DailyReportRepository;
import Repositories.EmployeeRepository;
import Repositories.TaskChangeRepository;
import Repositories.TaskRepository;

public class HibernateRepositoryFactory implements AbstractRepositoryFactory{
    @Override
    public TaskRepository createTaskRepository() {
        return new Repositories.TaskHibernateRepository();
    }

    @Override
    public EmployeeRepository createEmployeeRepository() {
        return new Repositories.EmployeeHibernateRepository();
    }

    @Override
    public TaskChangeRepository createTaskChangeRepository() {
        return new Repositories.TaskChangeHibernateRepository();
    }

    @Override
    public DailyReportRepository createDailyReportRepository() {
        return new Repositories.DailyReportHibernateRepository();
    }
}
