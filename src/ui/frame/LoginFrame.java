package ui.frame;

import model.Account;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1l;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnLogin;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        usernameLabel = new JLabel("UserName");
        passwordLabel = new JLabel("Password");
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        btnLogin = new JButton("Login");
//		btnLogin.addActionListener(this);

        SpringLayout layout = new SpringLayout();
        JPanel loginPanel = new JPanel();
        loginPanel.setSize(400, 300);
        loginPanel.setLayout(layout);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(btnLogin);

        layout.putConstraint(SpringLayout.WEST, usernameLabel, 20, SpringLayout.WEST, loginPanel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 80, SpringLayout.NORTH, loginPanel);
        layout.putConstraint(SpringLayout.WEST, passwordLabel, 20, SpringLayout.WEST, loginPanel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 105, SpringLayout.NORTH, loginPanel);
        layout.putConstraint(SpringLayout.WEST, usernameField, 80, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, 80, SpringLayout.NORTH, loginPanel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 80, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 105, SpringLayout.NORTH, loginPanel);
        layout.putConstraint(SpringLayout.WEST, btnLogin, 80, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, btnLogin, 130, SpringLayout.NORTH, loginPanel);

        this.add(loginPanel);
        this.pack();
        this.setTitle("Login");
        this.setSize(400,300);
        this.setResizable(false);
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public Account getAccount() {
        return new Account(usernameField.getText(), String.copyValueOf(passwordField.getPassword()));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
