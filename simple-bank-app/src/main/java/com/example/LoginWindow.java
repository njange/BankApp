package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private Bank bank;
    private JTextField accountNumberField;
    private JPasswordField passwordField;

    public LoginWindow(Bank bank) {
        this.bank = bank;
        setTitle("Login");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        add(loginButton);
    }

    private void login() {
        String accountNumber = accountNumberField.getText();
        String password = new String(passwordField.getPassword());

        BankAccount account = bank.findAccount(accountNumber);
        if (account != null && account.verifyPassword(password)) {
            new AccountDetailsWindow(account, bank).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}