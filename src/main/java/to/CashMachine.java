package to;

public class CashMachine {
    private double balance;
    private Account account;
    private Card card;

    public CashMachine() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public Account getAccount() {
        return account;
    }

    public Card getCard() {
        return card;
    }    

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void insertCard(Card card) {
        this.card = card;
    }

    public void typePin(String pin) {
        if (pin.equals(card.getPin())) {
            account = card.getAccount();
        } else {
            account = null;
            card = null;
        }
    }

    public boolean withdrawCash(double amount) {
        if (balance < amount) {
            return false;
        }
        if (account == null) {
            return false;
        }
        if (account.getBalance() < amount) {
            return false;
        }
        setBalance(getBalance() - amount);
        account.setBalance(account.getBalance() - amount);
        return true;
    }

    public boolean depositCash(double amount) {
        if (account == null) {
            return false;
        }
        setBalance(getBalance() + amount);
        account.setBalance(account.getBalance() + amount);
        return true;
    }
}
