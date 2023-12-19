package FinPROTest.src;

import javax.swing.*;

public class LoginManager {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AccountController controller = new AccountController();
                new LoginView(controller);
            }
        });
    }
}
