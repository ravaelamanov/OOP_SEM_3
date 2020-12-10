package Repositories;

import Entities.DailyReport;
import org.hibernate.Session;

import java.util.Collection;

public class DailyReportHibernateRepository extends HibernateRepository<DailyReport> implements DailyReportRepository {

    @Override
    protected Collection<DailyReport> getAllImpl(Session session) {
        return session.createQuery("From DailyReport").list();
    }

    @Override
    public DailyReport getImpl(Session session, int id) {
        return session.get(DailyReport.class, id);
    }

    @Override
    public void deleteImpl(Session session, int id) {
        session.delete(session.get(DailyReport.class, id));
    }
}
