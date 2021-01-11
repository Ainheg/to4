package to;

public class Account {
    private double balance;
    private Client client;

    public Account(Client client) {
        this.client = client;
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
