package main;

import controller.AppController;

public class app {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AppController();
            }
        });

    }
}

