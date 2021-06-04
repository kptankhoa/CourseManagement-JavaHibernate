package controller;

import model.Account;
import model.AccountManager;
import ui.frame.LoginFrame;
import ui.frame.MinistryFrame;
import ui.frame.StudentFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController {
    private LoginFrame loginFrame;
    private MinistryFrame ministryFrame;
    private StudentFrame studentFrame;

    public AppController() {
        loginFrame = new LoginFrame();
        loginFrame.addLoginListener(new LoginListener());
        loginFrame.addLoginKeyListener(loginAction);
        loginFrame.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            verifyLogin();
        }
    }

    Action loginAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            verifyLogin();
        }
    };

    public void verifyLogin(){
        Account logInAccount = loginFrame.getAccount();
        Account acc = AccountManager.logIn(logInAccount);
        if (acc == null) {
            loginFrame.showMessage("Wrong username or password!");
        } else {
            if (acc.getType().equals("ministry")) {
                loginFrame.setVisible(false);
                ministryFrame = new MinistryFrame();
                ministryFrame.addLogoutListener(new LogoutListener());
            } else if (acc.getType().equals("student")) {
                loginFrame.setVisible(false);
                studentFrame = new StudentFrame();
                studentFrame.addLogoutListener(new LogoutListener());
            }
        }
    }
    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals(ministryFrame.ACTION_COMMAND)) {
                AccountManager.logOut();
                ministryFrame.dispose();
            }
            else if(command.equals(studentFrame.ACTION_COMMAND)) {
                AccountManager.logOut();
                studentFrame.dispose();
            }
            loginFrame.setVisible(true);
        }
    }
}
