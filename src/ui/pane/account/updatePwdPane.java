package ui.pane.account;

import dao.AccountDAO;
import model.Account;
import model.AccountManager;

import javax.swing.*;
import java.awt.*;

public class   updatePwdPane {
    public static void display(Account acc) {
        JTextField userField = new JTextField(acc.getUsername());
        userField.setEditable(false);
        JPasswordField pwdField = new JPasswordField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("New password:"));
        panel.add(pwdField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Update Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String newPwd = pwdField.getText();
            acc.setPassword(newPwd);
            AccountDAO.updateAccount(acc);
        } else {
            System.out.println("Cancelled");
        }
    }
}
