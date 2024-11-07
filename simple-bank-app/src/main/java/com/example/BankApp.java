package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankApp extends JFrame {
    private Bank bank;
    private JTextField accountNumberField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField balanceField;

    public BankApp() {
        bank = new Bank();
        setTitle("Simple Bank App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Balance:"));
        balanceField = new JTextField();
        add(balanceField);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        add(createAccountButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        add(withdrawButton);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        add(checkBalanceButton);
    }

    private void createAccount() {
        String accountNumber = accountNumberField.getText();
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());
        String balanceText = balanceField.getText();

        if (accountNumber.isEmpty() || name.isEmpty() || password.isEmpty() || balanceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double balance = Double.parseDouble(balanceText);
            bank.createAccount(accountNumber, name, password, balance);
            JOptionPane.showMessageDialog(this, "Account created successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid balance amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deposit() {
        String accountNumber = accountNumberField.getText();
        String amountText = balanceField.getText();

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Account number and amount must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            BankAccount account = bank.findAccount(accountNumber);
            if (account != null) {
                account.deposit(amount);
                bank.saveAccounts();
                JOptionPane.showMessageDialog(this, "Amount deposited successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw() {
        String accountNumber = accountNumberField.getText();
        String amountText = balanceField.getText();

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Account number and amount must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            BankAccount account = bank.findAccount(accountNumber);
            if (account != null) {
                if (account.withdraw(amount)) {
                    bank.saveAccounts();
                    JOptionPane.showMessageDialog(this, "Amount withdrawn successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkBalance() {
        String accountNumber = accountNumberField.getText();

        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Account number must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            double balance = account.checkBalance();
            JOptionPane.showMessageDialog(this, "Current balance: " + balance);
        } else {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankApp().setVisible(true);
            }
        });
    }
}