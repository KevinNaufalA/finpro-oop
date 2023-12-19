package FinPROTest.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private AccountController controller;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginView(AccountController controller) {
        this.controller = controller;

        frame = new JFrame("Login/Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (controller.login(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    frame.dispose(); // Close the login window
                    openHomePage(username); // Open the home page
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });

        signupButton = new JButton("Signup");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (!controller.register(username)) {
                    JOptionPane.showMessageDialog(frame, "Account created successfully!");
                    controller.createAccount(username, password);
                } else {
                    JOptionPane.showMessageDialog(frame, "Account Already Exist");
                }
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);

        frame.setVisible(true);
    }

    private void openHomePage(String username) {
        Account account = controller.getAccount(username);

        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(300, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome, " + username + "!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        if (account != null) {
            JLabel infoLabel = new JLabel("Account Information:");
            panel.add(infoLabel, BorderLayout.CENTER);

            String[] accountInfo = {"Username: " + account.getUsername(), "Password: " + account.getPassword(), "Balance: " + Double.toString(account.getBalance())};
            JList<String> accountList = new JList<>(accountInfo);
            panel.add(new JScrollPane(accountList), BorderLayout.CENTER);
        } else {
            JLabel notFoundLabel = new JLabel("Account not found.");
            panel.add(notFoundLabel, BorderLayout.CENTER);
        }

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        JButton manageFinanceButton = new JButton("Manage Finance");
        manageFinanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open "Manage Finance" activity or perform actions
                JOptionPane.showMessageDialog(homeFrame, "Opening 'Manage Finance' activity...");
            }
        });

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = JOptionPane.showInputDialog(frame, "Enter Expense Description:");
                String amountStr = JOptionPane.showInputDialog(frame, "Enter Expense Amount:");
                double amount = Double.parseDouble(amountStr);
                String date = JOptionPane.showInputDialog(frame, "Enter Expense Date (YYYY-MM-DD):");

                account.addExpense(description, amount, date);

                JOptionPane.showMessageDialog(frame, "Expense added successfully!");
            }
        });

        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open "Add Income" activity or perform actions
                JOptionPane.showMessageDialog(homeFrame, "Opening 'Add Income' activity...");
            }
        });

        buttonPanel.add(manageFinanceButton);
        buttonPanel.add(addExpenseButton);
        buttonPanel.add(addIncomeButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        homeFrame.add(panel);
        homeFrame.setVisible(true);
    }

}
