import DTO.DailyReport;
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

    public static void createDailyReport(DailyReport dailyReport) {
        dailyReport.setCreationDate(new Date());
        Entities.DailyReport dalDailyReport = EntityConverter.convert(dailyReport);
        dailyReportRepository.add(dalDailyReport);
        dailyReport.setId(dalDailyReport.getID());
    }

    public static void updateDailyReport(DailyReport dailyReport) {
        dailyReportRepository.update(EntityConverter.convert(dailyReport));
    }
}
