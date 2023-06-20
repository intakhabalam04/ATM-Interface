import java.util.*;
import java.io.*;
import java.text.*;

class BankTransaction {
    private Account account;
    private double amount;
    private String type;
    private Date transactionDate;

    public BankTransaction(Account account, double amount, String type, Date transactionDate) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
