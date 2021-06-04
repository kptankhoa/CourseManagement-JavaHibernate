package ui.frame;

import model.AccountManager;
import model.ActiveSemester;
import ui.pane.account.changePwdOptionPane;
import ui.panel.*;

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
    private JButton studentRegistrationPanelBtn;
    private JButton courseRegistrationPanelBtn;

    private JButton changePwdBtn;
    private JButton logOutBtn;

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel topPanel;

    public MinistryFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 600));
        this.setTitle("Ministry");
        initComponents();
        initEvents(this);

        ActiveSemester.getActiveSemesterFromDB();
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
        coursePanelBtn = new JButton("Courses");
        coursePanelBtn.setBackground(new Color(0x6c757d));
        coursePanelBtn.setFocusable(false);
        registrationSessionPanelBtn = new JButton("Registration Sessions");
        registrationSessionPanelBtn.setBackground(new Color(0x6c757d));
        registrationSessionPanelBtn.setFocusable(false);
        studentRegistrationPanelBtn = new JButton("Student Registrations");
        studentRegistrationPanelBtn.setBackground(new Color(0x6c757d));
        studentRegistrationPanelBtn.setFocusable(false);
        courseRegistrationPanelBtn = new JButton("Course Registrations");
        courseRegistrationPanelBtn.setBackground(new Color(0x6c757d));
        courseRegistrationPanelBtn.setFocusable(false);

        leftPanel.add(accountPanelBtn);
        leftPanel.add(subjectPanelBtn);
        leftPanel.add(semesterPanelBtn);
        leftPanel.add(classPanelBtn);
        leftPanel.add(studentPanelBtn);
        leftPanel.add(coursePanelBtn);
        leftPanel.add(registrationSessionPanelBtn);
        leftPanel.add(studentRegistrationPanelBtn);
        leftPanel.add(courseRegistrationPanelBtn);

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

        accountPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AccountPanel ap = new AccountPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(ap);
            }
        });

        subjectPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SubjectPanel sp = new SubjectPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(sp);
            }
        });

        semesterPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SemesterPanel sp = new SemesterPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(sp);
            }
        });

        classPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClassPanel cp = new ClassPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(cp);
            }
        });

        coursePanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CoursePanel cp = new CoursePanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(cp);
            }
        });
        studentPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StudentPanel sp = new StudentPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(sp);
            }
        });
        registrationSessionPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegistrationSessionPanel rsp = new RegistrationSessionPanel(containerFrame);
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(rsp);
            }
        });
        courseRegistrationPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CourseRegistrationPanel crp = new CourseRegistrationPanel();
                rightPanel.removeAll();
                rightPanel.revalidate();
                rightPanel.repaint();
                rightPanel.add(crp);
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
