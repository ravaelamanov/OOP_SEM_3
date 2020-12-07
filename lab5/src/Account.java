abstract class Account {
    protected double savings;
    protected int ID;

    public Account(int ID) {
        savings = 0;
        this.ID = ID;
    }

    public void withdraw(double amount) throws WithdrawalFailure {
        savings -= amount;
    }

    public void replenish(double amount) {
        savings += amount;
    }

    public void transfer(Account addressee, double amount) throws WithdrawalFailure {
        withdraw(amount);
        addressee.replenish(amount);
    }

    public int getID() {
        return ID;
    }

    public double getSavings() {
        return savings;
    }
}
