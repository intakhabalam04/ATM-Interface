import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts;
    private final Map<String, AccountHolder> accountHolders;

    /**
     * Constructor for creating a Bank object.
     * Initializes the accounts and accountHolders maps and adds some sample data.
     * These sample data include two accounts and their corresponding account holders.
     */
    public Bank() {
        accounts = new HashMap<>();
        accountHolders = new HashMap<>();

        // Sample data: Create two accounts and add them to the accounts map.
        Account account1 = new Account("123", 1000.0);
        accounts.put(account1.getAccountId(), account1);

        Account account2 = new Account("456", 1000.0);
        accounts.put(account2.getAccountId(), account2);

        // Sample data: Create two account holders and add them to the accountHolders map.
        AccountHolder accountHolder1 = new AccountHolder("123", "123", "Intakhab Alam");
        accountHolders.put(accountHolder1.getUserId(), accountHolder1);

        AccountHolder accountHolder2 = new AccountHolder("456", "456", "Md Khushnood Alam");
        accountHolders.put(accountHolder2.getUserId(), accountHolder2);
    }

    /**
     * Get the Account object associated with the specified account ID.
     *
     * @param accountId The account ID to look up.
     * @return The Account object if found, or null if not found.
     */
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    /**
     * Get the AccountHolder object associated with the specified user ID.
     *
     * @param userId The user ID to look up.
     * @return The AccountHolder object if found, or null if not found.
     */
    public AccountHolder getAccountHolder(String userId) {
        return accountHolders.get(userId);
    }
}
