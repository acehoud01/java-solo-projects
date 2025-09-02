package io.github.acehoud01.budgettracker;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    private List<Transaction> transactions;
    private Scanner scanner;
    private FileManager fileManager;

    public BudgetTracker() {
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
        loadTransactions();
    }

    public void addIncome() {
        System.out.print("Enter income amount: R");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter category (Salary/Freelance/Other): ");
        String category = scanner.nextLine();

        Income income = new Income(amount, description, category);
        transactions.add(income);

        System.out.println("Income added successfully!\n");
        saveTransactions();
    }

    public void addExpense() {
        System.out.print("Enter expense amount: R");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter category (Food/Transport/Bills/Entertainment/Other): ");
        String category = scanner.nextLine();

        Expense expense = new Expense(amount, description, category);
        transactions.add(expense);

        System.out.println("Expense added successfully!\n");
        saveTransactions();
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.\n");
            return;
        }

        System.out.println("\n=== ALL TRANSACTIONS ===");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println();
    }

    public void monthlyReport() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to report.\n");
            return;
        }

        YearMonth currentMonth = YearMonth.now();
        double totalIncome = 0, totalExpenses = 0;

        System.out.printf("\n=== MONTHLY REPORT (%s) ===\n", currentMonth);

        for (Transaction transaction : transactions) {
            YearMonth transactionMonth = YearMonth.from(transaction.getDate());
            if (transactionMonth.equals(currentMonth)) {
                if (transaction instanceof Income) {
                    totalIncome += transaction.getAmount();
                } else {
                    totalExpenses += transaction.getAmount();
                }
            }
        }

        double netIncome = totalIncome - totalExpenses;

        System.out.printf("Total Income: R%.2f\n", totalIncome);
        System.out.printf("Total Expenses: R%.2f\n", totalExpenses);
        System.out.printf("Net Income: R%.2f\n", netIncome);

        if (netIncome > 0) {
            System.out.println("Great! You saved money this month!");
        } else if (netIncome < 0) {
            System.out.println("Warning: You spent more than you earned!");
        } else {
            System.out.println("You broke even this month.");
        }
        System.out.println();
    }

    private void loadTransactions() {
        transactions = fileManager.loadTransactions();
    }

    private void saveTransactions() {
        fileManager.saveTransactions(transactions);
    }

    public void showMenu() {
        while (true) {
            System.out.println("=== BUDGET TRACKER MENU ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Monthly Report");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addIncome(); break;
                case 2: addExpense(); break;
                case 3: viewTransactions(); break;
                case 4: monthlyReport(); break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }
}
