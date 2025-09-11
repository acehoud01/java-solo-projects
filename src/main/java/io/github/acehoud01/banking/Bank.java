package io.github.acehoud01.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private String bankName;
    private List<Client> clients;
    private List<Account> accounts;
    private Scanner scanner;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.clients = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter initial deposit: R");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine();

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative.\n");
            return;
        }

        // Create client if it doesn't exist
        String clientId = "CLI" + (clients.size() + 1001);
        Client client = new Client(clientId, name, email, phone);

        // Create account
        Account account = new Account(name, initialDeposit);

        client.addAccount(account);
        clients.add(client);
        accounts.add(account);

        System.out.print("Account created successfully!\n");
        System.out.printf("Customer ID: %s\n", clientId);
        System.out.printf("Account Number: %s\n", account.getAccountNumber());
        System.out.printf("Initial Balance: $%.2f\n\n", initialDeposit);
    }

    public void deposit() {
        Account account = findAccountByInput();

        if (account == null) return;

        System.out.print("Enter deposit amount: R");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            account.deposit(amount);
            System.out.printf("Deposited R%.2f successfully.\n", amount);
            System.out.printf("New balance: R%.2f\n\n", account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public void withdraw() {
        Account account = findAccountByInput();
        if (account == null) return;

        System.out.printf("Current balance: R%.2f\n", account.getBalance());
        System.out.print("Enter withdrawal amount: R");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            account.withdraw(amount);
            System.out.printf("Withdrew R%.2f successfully.\n", amount);
            System.out.printf("New balance: R%.2f\n\n", account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public void transfer() {
        System.out.print("Enter source account number: ");
        String sourceAccountNumber = scanner.nextLine();
        Account sourceAccount = findAccount(sourceAccountNumber);

        if (sourceAccount == null) {
            System.out.println("Source account not found.\n");
            return;
        }

        System.out.print("Enter destination account number: ");
        String destAccountNumber = scanner.nextLine();
        Account destAccount = findAccount(destAccountNumber);

        if (destAccount == null) {
            System.out.println("Destination account not found.\n");
            return;
        }

        System.out.printf("Source account balance: R%.2f\n", sourceAccount.getBalance());
        System.out.print("Enter transfer amount: R");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            sourceAccount.transfer(destAccount, amount);
            System.out.printf("Transferred R%.2f successfully.\n", amount);
            System.out.printf("Source account new balance: R%.2f\n", sourceAccount.getBalance());
            System.out.printf("Destination account new balance: R%.2f\n\n", destAccount.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public void checkBalance() {
        Account account = findAccountByInput();
        if (account == null) return;

        System.out.printf("Account: %s\n", account.getAccountNumber());
        System.out.printf("Account Holder: %s\n", account.getAccountHolderName());
        System.out.printf("Current Balance: R%.2f\n\n", account.getBalance());
    }

    public void printStatement() {
        Account account = findAccountByInput();
        if (account == null) return;

        account.printStatement();
    }

    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.\n");
            return;
        }

        System.out.println("\n=== ALL ACCOUNTS ===");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println();
    }

    private Account findAccountByInput() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found.\n");
        }
        return account;
    }

    private Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void showMenu() {
        System.out.printf("Welcome to %s!\n\n", bankName);

        while (true) {
            System.out.printf("=== %s BANKING SYSTEM ===\n", bankName.toUpperCase());
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Print Statement");
            System.out.println("7. View All Accounts");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: transfer(); break;
                case 5: checkBalance(); break;
                case 6: printStatement(); break;
                case 7: viewAllAccounts(); break;
                case 8:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

}
