import java.util.Date;

public class BankTransaction {
    private final Account account;
    private final Account receiverAccount;
    private final double amount;
    private final String type;
    private final Date transactionDate;

    /**
     * Constructor for creating a BankTransaction object.
     *
     * @param account         The Account involved in the transaction.
     * @param receiverAccount The receiving Account (for transfer transactions), or null for other transaction types.
     * @param amount          The transaction amount.
     * @param type            The type of transaction (e.g., "Withdrawal," "Deposit," "Transfer," etc.).
     * @param transactionDate The date and time of the transaction.
     */
    public BankTransaction(Account account, Account receiverAccount, double amount, String type, Date transactionDate) {
        this.account = account;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    /**
     * Get the Account involved in the transaction.
     *
     * @return The Account object representing the account involved in the transaction.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Get the receiving Account (for transfer transactions).
     *
     * @return The receiving Account object, or null if the transaction type is not a transfer.
     */
    public Account getReceiverAccount() {
        return receiverAccount;
    }

    /**
     * Get the transaction amount.
     *
     * @return The amount involved in the transaction as a double value.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Get the type of transaction.
     *
     * @return A String representing the type of transaction (e.g., "Withdrawal," "Deposit," "Transfer," etc.).
     */
    public String getType() {
        return type;
    }

    /**
     * Get the date and time of the transaction.
     *
     * @return The Date object representing the date and time of the transaction.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }
}
