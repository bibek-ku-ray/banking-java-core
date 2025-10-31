import service.BankService;
import service.impl.BankServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        System.out.println("Welcome to Console Banking System");
        boolean running = true;
        while (running) {
            System.out.println("""
                    \n
                    1. Open Account
                    2. Deposit
                    3. Withdraw
                    4. Transfer
                    5. Account statement
                    6. List accounts
                    7. Search accounts by name
                    0. Exit
                    """);

            System.out.println("Choose option (Enter 0 to 7)");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            System.out.println("Choose: " + choice);

            switch (choice) {
                case "1" -> openAccount(sc, bankService);
                case "2" -> deposit(sc, bankService);
                case "3" -> System.out.println("3");
                case "4" -> System.out.println("4");
                case "5" -> System.out.println("5");
                case "6" -> listAccount(bankService);
                case "7" -> System.out.println("7");
                case "0" -> {
                    running = false;
                    System.out.println("Program End 🎬");
                }
            }
        }
    }

    private static void openAccount(Scanner sc, BankService bankService) {
        System.out.print("Customer Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Customer Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Account Type (SAVING/CURRENT): ");
        String type = sc.nextLine().trim();

        System.out.print("Initial balance (Optional): ");
        String balanceStr = sc.nextLine().trim();

        if (balanceStr.isBlank()) balanceStr = "0";
        double balance = Double.parseDouble(balanceStr);

        String accountNumber = bankService.openAccount(name, email, type);

        if (balance > 0) bankService.deposit(accountNumber, balance, "INITIAL DEPOSIT");

        System.out.println("New Account Created successfully. 🎉");
        System.out.println("Your account number: " + accountNumber);
    }

    private static void deposit(Scanner sc, BankService bankService) {
        System.out.print("Account Number: ");
        String accountNumber = sc.nextLine().trim();

        System.out.print("Amount: ");
        Double amount = sc.nextDouble();

        bankService.deposit(accountNumber, amount, "DEPOSIT");

        System.out.println("DEPOSIT SUCCESSFULLY, AMOUNT: " + amount + "🎉");
    }

    private static void listAccount(BankService bankService) {
        bankService.listAccounts().forEach(
                acc -> {
                    System.out.println(
                            acc.getAccountNumber() + " | " +
                                    acc.getAccountType() + " | " +
                                    acc.getBalance() + " | " +
                                    acc.getCustomerId()
                    );
                }
        );
    }

}