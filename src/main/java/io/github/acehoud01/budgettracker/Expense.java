package io.github.acehoud01.budgettracker;

import java.time.LocalDate;

public class Expense extends Transaction{
    public Expense(double amount, String description, String category) {
        super(amount, description, category);
    }

    public Expense(double amount, String description, String category, LocalDate date) {
        super(amount, description, category, date);
    }

    @Override
    public String getTransactionType() {
        return "EXPENSE";
    }
}
