package Repositories;

import Entities.DailyReport;
import Entities.Task;
import org.hibernate.Session;

public class DailyReportHibernateRepository extends HibernateRepository<DailyReport> implements DailyReportRepository {

    @Override
    protected Iterable<DailyReport> getAllImpl(Session session) {
        return session.createQuery("From DailyReport").list();
    }

    @Override
    public DailyReport getImpl(Session session, int id) {
        return session.get(DailyReport.class, id);
    }

    @Override
    public void deleteImpl(Session session, int id) {
        session.delete(session.get(Task.class, id));
    }
}
