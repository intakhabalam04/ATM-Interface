import java.util.*;
import java.io.*;
import java.text.*;

class Account {
    private String accountId;
    private double balance;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(Account receiver, double amount) {
        if (balance >= amount) {
            balance -= amount;
            receiver.deposit(amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
