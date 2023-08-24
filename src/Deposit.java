import java.util.*;
public class Deposit {
    public static void performDeposit(Scanner scanner,Bank bank,AccountHolder accountHolder) {
        System.out.print("Enter amount to deposit: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
            scanner.nextLine();
            return;
        }

        if (amount < 0) {
            System.out.println("Enter positive value ");
            return;
        }

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
}
