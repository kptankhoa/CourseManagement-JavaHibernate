package main;

import controller.AppController;
import model.*;
import ui.frame.MinistryFrame;
import ui.panel.AccountPanel;

public class app {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                AppController appController = new AppController();
//                appController.showLoginView();
                AccountManager.logIn(new Account("admin", "admin"));
                MinistryFrame mf = new MinistryFrame();
                mf.showGUI();

            }
        });

    }
}

