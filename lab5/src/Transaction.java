abstract public class Transaction {
    protected int ID;

    public Transaction(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    abstract void apply() throws WithdrawalFailure;
    abstract void cancel() throws WithdrawalFailure;
}
