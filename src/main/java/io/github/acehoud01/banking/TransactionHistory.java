package io.github.acehoud01.banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    private TransactionType type;
    private double amount;
    private double balanceAfter;
    private LocalDateTime timestamp;
    private String description;

    public TransactionHistory(TransactionType type, double amount, double balanceAfter, String description) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public double getBalanceAfter() {
        return balanceAfter;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: R%.2f (Balance: R%.2f) - %s",
                timestamp.format(formatter), type, amount, balanceAfter, description);
    }
}
