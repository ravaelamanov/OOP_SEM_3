import java.util.Objects;

public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String passportID;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client))
            return false;
        Client other = (Client)obj;
        return firstName.equals(other.getFirstName()) && lastName.equals(other.getLastName());
    }

    @Override
    public int hashCode() {
        return 31 * firstName.hashCode() + lastName.hashCode();
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportID() {
        return passportID;
    }

    public static boolean isSuspicious(Client client) {
        return client.getPassportID() == null || client.getAddress() == null;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
