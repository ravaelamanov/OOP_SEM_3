public class Transfer extends Transaction {
    private Account fromAcc;
    private Account toAcc;
    private double amount;

    public Transfer(int ID, Account fromAcc, Account toAcc, double amount) {
        super(ID);
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
    }

    @Override
    public void apply() throws WithdrawalFailure {
        fromAcc.transfer(toAcc, amount);
    }

    @Override
    public void cancel() throws WithdrawalFailure {
        toAcc.transfer(fromAcc, amount);
    }
}
