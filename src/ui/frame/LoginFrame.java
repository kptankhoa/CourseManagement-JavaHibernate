package ui.frame;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;

    public LoginFrame() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initComponents() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(380, 250);
        loginPanel.setBackground(new Color(0xadb5bd));
        loginPanel.setLayout(null);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 50, 80, 25);
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 90, 80, 25);
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 160, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 160, 25);
        loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0x6c757d));
        loginBtn.setFocusable(false);
        loginBtn.setBounds(70, 130, 240, 30);


        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginBtn);

        ImageIcon imageIcon = new ImageIcon("ui/img/student.png");
        this.setIconImage(imageIcon.getImage());
        this.add(loginPanel);
        this.setTitle("Login");
        this.pack();
        this.setSize(380,250);
        this.setResizable(false);
    }

    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }
    public void addLoginKeyListener(ActionListener listener) {
        passwordField.addActionListener(listener);
    }

    public Account getAccount() {
        return new Account(usernameField.getText(), String.copyValueOf(passwordField.getPassword()));
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
