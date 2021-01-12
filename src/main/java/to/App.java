package to;

import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    private static final CashMachine cm = new CashMachine();
    private static final Bank piniondzBank = new Bank();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Card> cardsAvailable = new ArrayList<>();
    public static void main( String[] args )
    {
        cm.setBalance(500);
        System.out.println( "Witamy w bankomacie banku Piniondz" );
        menu();
    }
    private static void menu(){
        if(cm.getAccount() == null){
            System.out.println( "1. Załóż konto" );
            System.out.println( "2. Zaloguj się" );
        }
        else {
            System.out.println( "1. Sprawdź stan konta" );
            System.out.println( "2. Wypłać pieniądze" );
            System.out.println( "3. Wpłać pieniądze" );
            System.out.println( "4. Wyloguj się" );
        }
        waitForMenuInputAndExecuteAction();
    }
    private static void waitForMenuInputAndExecuteAction() {
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Podaj liczbę zgodnie z instrukcjami");
            menu();
        }
        if (cm.getAccount() == null) {
            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, jeszcze...");
                    break;
            }
        } else {
            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, jeszcze...");
                    break;
            }
        }
        menu();
    }

    private static void logout() {
        cm.removeCard();
        if (cm.getAccount() == null){
            System.out.println("Pomyślnie wylogowano");
        } else {
            System.out.println("Coś się popsuło");
        }
        menu();
    }

    private static void depositMoney() {
        System.out.println("Wpłacanie pieniędzy:");
        double ammount = 0.0;
        try {
            ammount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Podaj liczbę zgodnie z instrukcjami");
            menu();
        }
        if (cm.depositCash(ammount)){
            System.out.println("Operacja udana");
        } else {
            System.out.println("Operacja nieudana");
        }
        menu();
    }

    private static void withdrawMoney() {
        System.out.println("Wypłacanie pieniędzy:");
        double ammount = 0.0;
        try {
            ammount = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Podaj liczbę zgodnie z instrukcjami");
            menu();
        }
        if (cm.withdrawCash(ammount)){
            System.out.println("Operacja udana");
        } else {
            System.out.println("Operacja nieudana");
        }
        menu();
    }

    private static void checkBalance() {
        System.out.println("Stan konta:");
        System.out.println(cm.getAccount().getBalance());
        scanner.next();
        menu();
    }

    private static void login() {
        System.out.println("Włóż kartę");
        for (int i=1; i <= cardsAvailable.size(); i++){
            System.out.println(i-1 + " " + cardsAvailable.get(i-1).getAccount().getClient().getNumber());
        }
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Podaj liczbę zgodnie z instrukcjami");
            menu();
        }
        if (option < cardsAvailable.size()){
            Card toLogin = cardsAvailable.get(option);
            cm.insertCard(toLogin);
            System.out.println("Podaj PIN");
            cm.typePin(scanner.nextLine());
            if (cm.getAccount() != null){
                System.out.println("Logowanie powiodło się");
            } else {
                System.out.println("Logowanie się nie udało");
            }
        }
        else {
            System.out.println("Nie ma takiej karty");
        }
        menu();
    }

    private static void createAccount() {
        System.out.println("Podaj swój wiek");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Wiek raczej powinien być liczbą");
            menu();
        }
        Client client = new Client(age);
        Account acc = piniondzBank.createAccount(client);
        if (acc == null){
            System.out.println("Utworzenie konta nie udało się");
            menu();
        }
        System.out.println("Podaj PIN do karty, tylko u nas może być nawet słowny");
        String pin = scanner.nextLine();

        Card card = new Card(acc, pin);
        cardsAvailable.add(card);
    }
}
