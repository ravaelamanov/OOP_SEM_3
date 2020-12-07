public class ClientBuilder {
    Client client;

    ClientBuilder() {
        reset();
    }

    public void reset() {
        client = new Client();
    }

    public Client getClient() {
        return client;
    }

    public ClientBuilder setFirstName(String firstName) {
        client.setFirstName(firstName);
        return this;
    }

    public ClientBuilder setLastName(String lastName) {
        client.setLastName(lastName);
        return this;
    }

    public ClientBuilder setPassportID(String passportID) {
        client.setPassportID(passportID);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }
}
