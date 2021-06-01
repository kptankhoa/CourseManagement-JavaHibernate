package ui.frame;

import model.AccountManager;
import ui.pane.changePwdOptionPane;
import ui.panel.AccountPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinistryFrame extends JFrame {
    public static final String ACTION_COMMAND = "ministry";

    private JButton accountPanelBtn;
    private JButton subjectPanelBtn;
    private JButton semesterPanelBtn;
    private JButton classPanelBtn;
    private JButton studentPanelBtn;
    private JButton registrationSessionPanelBtn;
    private JButton coursePanelBtn;
    private JButton registrationPanelBtn;

    private JButton changePwdBtn;
    private JButton logOutBtn;

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel topPanel;

    public MinistryFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.setTitle("Ministry");
        initComponents();
        initEvents();
    }

    private void initComponents() {
        ImageIcon imageIcon = new ImageIcon("ui/img/student.png");
        this.setIconImage(imageIcon.getImage());

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(50, 50));
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0xadb5bd));

        JLabel helloLabel = new JLabel("Hello, ".concat(AccountManager.getActiveAccount().getUsername()));
        helloLabel.setBounds(10, 10, 200, 30);
        helloLabel.setFont(new Font(helloLabel.getFont().getName(), Font.BOLD,17));

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBounds(670, 10, 100, 30);
        logOutBtn.setBackground(new Color(0x6c757d));
        logOutBtn.setFocusable(false);

        changePwdBtn = new JButton("Change Password");
        changePwdBtn.setBounds(450, 10, 200, 30);
        changePwdBtn.setBackground(new Color(0x6c757d));
        changePwdBtn.setFocusable(false);

        topPanel.add(helloLabel);
        topPanel.add(logOutBtn);
        topPanel.add(changePwdBtn);

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 200));
        leftPanel.setLayout(new GridLayout(0, 1, 5, 5));
        leftPanel.setBackground(new Color(0xadb5bd));

        accountPanelBtn = new JButton("Accounts");
        accountPanelBtn.setBackground(new Color(0x6c757d));
        accountPanelBtn.setFocusable(false);
        subjectPanelBtn = new JButton("Subjects");
        subjectPanelBtn.setBackground(new Color(0x6c757d));
        subjectPanelBtn.setFocusable(false);
        semesterPanelBtn = new JButton("Semester");
        semesterPanelBtn.setBackground(new Color(0x6c757d));
        semesterPanelBtn.setFocusable(false);
        classPanelBtn = new JButton("Classes");
        classPanelBtn.setBackground(new Color(0x6c757d));
        classPanelBtn.setFocusable(false);
        studentPanelBtn = new JButton("Students");
        studentPanelBtn.setBackground(new Color(0x6c757d));
        studentPanelBtn.setFocusable(false);
        coursePanelBtn= new JButton("Courses");
        coursePanelBtn.setBackground(new Color(0x6c757d));
        coursePanelBtn.setFocusable(false);
        registrationSessionPanelBtn = new JButton("Registration Session");
        registrationSessionPanelBtn.setBackground(new Color(0x6c757d));
        registrationSessionPanelBtn.setFocusable(false);
        registrationPanelBtn = new JButton("Registrations");
        registrationPanelBtn.setBackground(new Color(0x6c757d));
        registrationPanelBtn.setFocusable(false);

        leftPanel.add(accountPanelBtn);
        leftPanel.add(subjectPanelBtn);
        leftPanel.add(semesterPanelBtn);
        leftPanel.add(classPanelBtn);
        leftPanel.add(studentPanelBtn);
        leftPanel.add(coursePanelBtn);
        leftPanel.add(registrationSessionPanelBtn);
        leftPanel.add(registrationPanelBtn);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(0x495057));

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(leftPanel, BorderLayout.LINE_START);
        contentPane.add(rightPanel, BorderLayout.CENTER);
    }

    private void initEvents() {
        changePwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePwdOptionPane.display();
            }
        });

        accountPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccountPanel ap = new AccountPanel();
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(ap);
            }
        });
    }

    public void showGUI() {
        this.pack();
        this.setVisible(true);
    }


    public void addLogoutListener(ActionListener listener) {
        logOutBtn.addActionListener(listener);
        logOutBtn.setActionCommand(ACTION_COMMAND);
    }
}
