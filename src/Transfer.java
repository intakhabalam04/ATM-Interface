import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transfer {
    public static void performTransfer(Scanner scanner,Bank bank,AccountHolder accountHolder) {
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
}
