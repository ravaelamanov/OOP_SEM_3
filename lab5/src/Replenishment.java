public class Replenishment extends Transaction {
    private Account account;
    private double amount;

    public Replenishment(int ID, Account account, double amount) {
        super(ID);
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void apply() {
        account.replenish(amount);
    }

    @Override
    public void cancel() throws WithdrawalFailure {
        account.withdraw(amount);
    }
}
