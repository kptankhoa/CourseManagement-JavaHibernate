package ui.frame;

import model.AccountManager;
import model.ActiveSemester;
import ui.pane.account.changePwdOptionPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFrame extends JFrame {
    public static final String ACTION_COMMAND = "student";

    private JButton registerBtn;
    private JButton registrationBtn;

    private JButton changePwdBtn;
    private JButton logOutBtn;

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel topPanel;

    public StudentFrame(){
        ActiveSemester.getActiveSemesterFromDB();
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 600));
        this.setTitle("Student");
        initComponents();
        initEvents(this);
        this.pack();
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    private void initComponents() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(50, 50));
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0xadb5bd));

        JLabel helloLabel = new JLabel("Hello, ".concat(AccountManager.getActiveAccount().getUsername()));
        helloLabel.setBounds(10, 10, 200, 30);
        helloLabel.setFont(new Font(helloLabel.getFont().getName(), Font.BOLD, 17));

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBounds(870, 10, 100, 30);
        logOutBtn.setBackground(new Color(0x6c757d));
        logOutBtn.setFocusable(false);

        changePwdBtn = new JButton("Change Password");
        changePwdBtn.setBounds(650, 10, 200, 30);
        changePwdBtn.setBackground(new Color(0x6c757d));
        changePwdBtn.setFocusable(false);

        topPanel.add(helloLabel);
        topPanel.add(logOutBtn);
        topPanel.add(changePwdBtn);

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 200));
        leftPanel.setLayout(new GridLayout(0, 1, 5, 5));
        leftPanel.setBackground(new Color(0xadb5bd));

        registerBtn = new JButton("Register Courses");
        registerBtn.setBackground(new Color(0x6c757d));
        registerBtn.setFocusable(false);
        registrationBtn = new JButton("Your Registrations");
        registrationBtn.setBackground(new Color(0x6c757d));
        registrationBtn.setFocusable(false);

        leftPanel.add(registerBtn);
        leftPanel.add(registrationBtn);
        leftPanel.add(new JLabel());
        leftPanel.add(new JLabel());
        leftPanel.add(new JLabel());

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(0x495057));

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(leftPanel, BorderLayout.LINE_START);
        contentPane.add(rightPanel, BorderLayout.CENTER);
    }

    private void initEvents(JFrame containerFrame) {
        changePwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePwdOptionPane.display(containerFrame);
            }
        });
    }

    public void addLogoutListener(ActionListener listener) {
        logOutBtn.addActionListener(listener);
        logOutBtn.setActionCommand(ACTION_COMMAND);
    }
}
