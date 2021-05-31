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
        ministryFrame = new MinistryFrame();
        studentFrame = new StudentFrame();

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
                if (acc.getType() == "ministry") {
                    loginFrame.setVisible(false);
//                    ministryFrame.show()
                } else if (acc.getType() == "student") {
                    loginFrame.setVisible(false);
//                  dentFrame.show();
                }
            }
        }
    }
}
