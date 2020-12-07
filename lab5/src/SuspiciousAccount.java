public class SuspiciousAccount extends Account {
    private Account account;
    private double limit;

    public SuspiciousAccount(Account account, double limit) {
        super(account.ID);
        this.account = account;
        this.limit = limit;
    }

    @Override
    public void withdraw(double amount) throws WithdrawalFailure {
        account.withdraw(Math.min(amount, limit));
    }

    @Override
    public void replenish(double amount) {
        account.replenish(amount);
    }

    @Override
    public void transfer(Account addressee, double amount) throws WithdrawalFailure {
        account.transfer(addressee, Math.min(amount, limit));
    }

    public Account release() {
        return account;
    }

    @Override
    public double getSavings() {
        return account.getSavings();
    }
}
