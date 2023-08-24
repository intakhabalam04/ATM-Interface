import java.util.List;

public class TransactionHistory {
    public static void showTransactionHistory(Bank bank,AccountHolder accountHolder) {
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
}
