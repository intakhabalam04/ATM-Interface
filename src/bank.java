import java.util.*;
import java.io.*;
import java.text.*;




class Bank {
    private Map<String, Account> accounts;
    private Map<String, AccountHolder> accountHolders;

    public Bank() {
        accounts = new HashMap<>();
        accountHolders = new HashMap<>();
        // Initialize with some dummy accounts
        Account account1 = new Account("123", 1000.0);
        accounts.put(account1.getAccountId(), account1);
        Account account2 = new Account("456", 500.0);
        accounts.put(account2.getAccountId(), account2);
        Account account3 = new Account("789", 500.0);
        accounts.put(account3.getAccountId(), account3);
        AccountHolder accountHolder1 = new AccountHolder("123", "123", "Intakhab Alam");
        accountHolders.put(accountHolder1.getUserId(), accountHolder1);
        AccountHolder accountHolder2 = new AccountHolder("456", "456", "Md Khushnood Alam");
        accountHolders.put(accountHolder2.getUserId(), accountHolder2);
        AccountHolder accountHolder3 = new AccountHolder("789", "i", "Purna");
        accountHolders.put(accountHolder3.getUserId(), accountHolder3);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public AccountHolder getAccountHolder(String userId) {
        return accountHolders.get(userId);
    }
}
