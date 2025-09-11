package io.github.acehoud01.banking;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String clientId;
    private String name;
    private String email;
    private String phone;
    private List<Account> accounts;

    private Client(String clientId, String name, String email, String phone) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return String.format("Customer: %s | %s | %s | Accounts: %d",
                clientId, name, email, accounts.size());
    }
}

