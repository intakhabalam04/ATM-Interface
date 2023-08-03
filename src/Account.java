public class Account {
    private final String accountId;
    private double balance;

    /**
     * Constructor for creating an Account object with a specified accountId and initial balance.
     *
     * @param accountId The unique identifier for the account.
     * @param balance   The initial balance of the account.
     */
    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    /**
     * Get the account ID.
     *
     * @return The account ID as a String.
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Get the current balance of the account.
     *
     * @return The account balance as a double.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposit the specified amount into the account, increasing the account balance.
     *
     * @param amount The amount to be deposited into the account.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraw the specified amount from the account, decreasing the account balance.
     *
     * @param amount The amount to be withdrawn from the account.
     */
    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("Withdrawal successful!");
    }

    /**
     * Transfer the specified amount from this account to the specified receiver account,
     * decreasing the sender's account balance and increasing the receiver's account balance.
     *
     * @param receiver The receiving account for the transfer.
     * @param amount   The amount to be transferred.
     */
    public void transfer(Account receiver, double amount) {
        balance -= amount;
        receiver.deposit(amount);
        System.out.println("Transfer Successful!");
    }
}
