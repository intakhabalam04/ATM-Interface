import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private final Bank bank;
    private AccountHolder accountHolder;

    /**
     * Constructor for creating an ATM object.
     * Initializes the bank by creating a new Bank instance.
     */
    public ATM() {
        bank = new Bank();
    }

    /**
     * A method to clear the console screen, creating a cleaner UI for the user.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * The starting point of the ATM application.
     * Asks the user to enter User ID and PIN, and if authentication is successful,
     * displays the main menu to the user.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine(); // Read the user's entered User ID.

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine(); // Read the user's entered PIN.

        if (authenticate(userId, pin)) {
            // If authentication is successful, show the main menu.
            clearScreen();
            System.out.println("Authentication Successful!");
            System.out.println("Welcome " + accountHolder.getName());
            showMenu(scanner);
        } else {
            System.out.println("Authentication Failed!");
        }
    }

    /**
     * Validate the User ID and PIN to authenticate the user.
     * If the credentials are valid, set the accountHolder to the corresponding account holder object.
     *
     * @param userId The user ID entered by the user.
     * @param pin    The PIN entered by the user.
     * @return true if authentication is successful, false otherwise.
     */
    private boolean authenticate(String userId, String pin) {
        AccountHolder accountHolder = bank.getAccountHolder(userId);
        if (accountHolder != null && accountHolder.getPin().equals(pin)) {
            this.accountHolder = accountHolder;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Display the main menu and handle user's menu choice.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Show Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Show Balance");
            System.out.println("6. LogOut");
            System.out.println("7. Quit");

            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Enter valid Index ");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    clearScreen();
                    TransactionHistory.showTransactionHistory(bank,accountHolder);
                }
                case 2 -> {
                    clearScreen();
                    Withdrawal.performWithdrawal(scanner,bank,accountHolder);
                }
                case 3 -> {
                    clearScreen();
                    Deposit.performDeposit(scanner,bank,accountHolder);
                }
                case 4 -> {
                    clearScreen();
                    Transfer.performTransfer(scanner,bank,accountHolder);
                }
                case 5 -> {
                    clearScreen();
                    Balance.showBalance(bank,accountHolder);
                }
                case 6 -> {
                    clearScreen();
                    Message.thankYouMessage(accountHolder.getName());
                    return;
                }
                case 7 -> {
                    Message.thankYouMessage();
                    System.exit(0);
                }
                default -> {
                    clearScreen();
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
