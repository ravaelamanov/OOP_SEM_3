package BLLServicies;

import DTO.DailyReport;
import DTO.Employee;
import Repositories.DailyReportRepository;
import util.AbstractRepositoryFactory;

import java.util.Date;

public class ReportManagementSystem extends BLLService {
    private static DailyReportRepository dailyReportRepository;

    public ReportManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    @Override
    protected void initFactories(AbstractRepositoryFactory factory) {
        dailyReportRepository = factory.createDailyReportRepository();
    }

    public static DailyReport get(int id) {
        return EntityConverter.convert(dailyReportRepository.get(id));
    }

    public static void createDailyReport(DailyReport dailyReport) {
        dailyReport.setCreationDate(new Date());
        Entities.DailyReport dalDailyReport = EntityConverter.convert(dailyReport);
        dailyReportRepository.add(dalDailyReport);
        dailyReport.setId(dalDailyReport.getID());
    }

    public static void updateDailyReport(DailyReport dailyReport) {
        dailyReportRepository.update(EntityConverter.convert(dailyReport));
    }

    public static DailyReport getLastByEmployee(Employee employee) {
        return  EntityConverter.convert(dailyReportRepository.getLastByEmployee(EntityConverter.convert(employee)));
    }
}
