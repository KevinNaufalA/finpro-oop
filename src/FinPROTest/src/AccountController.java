package FinPROTest.src;

import java.util.ArrayList;

public class AccountController {
    private ArrayList<Account> accountList;

    public AccountController() {
        this.accountList = new ArrayList<>();
    }

    public void createAccount(String username, String password) {
        Account newAccount = new Account(username, password);
        accountList.add(newAccount);
    }

    public boolean login(String username, String password) {
        for (Account account : accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public boolean register(String username) {
        for (Account account : accountList) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public Account getAccount(String username) {
        for (Account account : accountList) {
            if (account.getUsername().equals(username)) {
                return account; // Return account if found
            }
        }
        return null; // Return null if not found
    }
}
