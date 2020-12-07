public class Withdrawal extends Transaction {
    private Account account;
    private double amount;

    public Withdrawal(int ID, Account account, double amount) {
        super(ID);
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void apply() throws WithdrawalFailure {
        account.withdraw(amount);
    }

    @Override
    public void cancel() {
        account.replenish(amount);
    }
}
