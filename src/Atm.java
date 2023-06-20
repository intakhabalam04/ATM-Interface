import java.util.*;
import java.io.*;
import java.text.*;




class ATM {
    Scanner scanner = new Scanner(System.in);

    private Bank bank;
    private AccountHolder accountHolder;

    public ATM() {
        bank = new Bank();
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticate(userId, pin)) {
            System.out.println("\nAuthentication Successful!");
            System.out.println("Welcome " + accountHolder.getName());
            showMenu(scanner);
        } else {
            System.out.println("Authentication Failed!");
        }
    }


    private void thankYouMessage() {
        System.out.println("\nThank you, " + accountHolder.getName() + ", for using my banking services!");
    }

    private boolean authenticate(String userId, String pin) {
        AccountHolder accountHolder = bank.getAccountHolder(userId);
        if (accountHolder != null && accountHolder.getPin().equals(pin)) {
            this.accountHolder = accountHolder;
            return true;
        } else {
            return false;
        }
    }

    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Show Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Show Balance"); // New option
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    showBalance();
                    break;
                case 6:
                    // System.out.println("Thank you for using the ATM!");
                    thankYouMessage();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        List<BankTransaction> transactionHistory = accountHolder.getTransactionHistory();
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (BankTransaction transaction : transactionHistory) {
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Account ID: " + transaction.getAccount().getAccountId());
                System.out.println("Date: " + transaction.getTransactionDate()); // Display the transaction date
                System.out.println("-------------------------");
            }
        }
    }

    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            account.withdraw(amount);
            Date transactionDate = new Date(); // Get the current date and time
            BankTransaction transaction = new BankTransaction(account, amount, "Withdrawal", transactionDate);
            accountHolder.addTransaction(transaction);
        } else {
            System.out.println("Invalid account!");
        }
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            account.deposit(amount);
            Date transactionDate = new Date(); // Get the current date and time
            BankTransaction transaction = new BankTransaction(account, amount, "Deposit", transactionDate);
            accountHolder.addTransaction(transaction);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid account!");
        }
    }

    private void performTransfer(Scanner scanner) {
        System.out.print("Enter account ID to transfer: ");
        String accountId = scanner.nextLine();

        Account receiverAccount = bank.getAccount(accountId);
        if (receiverAccount != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            Account senderAccount = bank.getAccount(accountHolder.getUserId());
            if (senderAccount != null) {
                senderAccount.transfer(receiverAccount, amount);
                Date transactionDate = new Date(); // Get the current date and time
                BankTransaction transaction = new BankTransaction(senderAccount, amount, "Transfer", transactionDate);
                accountHolder.addTransaction(transaction);
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Invalid account!");
            }
        }
    }

    private void showBalance() {
        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid account!");
        }
    }

}
