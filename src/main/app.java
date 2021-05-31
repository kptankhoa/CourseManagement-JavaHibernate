package main;

import controller.AppController;
import dao.*;
import model.*;

public class app {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AppController appController = new AppController();
                appController.showLoginView();
            }
        });

    }
}

