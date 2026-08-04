package Encapsulation;
import java.util.ArrayList;
import java.util.List;

class Account {
    private double balance;
    private final List<String> transactionHistory=new ArrayList<>();
    public Account(double initialBalance) {
        this.balance = initialBalance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
    }
    public double getBalance() {
        return balance;
    }
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}