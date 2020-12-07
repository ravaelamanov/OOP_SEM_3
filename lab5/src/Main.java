import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            ClientBuilder builder = new ClientBuilder();

            Bank bank = new Bank(3.65, 500, 10, 3);

            bank.createDebit(builder.setFirstName("Ivan").setLastName("Ivanov").getClient());

            bank.replenish(1, 500);
            bank.print();

            bank.withdraw(1, 200);
            bank.print();
            bank.updateClientAddress("Ivan", "Ivanov", "address1");
            bank.updateClientPassportID("Ivan", "Ivanov", "passportID1");
            bank.withdraw(1, 490);
            bank.print();

            bank.replenish(1, 100000);
            bank.accumulate(myLocalDateNow(1));

            bank.replenish(1, 100000);
            bank.accumulate(myLocalDateNow(2));

            bank.withdraw(1, 150000);
            bank.accumulate(myLocalDateNow(3));

            bank.addAccumulated(myLocalDateNow(4));
            bank.print();

            System.out.println("\n");
            builder.reset();
            bank.createDebit(builder.setFirstName("Petr").setLastName("Petrov").setAddress("address2").setPassportID("passportID2").getClient());
            bank.transfer(1, 2, 3500);
            bank.print();

            System.out.println("\n");
            bank.cancelTransaction(8);
            bank.print();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    public static LocalDate myLocalDateNow(long shift) {
        return LocalDate.now(Clock.offset(Clock.systemUTC(), Duration.ofDays(shift)));
    }
}
