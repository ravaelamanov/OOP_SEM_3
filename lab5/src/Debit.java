import java.time.LocalDate;

public class Debit extends Account implements IAccumulating {
    protected double interestRate;
    protected double accumulated;
    protected LocalDate lastAccumulated;

    public Debit(int ID, double interestRate) {
        super(ID);
        this.interestRate = interestRate / 365;
        lastAccumulated = LocalDate.now();
        accumulated = 0;
    }

    @Override
    public void withdraw(double amount) throws WithdrawalFailure {
        if (savings < amount) {
            throw new WithdrawalFailure("Failed to withdraw!", savings, amount);
        }
        super.withdraw(amount);
    }

    @Override
    public void accumulate(LocalDate now) {
        if (now.isAfter(lastAccumulated)) {
            accumulated += interestRate * savings;
            lastAccumulated = LocalDate.now();
        }
    }

    @Override
    public void resetAccumulated() {
        accumulated = 0;
    }

    @Override
    public double getAccumulated() {
        return accumulated;
    }
}
