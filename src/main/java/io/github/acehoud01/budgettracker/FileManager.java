package io.github.acehoud01.budgettracker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "transactions.txt";

    public void saveTransactions(List<Transaction> transactions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction transaction : transactions) {
                writer.printf("%s,%s,%.2f,%s,%s%n",
                        transaction.getDate(),
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getCategory(),
                        transaction.getDescription());
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String type = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String category = parts[3];
                    String description = parts[4];

                    if (type.equals("INCOME")) {
                        transactions.add(new Income(amount, description, category, date));
                    } else {
                        transactions.add(new Expense(amount, description, category, date));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, return empty list
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }
}
