package ui.pane;

import model.AccountManager;

import javax.swing.*;
import java.awt.*;

public class changePwdOptionPane {
    public static void display() {
        JTextField userField = new JTextField(AccountManager.getActiveAccount().getUsername());
        userField.setEditable(false);
        JPasswordField pwdField = new JPasswordField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Your new password:"));
        panel.add(pwdField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Change Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String newPwd = pwdField.getText();
            AccountManager.changePassword(newPwd);
        } else {
            System.out.println("Cancelled");
        }
    }
}
