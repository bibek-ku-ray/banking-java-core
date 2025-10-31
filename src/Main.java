import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Welcome to Console Banking System");

        boolean running = true;
        while (running) {
            System.out.println("""
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
                case "1" -> System.out.println("1");
                case "2" -> System.out.println("2");
                case "3" -> System.out.println("3");
                case "4" -> System.out.println("4");
                case "5" -> System.out.println("5");
                case "6" -> System.out.println("6");
                case "7" -> System.out.println("7");
                case "0" -> {
                    running = false;
                    System.out.println("Program End 🎬");
                }
            }
        }
    }


}

/*
    Resume
    - AI Faisal
    - Stock trading application
    - Ecommerce + Auth
    - Wallet apap
 */