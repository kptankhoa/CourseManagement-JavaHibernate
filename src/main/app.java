package main;

import model.Account;
import model.AccountManager;

import static java.lang.System.out;


public class app {
    public static void main(String[] args) {
        Account acc = AccountManager.logIn(new Account("kptankhoa", "tankhoa99", "ministry"));
        if (acc != null) {
            out.println("logged in with: " + acc);
            out.println("Current: " + AccountManager.activeAccount);
        } else {
            out.println("log in failed");
        }

        acc = AccountManager.logIn(new Account("1712537", "1712537"));
        if (acc != null) {
            out.println("logged in with: " + acc);
            out.println("Current: " + AccountManager.activeAccount);
        } else {
            out.println("log in failed");
        }

        acc = AccountManager.logOut();
        if (acc != null) {
            out.println("Logged out from:" + acc);
            out.println("Current: " + AccountManager.activeAccount);
        } else out.println("no current active account/ log out failed");


    }
}

