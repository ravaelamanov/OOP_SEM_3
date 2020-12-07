import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;

public class Bank {
    private double interestRate;
    private double creditLimit;
    private double suspiciousLimit;
    private double creditCommission;
    private HashMap<Client, ArrayList<Integer>> cliAcc;
    private HashMap<Integer, Account> idAcc;
    private HashMap<Integer, Transaction> idTransaction;
    private ArrayList<Client> suspiciousList;

    public Bank(double interestRate, double creditLimit, double suspiciousLimit, double creditCommission) {
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.suspiciousLimit = suspiciousLimit;
        this.creditCommission = creditCommission;
        cliAcc = new HashMap<>();
        idAcc = new HashMap<>();
        idTransaction = new HashMap<>();
        suspiciousList = new ArrayList<>();
    }

    public void createDebit(Client client) {
        Account debit = new Debit(nextAccountId(), interestRate);
        addAccount(client, debit);
    }

    public void createCredit(Client client) {
        Account credit = new Credit(nextAccountId(), creditLimit, creditCommission);
        addAccount(client, credit);
    }

    public void createDeposit(Client client, LocalDate expirationDate, double initial) {
        Account deposit = new Deposit(nextAccountId(), initial, expirationDate);
        addAccount(client, deposit);
    }

    public void withdraw(int accountID, double amount) throws WithdrawalFailure {
        Account account = Objects.requireNonNull(idAcc.get(accountID));
        Withdrawal withdrawal = new Withdrawal(nextTransactionId(), account, amount);
        transact(withdrawal);
    }

    public void replenish(int accountID, double amount) throws WithdrawalFailure {
        Account account = Objects.requireNonNull(idAcc.get(accountID));
        Replenishment replenishment = new Replenishment(nextTransactionId(), account, amount);
        transact(replenishment);
    }

    public void transfer(int fromID, int toID, double amount) throws WithdrawalFailure {
        Account fromAcc = Objects.requireNonNull(idAcc.get(fromID));
        Account toAcc = Objects.requireNonNull(idAcc.get(toID));
        Transfer transfer = new Transfer(nextTransactionId(), fromAcc, toAcc, amount);
        transact(transfer);
    }

    public void cancelTransaction(int ID) throws WithdrawalFailure {
        Transaction transaction = Objects.requireNonNull(idTransaction.get(ID));
        transaction.cancel();
    }

    public void updateClientPassportID(String firstName, String lastName, String passportID) {
        Objects.requireNonNull(passportID);
        Client client = new ClientBuilder().setFirstName(firstName).setLastName(lastName).setPassportID(passportID).getClient();

        if (!cliAcc.containsKey(client))
            throw new RuntimeException("No such client to update! Client name: " + firstName + " " + lastName);

        client.setAddress(getClient(firstName, lastName).getAddress());
        cliAcc.put(client, cliAcc.remove(client));

        if (!Client.isSuspicious(client)) {
            tryToUnsuspicious(client);
        }
    }

    public void updateClientAddress(String firstName, String lastName, String address) {
        Objects.requireNonNull(address);
        Client client = new ClientBuilder().setFirstName(firstName).setLastName(lastName).setAddress(address).getClient();

        if (!cliAcc.containsKey(client))
            throw new RuntimeException("No such client to update! Client name: " + firstName + " " + lastName);

        client.setPassportID(getClient(firstName, lastName).getPassportID());
        cliAcc.put(client, cliAcc.remove(client));

        if (!Client.isSuspicious(client)) {
            tryToUnsuspicious(client);
        }
    }

    private void tryToUnsuspicious(Client client) {
        if (suspiciousList.contains(client)) {
            suspiciousList.remove(client);
            for (Integer accountID : cliAcc.get(client)) {
                idAcc.replace(accountID, ((SuspiciousAccount) idAcc.get(accountID)).release());
            }
        }
    }

    private Client getClient(String firstName, String lastName) {
        Client client = new ClientBuilder().setFirstName(firstName).setLastName(lastName).getClient();
        for (Map.Entry<Client, ArrayList<Integer>> entry : cliAcc.entrySet()) {
            if (client.equals(entry.getKey())) {
                return entry.getKey();
            }
        }
        return new Client();
    }

    public void accumulate(LocalDate now) {
        for (Map.Entry<Integer, Account> entry : idAcc.entrySet()) {
            Account account = entry.getValue();
            if (account instanceof IAccumulating) {
                ((IAccumulating) account).accumulate(now);
            }
        }
    }

    public void addAccumulated(LocalDate now) throws WithdrawalFailure {
        if (now.get(DAY_OF_MONTH) != 1) {
            return;
        }
        for (Map.Entry<Integer, Account> entry : idAcc.entrySet()) {
            Account account = entry.getValue();
            if (account instanceof IAccumulating) {
                Replenishment replenishment = new Replenishment(nextTransactionId(), account, ((IAccumulating) account).getAccumulated());
                transact(replenishment);
                ((IAccumulating) account).resetAccumulated();
            }
        }
    }

    private void addAccount(Client client, Account account) {
        if (client.getFirstName().isEmpty() || client.getLastName().isEmpty()) {
            throw new RuntimeException("Invalid client!");
        }

        if (Client.isSuspicious(client) && !suspiciousList.contains(client)) {
            account = new SuspiciousAccount(account, suspiciousLimit);
            suspiciousList.add(client);
        }
        idAcc.put(account.getID(), account);

        if (!cliAcc.containsKey(client))
            cliAcc.put(client, new ArrayList<>());

        cliAcc.get(client).add(account.ID);
    }

    private void transact(Transaction transaction) throws WithdrawalFailure {
        transaction.apply();
        idTransaction.put(transaction.getID(), transaction);
    }

    public void print() {
        for (Map.Entry<Client, ArrayList<Integer>> entry : cliAcc.entrySet()) {
            Client client = entry.getKey();
            System.out.println("Name: " + client.getFirstName() + " " + client.getLastName());
            for (Integer accountID : entry.getValue()) {
                System.out.println(idAcc.get(accountID).getID() + ": " + idAcc.get(accountID).getSavings());
            }
        }
    }

    private int nextAccountId() {
        return idAcc.size() + 1;
    }

    private int nextTransactionId() {
        return idTransaction.size() + 1;
    }
}
