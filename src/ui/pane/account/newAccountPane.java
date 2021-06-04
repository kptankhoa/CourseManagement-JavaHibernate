package ui.pane.account;

import dao.AccountDAO;
import model.Account;

import javax.swing.*;
import java.awt.*;

public class newAccountPane {
    public static void display(JFrame parentFrame) {
        JTextField userField = new JTextField();
        JPasswordField pwdField = new JPasswordField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Your new password:"));
        panel.add(pwdField);
        int result = JOptionPane.showConfirmDialog(null, panel, "New Ministry",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String newUser = userField.getText();
            String newPwd = pwdField.getText();
            if ((newUser.equals("")) || (newPwd.equals(""))) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill all fields!");
            } else {
                Account newAcc = new Account(newUser, newPwd, "ministry");
                if (AccountDAO.addAccount(newAcc) != null) {
                    JOptionPane.showMessageDialog(parentFrame, "Added successfully!");
                } else {
                    System.out.println("Not added");
                }
            }
        } else {
            System.out.println("Cancelled");
        }
    }
}

