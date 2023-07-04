import java.util.*;
import java.io.*;
import java.text.*;

class BankTransaction {
    private Account account;
    private Account receiverAccount; // New field for receiver account
    private double amount;
    private String type;
    private Date transactionDate;

    public BankTransaction(Account account, Account receiverAccount, double amount, String type, Date transactionDate) {
        this.account = account;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}

class Bank {
    private Map<String, Account> accounts;
    private Map<String, AccountHolder> accountHolders;

    public Bank() {
        accounts = new HashMap<>();
        accountHolders = new HashMap<>();

        Account account1 = new Account("123", 1000.0);
        accounts.put(account1.getAccountId(), account1);

        Account account2 = new Account("456", 1000.0);
        accounts.put(account2.getAccountId(), account2);

        AccountHolder accountHolder1 = new AccountHolder("123", "123", "Intakhab Alam");
        accountHolders.put(accountHolder1.getUserId(), accountHolder1);

        AccountHolder accountHolder2 = new AccountHolder("456", "456", "Md Khushnood Alam");
        accountHolders.put(accountHolder2.getUserId(), accountHolder2);

    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public AccountHolder getAccountHolder(String userId) {
        return accountHolders.get(userId);
    }
}

class ATM {
    Scanner scanner = new Scanner(System.in);

    private Bank bank;
    private AccountHolder accountHolder;

    public ATM() {
        bank = new Bank();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticate(userId, pin)) {
            clearScreen();
            System.out.println("Authentication Successful!");
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
            System.out.println("5. Show Balance");
            System.out.println("6. Quit");

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
                    thankYouMessage();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

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

                    // Transaction in sender's account
                    BankTransaction senderTransaction = new BankTransaction(senderAccount, receiverAccount, amount,
                            "Transfer", transactionDate);
                    accountHolder.addTransaction(senderTransaction);

                    // Transaction in receiver's account
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

    private void showBalance() {
        Account account = bank.getAccount(accountHolder.getUserId());
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid account!");
        }
    }

}

class AccountHolder {
    private String userId;
    private String pin;
    private String name;
    private List<BankTransaction> transactionHistory;

    public AccountHolder(String userId, String pin, String name) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public List<BankTransaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(BankTransaction transaction) {
        transactionHistory.add(transaction);
    }
}

class Account {
    private String accountId;
    private double balance;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;

    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("Withdrawal successful!");
    }

    public void transfer(Account receiver, double amount) {
        balance -= amount;
        receiver.deposit(amount);
        System.out.println("Transfer Successful!");
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm1 = new ATM();
        while (true) {
            atm1.start();
        }
    }
}
