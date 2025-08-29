package io.github.acehoud01.budgettracker;

import java.time.LocalDate;

public abstract class Transaction {
    protected double amount;
    protected String description;
    protected LocalDate date;
    protected String category;

    public Transaction(double amount, String description, String category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = LocalDate.now();
    }

    public Transaction(double amount, String description, String category, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public abstract String getTransactionType();

    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | %s | %s",
                date, getTransactionType(), amount, category, description);
    }
}
