package controller;

import model.Account;
import model.AccountManager;
import ui.frame.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController {
    private LoginFrame loginFrame;
    private MinistryFrame ministryFrame;
    private StudentFrame studentFrame;

    public AppController() {
        loginFrame = new LoginFrame();
//        studentFrame = new StudentFrame();
        loginFrame.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginFrame.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Account logInAccount = loginFrame.getAccount();
            Account acc = AccountManager.logIn(logInAccount);
            if(acc==null){
                loginFrame.showMessage("Wrong username or password!");
            } else {
                if (acc.getType().equals("ministry")) {
                    loginFrame.setVisible(false);
                    ministryFrame = new MinistryFrame();
                    ministryFrame.addLogoutListener(new LogoutListener());
                    ministryFrame.showGUI();
                } else if (acc.getType().equals("student")) {
                    loginFrame.setVisible(false);
//                  studentFrame.show();
                }
            }
        }
    }

    class LogoutListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals(ministryFrame.ACTION_COMMAND)) {
                AccountManager.logOut();
                ministryFrame.dispose();
            }
//            else if(command.equals(studentFrame.ACTION_COMMAND)) {
//                studentFrame.hideGUI();
//            }
            loginFrame.setVisible(true);
        }

    }
}
