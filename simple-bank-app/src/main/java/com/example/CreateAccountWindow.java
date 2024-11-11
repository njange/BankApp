package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountWindow extends JFrame {
    private Bank bank;
    private JTextField accountNumberField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField balanceField;

    public CreateAccountWindow(Bank bank) {
        this.bank = bank;
        setTitle("Create Account");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

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

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        add(createButton);
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
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid balance amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}