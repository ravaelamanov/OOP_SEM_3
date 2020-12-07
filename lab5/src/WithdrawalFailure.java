public class WithdrawalFailure extends Exception {
    double savings;
    double amount;

    public WithdrawalFailure(String msg, double savings, double amount) {
        super(msg);
        this.savings = savings;
        this.amount = amount;
    }
}
