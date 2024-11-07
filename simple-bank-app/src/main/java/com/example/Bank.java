package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
        loadAccounts();
    }

    public void createAccount(String accountNumber, String name, String password, double balance) {
        BankAccount account = new BankAccount(accountNumber, name, password, balance);
        this.accounts.add(account);
        saveAccounts();
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void saveAccounts() {  // Changed from private to public
        try (Writer writer = new FileWriter("accounts.json")) {
            Gson gson = new Gson();
            gson.toJson(accounts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccounts() {
        try (Reader reader = new FileReader("accounts.json")) {
            Gson gson = new Gson();
            accounts = gson.fromJson(reader, new TypeToken<List<BankAccount>>() {}.getType());
        } catch (IOException e) {
            accounts = new ArrayList<>();
        }
    }
}