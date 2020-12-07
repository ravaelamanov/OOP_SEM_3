import java.time.LocalDate;

public interface IAccumulating {
    void accumulate(LocalDate now);
    void resetAccumulated();
    double getAccumulated();
}
