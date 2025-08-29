package io.github.acehoud01.budgettracker;

import java.time.LocalDate;

public class Income extends Transaction{
    public Income(double amount, String description, String category) {
        super(amount, description, category);
    }

    public Income(double amount, String description, String category, LocalDate date) {
        super(amount, description, category, date);
    }

    @Override
    public String getTransactionType() {
        return "INCOME";
    }
}
