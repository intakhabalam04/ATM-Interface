import java.util.*;

public class Bank {
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
