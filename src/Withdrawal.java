import java.util.*;


public class Withdrawal {
    public static void performWithdrawal(Scanner scanner,Bank bank,AccountHolder accountHolder) {
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
}
