import util.AbstractRepositoryFactory;

public abstract class BLLService {
    protected AbstractRepositoryFactory factory;

    public BLLService(AbstractRepositoryFactory factory) {
        setFactory(factory);
    }

    public void setFactory(AbstractRepositoryFactory factory) {
        this.factory = factory;
        initFactories(factory);
/*        taskRepository = factory.createTaskRepository();
        employeeRepository = factory.createEmployeeRepository();
        taskChangeRepository = factory.createTaskChangeRepository();
        dailyReportRepository = factory.createDailyReportRepository();*/
    }

    abstract protected void initFactories(AbstractRepositoryFactory factory);
}
