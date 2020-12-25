package Repositories;

import Entities.DailyReport;
import Entities.Employee;
import Infrastructure.IRepository;

import java.util.Collection;

public interface DailyReportRepository extends IRepository<DailyReport> {
    default Collection<DailyReport> getByEmployee(Employee employee) {
        return find(dailyReport -> dailyReport.getEmployee() != null && dailyReport.getEmployee().getID() == employee.getID());
    }

    default DailyReport getLastByEmployee(Employee employee) {
        Collection<DailyReport> dailyReports = getByEmployee(employee);
        DailyReport ret = null;
        for (DailyReport dailyReport : dailyReports) {
            ret = dailyReport;
        }
        return ret;
    }
}
