import DTO.DailyReport;
import util.AbstractRepositoryFactory;

public class ReportManagementSystem extends BLLService {
    public ReportManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    public void createDailyReport(DailyReport dailyReport) {
        Entities.DailyReport dalDailyReport = EntityConverter.convert(dailyReport);
        dailyReportRepository.add(dalDailyReport);
        dailyReport.setId(dailyReport.getId());
    }
}
