import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Deposit extends Debit {
    private LocalDate expirationDate;

    public Deposit(int ID, double initial, LocalDate expirationDate) {
        super(ID, calcInterestRate(initial));
        this.expirationDate = expirationDate;
        savings = initial;
    }

    private static double calcInterestRate(double initial) {
        double interestRate;

        if (initial < 50000)
            interestRate = 0.03;
        else if (initial < 100000)
            interestRate = 0.035;
        else interestRate = 0.04;

        return interestRate;
    }

    @Override
    public void withdraw(double amount) throws WithdrawalFailure {
        if (expired()) {
            super.withdraw(amount);
        } else
            throw new RuntimeException("Invalid operation. Deposit date not expired yet! Days till expiration: " + DAYS.between(LocalDate.now(), expirationDate));
    }

    @Override
    public void transfer(Account addressee, double amount) throws WithdrawalFailure {
        if (expired()) {
            super.transfer(addressee, amount);
        } else
            throw new RuntimeException("Invalid operation. Deposit date not expired yet! Days till expiration: " + DAYS.between(LocalDate.now(), expirationDate));
    }

    private boolean expired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
