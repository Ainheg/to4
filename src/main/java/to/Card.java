package to;

public class Card {
    private Account account;
    private String pin;

    public Card(Account account, String pin) {
        this.account = account;
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public String getPin() {
        return pin;
    }
}
