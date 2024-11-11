package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountDetailsWindow extends JFrame {
    private BankAccount account;
    private Bank bank;
    private JLabel balanceLabel;

    public AccountDetailsWindow(BankAccount account, Bank bank) {
        this.account = account;
        this.bank = bank;
        setTitle("Account Details");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Account Number:"));
        add(new JLabel(account.getAccountNumber()));

        add(new JLabel("Name:"));
        add(new JLabel(account.getName()));

        add(new JLabel("Balance:"));
        balanceLabel = new JLabel(String.valueOf(account.checkBalance()));
        add(balanceLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeposit();
            }
        });
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performWithdraw();
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

    private void performDeposit() {
        String amountText = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountText);
            account.deposit(amount);
            bank.saveAccounts();
            balanceLabel.setText(String.valueOf(account.checkBalance()));
            JOptionPane.showMessageDialog(this, "Amount deposited successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performWithdraw() {
        String amountText = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountText);
            if (account.withdraw(amount)) {
                bank.saveAccounts();
                balanceLabel.setText(String.valueOf(account.checkBalance()));
                JOptionPane.showMessageDialog(this, "Amount withdrawn successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkBalance() {
        balanceLabel.setText(String.valueOf(account.checkBalance()));
        JOptionPane.showMessageDialog(this, "Current balance: " + account.checkBalance());
    }
}