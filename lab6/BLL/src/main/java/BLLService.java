import Repositories.DailyReportRepository;
import Repositories.EmployeeRepository;
import Repositories.TaskChangeRepository;
import Repositories.TaskRepository;
import util.AbstractRepositoryFactory;

public abstract class BLLService {
    protected static TaskRepository taskRepository;
    protected static EmployeeRepository employeeRepository;
    protected static TaskChangeRepository taskChangeRepository;
    protected static DailyReportRepository dailyReportRepository;
    protected static AbstractRepositoryFactory factory;

    public BLLService(AbstractRepositoryFactory factory) {
        setFactory(factory);
    }

    public static void setFactory(AbstractRepositoryFactory factory) {
        BLLService.factory = factory;
        taskRepository = factory.createTaskRepository();
        employeeRepository = factory.createEmployeeRepository();
        taskChangeRepository = factory.createTaskChangeRepository();
        dailyReportRepository = factory.createDailyReportRepository();
    }
}
