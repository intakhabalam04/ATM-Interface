import java.util.*;

public class ATM {
    Scanner scanner = new Scanner(System.in);

    private Bank bank;
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

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clearScreen();
                    showTransactionHistory();
                    break;
                case 2:
                    clearScreen();
                    performWithdrawal(scanner);
                    break;
                case 3:
                    clearScreen();
                    performDeposit(scanner);
                    break;
                case 4:
                    clearScreen();
                    performTransfer(scanner);
                    break;
                case 5:
                    clearScreen();
                    showBalance();
                    break;
                case 6:
                    clearScreen();
                    thankYouMessage(accountHolder.getName());
                    return;
                case 7:
                    thankYouMessage();
                    System.exit(0);
                default:
                    clearScreen();
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Display the transaction history for the current account holder.
     */
    private void showTransactionHistory() {
        List<BankTransaction> transactionHistory = accountHolder.getTransactionHistory();
        System.out.println("--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            int count = 0;
            for (BankTransaction transaction : transactionHistory) {
                System.out.println(++count);
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: $" + transaction.getAmount());
                System.out.println("Account ID: " + transaction.getAccount().getAccountId());

                Account receiverAccount = transaction.getReceiverAccount();
                if (receiverAccount != null) {
                    System.out.println("Receiver Account ID: " + receiverAccount.getAccountId());
                } else {
                    System.out.println("Receiver Account ID: N/A");
                }

                System.out.println("Date: " + transaction.getTransactionDate());
                System.out.println("-------------------------");
            }
            System.out.println("Account balance: $" + bank.getAccount(accountHolder.getUserId()).getBalance());
        }
    }

    /**
     * Perform a withdrawal transaction for the current account holder.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid numeric amount.");
            scanner.nextLine();
            return;
        }

        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                Date transactionDate = new Date();
                BankTransaction transaction = new BankTransaction(account, null, amount, "Withdrawal", transactionDate);
                accountHolder.addTransaction(transaction);
            } else {
                System.out.println("Insufficient Funds!!");
            }
        } else {
            System.out.println("Invalid account!");
        }
    }

    /**
     * Perform a deposit transaction for the current account holder.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            account.deposit(amount);
            Date transactionDate = new Date();
            BankTransaction transaction = new BankTransaction(account, null, amount, "Deposit", transactionDate);
            accountHolder.addTransaction(transaction);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid account!");
        }
    }

    /**
     * Perform a transfer transaction for the current account holder.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void performTransfer(Scanner scanner) {
        System.out.print("Enter account ID to transfer: ");
        String accountId = scanner.nextLine();

        Account receiverAccount = bank.getAccount(accountId);
        if (receiverAccount != null) {
            System.out.print("Enter amount to transfer: ");
            double amount;
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a positive value.");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                scanner.nextLine();
                return;
            }

            Account senderAccount = bank.getAccount(accountHolder.getUserId());
            if (senderAccount != null) {
                if (senderAccount.getBalance() >= amount) {
                    senderAccount.transfer(receiverAccount, amount);
                    Date transactionDate = new Date();

                    BankTransaction senderTransaction = new BankTransaction(senderAccount, receiverAccount, amount,
                            "Transfer", transactionDate);
                    accountHolder.addTransaction(senderTransaction);

                    BankTransaction receiverTransaction = new BankTransaction(receiverAccount, senderAccount, amount,
                            "Transfer", transactionDate);
                    AccountHolder receiverAccountHolder = bank.getAccountHolder(receiverAccount.getAccountId());
                    receiverAccountHolder.addTransaction(receiverTransaction);

                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Invalid account!");
            }
        } else {
            System.out.println("Receiver account not found!");
        }
    }

    /**
     * Display the current account balance for the current account holder.
     */
    private void showBalance() {
        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid account!");
        }
    }

    /**
     * Show a personalized thank you message with the user's name.
     *
     * @param UserName The name of the user to include in the message.
     */
    private void thankYouMessage(String UserName) {
        System.out.println("\nThank you, " + UserName + ", for using my banking services!");
        System.out.println("Visit Again!");
    }

    /**
     * Show a general thank you message without the user's name.
     */
    private void thankYouMessage() {
        System.out.println("\nThank you for using my banking services!");
        System.out.println("Visit Again!");
    }
}
