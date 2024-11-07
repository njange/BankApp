package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BankAccount {
    private String accountNumber;
    private String name;
    private double balance;
    private String hashedPassword;

    public BankAccount(String accountNumber, String name, String password, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.hashedPassword = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}