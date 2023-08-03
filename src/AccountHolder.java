import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private final String userId;
    private final String pin;
    private final String name;
    private final List<BankTransaction> transactionHistory;

    /**
     * Constructor for creating an AccountHolder object with the specified user ID, PIN, and name.
     * The transaction history list is initialized as an empty ArrayList.
     *
     * @param userId The unique identifier for the account holder.
     * @param pin    The PIN (Personal Identification Number) of the account holder.
     * @param name   The name of the account holder.
     */
    public AccountHolder(String userId, String pin, String name) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Get the user ID of the account holder.
     *
     * @return The user ID as a String.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Get the PIN (Personal Identification Number) of the account holder.
     *
     * @return The PIN as a String.
     */
    public String getPin() {
        return pin;
    }

    /**
     * Get the name of the account holder.
     *
     * @return The name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of bank transactions for the account holder.
     *
     * @return The list of bank transactions as a List<BankTransaction>.
     */
    public List<BankTransaction> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Add a bank transaction to the account holder's transaction history.
     *
     * @param transaction The bank transaction to be added to the history.
     */
    public void addTransaction(BankTransaction transaction) {
        transactionHistory.add(transaction);
    }
}
