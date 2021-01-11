package to;

public class Bank {
    public Account createAccount(Client client) {
        if (client == null) {
            return null;
        }
        if (client.getAge() < 18) {
            return null;
        }
        return new Account(client);
    }
}
