import java.time.LocalDate;
import java.util.ArrayList;

public class DateLimiter implements ILimiter {
    LocalDate dateLimit;
    public DateLimiter(LocalDate dateLimit) {
        this.dateLimit = dateLimit;
    }

    @Override
    public int limit(ArrayList<? extends RestorePoint> restorePoints) {
        int count = 0;
        for (RestorePoint restorePoint : restorePoints) {
            if (restorePoint.getCreationTime().toLocalDate().isBefore(dateLimit)) {
                count++;
            }
            else break;
        }
        return count;
    }
}
