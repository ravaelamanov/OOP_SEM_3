public class Credit extends Account {
    private double limit;
    private double commission;

    public Credit(int ID, double limit, double commission) {
        super(ID);
        this.commission = commission;
        this.limit = limit;
    }

    @Override
    public void withdraw(double amount) throws WithdrawalFailure {
        if (savings < 0) {
            amount += commission;
        }
        check(amount);
        super.withdraw(amount);
    }

    private void check(double amount) throws WithdrawalFailure {
        if (amount > limit + savings) {
            throw new WithdrawalFailure("Failed to withdraw!", savings, amount);
        }
    }
}
