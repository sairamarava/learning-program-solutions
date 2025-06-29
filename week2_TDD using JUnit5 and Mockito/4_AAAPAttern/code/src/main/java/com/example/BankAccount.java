package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * BankAccount class for demonstrating AAA pattern and test fixtures
 */
public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;
    private boolean isActive;
    
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.isActive = true;
        logTransaction("Account created with initial balance: $" + initialBalance);
    }
    
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is inactive");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
        logTransaction("Deposited: $" + amount + ", New balance: $" + balance);
    }
    
    public void withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is inactive");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        balance -= amount;
        logTransaction("Withdrew: $" + amount + ", New balance: $" + balance);
    }
    
    public void transfer(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        
        this.withdraw(amount); // This will validate amount and balance
        targetAccount.deposit(amount);
        
        logTransaction("Transferred: $" + amount + " to account " + targetAccount.getAccountNumber());
        targetAccount.logTransaction("Received transfer: $" + amount + " from account " + this.accountNumber);
    }
    
    public double calculateInterest(double rate) {
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        return balance * (rate / 100);
    }
    
    public void applyInterest(double rate) {
        double interest = calculateInterest(rate);
        balance += interest;
        logTransaction("Interest applied: $" + interest + " at rate " + rate + "%, New balance: $" + balance);
    }
    
    public void closeAccount() {
        isActive = false;
        logTransaction("Account closed");
    }
    
    public void reactivateAccount() {
        isActive = true;
        logTransaction("Account reactivated");
    }
    
    private void logTransaction(String transaction) {
        transactionHistory.add(transaction);
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public int getTransactionCount() {
        return transactionHistory.size();
    }
    
    @Override
    public String toString() {
        return String.format("BankAccount{accountNumber='%s', holder='%s', balance=%.2f, active=%s}", 
                           accountNumber, accountHolderName, balance, isActive);
    }
}
