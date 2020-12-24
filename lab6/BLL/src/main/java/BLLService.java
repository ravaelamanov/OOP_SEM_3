import Repositories.DailyReportRepository;
import Repositories.EmployeeRepository;
import Repositories.TaskChangeRepository;
import Repositories.TaskRepository;
import util.AbstractRepositoryFactory;

public abstract class BLLService {
    protected TaskRepository taskRepository;
    protected EmployeeRepository employeeRepository;
    protected TaskChangeRepository taskChangeRepository;
    protected DailyReportRepository dailyReportRepository;

    public BLLService(AbstractRepositoryFactory factory) {
        taskRepository = factory.createTaskRepository();
        employeeRepository = factory.createEmployeeRepository();
        taskChangeRepository = factory.createTaskChangeRepository();
        dailyReportRepository = factory.createDailyReportRepository();
    }
}
