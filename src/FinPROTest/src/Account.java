package FinPROTest.src;

import java.io.FileWriter;
import java.io.IOException;

public class Account {
    private String username;
    private String password;
    private double balance;
    private double expense;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.expense = 0;
    }

    // Add these methods to the Account class
    public void addExpense(String description, double amount, String date) {
        this.expense += amount;

        // Here, you can store the expense details in a data structure or CSV file.
        // For simplicity, let's assume you are using a StringBuilder to store data.
        StringBuilder expenseData = new StringBuilder();
        expenseData.append(description).append(",").append(amount).append(",").append(date).append("\n");

        // Save the expense data to a CSV file named Username.csv
        saveToCSV(expenseData.toString(), getUsername() + ".csv");
    }

    private void saveToCSV(String data, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public double getExpense() {
        return expense;
    }
    public double getBalance(){
        return balance;
    }
}
