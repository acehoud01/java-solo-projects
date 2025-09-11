package io.github.acehoud01.banking;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int nextAccountNumber = 1001;

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<TransactionHistory> transactionHistory;

    public Account(String accountHolderName, double initialDeposit) {
        this.accountNumber = String.valueOf(nextAccountNumber++);
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();

        if (initialDeposit > 0) {
            addTransaction(TransactionType.DEPOSIT, initialDeposit, "Initial deposit");
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }

        balance += amount;
        addTransaction(TransactionType.DEPOSIT, amount, "Cash deposit");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balance -= amount;
        addTransaction(TransactionType.WITHDRAWAL, amount, "Cash withdrawal");
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Deduct from this account
        balance -= amount;
        addTransaction(TransactionType.TRANSFER_OUT, amount, "Transfer to account " + destinationAccount.getAccountNumber());

        // Add to destination account
        destinationAccount.balance += amount;
        destinationAccount.addTransaction(TransactionType.TRANSFER_IN, amount, "Transfer from account " + this.accountNumber);
    }

    public void addTransaction(TransactionType type, double amount, String description) {
        transactionHistory.add(new TransactionHistory(type, amount, balance, description));
    }

    public void printStatement() {
        System.out.println("\n=== ACCOUNT STATEMENT ===");
        System.out.printf("Account Number: %s\n", accountNumber);
        System.out.printf("Account Holder: %s\n", accountHolderName);
        System.out.printf("Current Balance: R%.2f\n\n", balance);

        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction found.");
        } else {
            System.out.println("-".repeat(80));
            for (TransactionHistory transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<TransactionHistory> getTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public String toString() {
        return String.format("Account: %s | %s | Balance: R%.2f",
                accountNumber, accountHolderName, balance);
    }
}
