package to;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class AppTestSteps {
    private Account account;
    private Bank bank;
    private Card card;
    private CashMachine cashMachine;
    private Client client;

    @Given("konto")
    public void anAccount() {
        if (client == null) {
            aClient();
        }
        account = new Account(client);
    }

    @Given("bank")
    public void aBank() {
        bank = new Bank();
    }

    @Given("karta")
    public void aCard() {
        if (account == null) {
            anAccount();
        }
        card = new Card(account, "1234");
    }

    @Given("bankomat")
    public void aCashMachine() {
        cashMachine = new CashMachine();
    }

    @Given("klient")
    public void aClient() {
        client = new Client(20);
    }

    @Given("klient ma $age lat")
    public void aClientIs(int age) {
        client = new Client(age);
    }

    @Given("karta w bankomacie")
    public void aCardInTheCashMachine() {
        if (cashMachine == null) {
            aCashMachine();
        }
        if (card == null) {
            aCard();
        }
        cashMachine.insertCard(card);
    }

    @Given("klient jest zalogowany w bankomacie")
    public void theClientIsLoggedInTheCashMachine() {
        if (cashMachine == null) {
            aCashMachine();
        }
        if (card == null) {
            aCard();
        }
        cashMachine.insertCard(card);
        cashMachine.typePin(card.getPin());
    }

    @Given("saldo bankomatu wynosi $amount złotych")
    public void theCashMachineBalanceEquals(double amount) {
        if (cashMachine == null) {
            aCashMachine();
        }
        cashMachine.setBalance(amount);
    }

    @Given("saldo konta klienta wynosi $amount złotych")
    public void theClientBalanceEquals(double amount) {
        if (account == null) {
            anAccount();
        }
        account.setBalance(amount);
    }

    @When("klient składa wniosek o założenie konta")
    public void theClientRequestANewAccount() {
        account = bank.createAccount(client);
    }

    @When("klient wprowadzi poprawny PIN")
    public void theClientTypedACorrectPin() {
        String correctPin = card.getPin();
        cashMachine.typePin(correctPin);
    }

    @When("klient wprowadzi niepoprawny PIN")
    public void theClientTypedAnIncorrectPin() {
        String incorrectPin = card.getPin() + "!@#$";
        cashMachine.typePin(incorrectPin);
    }

    @When("klient wypłaci $amount złotych")
    public void theClientWithdrewCash(double amount) {
        cashMachine.withdrawCash(amount);
    }

    @When("klient wpłaci $amount złotych")
    public void theClientDepositedCash(double amount) {
        cashMachine.depositCash(amount);
    }

    @Then("konto powinno zostać stowrzone")
    public void theAccountShouldBeCreated() {
        assertNotNull(account);
    }

    @Then("konto nie powinno zostać stworzone")
    public void theAccountShouldNotBeCreated() {
        assertNull(account);
    }

    @Then("klient powinien być zalogowany w bankomacie")
    public void iShouldBeLoggedInTheCashMachine() {
        assertEquals(cashMachine.getAccount().getClient(), client);
    }

    @Then("klient nie powinien być zalogowany w bankomacie")
    public void iShouldNotBeLoggedInTheCashMachine() {
        assertNull(cashMachine.getAccount());
    }

    @Then("karta nie powinna być w bankomacie")
    public void theCardShouldNotBeInTheCashMachine() {
        assertNull(cashMachine.getCard());
    }

    @Then("saldo konta klienta powinno wynosić $amount złotych")
    public void theClientBalanceShouldEqual(double amount) {
        assertEquals(amount, account.getBalance(), 0.01);
    }

    @Then("saldo bankomatu powinno wynosić $amount złotych")
    public void theCashMachineBalanceShouldEqual(double amount) {
        assertEquals(amount, cashMachine.getBalance(), 0.01);
    }
}
