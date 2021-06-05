package ui.panel;

import dao.CourseDAO;
import dao.RegistrationDAO;
import model.ActiveSemester;
import model.Course;
import model.Registration;
import model.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseRegistrationPanel extends JPanel {
    private final JTable studentTable;
    private final JPanel mainPanel;
    private JComboBox courseCombo;
    private JButton chooseBtn;
    private ArrayList<Course> activeCourses;
    private Semester activeSemester = ActiveSemester.getActiveSemester();
    private JLabel courseIdLabel;
    private JLabel subjectIdLabel;
    private JLabel subjectNameLabel;
    private JLabel lecturerLabel;
    private JLabel studyTimeLabel;


    public CourseRegistrationPanel() {
        String[] columns = {"Student ID", "Name", "Registration Time"};
        studentTable = new JTable();
        studentTable.setModel(new DefaultTableModel(columns, 0));
        studentTable.setRowHeight(30);
        studentTable.setBackground(new Color(0xced4da));
        studentTable.getTableHeader().setOpaque(false);
        studentTable.getTableHeader().setBackground(new Color(0x495057));
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xadb5bd), 10));
        scrollPane.getViewport().setBackground(new Color(0xadb5bd));
        scrollPane.setColumnHeader(new JViewport() {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = 30;
                return d;
            }
        });
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(200, 150));
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0xadb5bd));
        activeCourses = CourseDAO.getCoursesBySemesterId(activeSemester.getSemesterId());
        ArrayList<String> courseStrings = new ArrayList<>();
        activeCourses.forEach(course -> {
            courseStrings.add(" " + course.getSubject().getName());
        });
        courseCombo = new JComboBox(courseStrings.toArray());
        courseCombo.setBounds(10, 0, 300, 30);
        courseCombo.setBackground(new Color(0x6c757d));
        courseCombo.setFocusable(false);
        chooseBtn = new JButton("Select");
        chooseBtn.setBounds(320, 0, 100, 30);
        chooseBtn.setBackground(new Color(0x6c757d));
        chooseBtn.setFocusable(false);
        chooseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateBySelect();
            }
        });
        Font f = new JLabel().getFont();
        JLabel courseId = new JLabel("Course ID:");
        courseIdLabel = new JLabel();
        JLabel subjectId = new JLabel("Subject ID:");
        subjectIdLabel = new JLabel();
        JLabel subjectName = new JLabel("Subject Name:");
        subjectNameLabel = new JLabel();
        JLabel lecturer = new JLabel("Lecturer:");
        lecturerLabel = new JLabel();
        JLabel studyTime = new JLabel("Study time:");
        studyTimeLabel = new JLabel();
        courseIdLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        subjectIdLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        subjectNameLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        lecturerLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        studyTimeLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        courseId.setBounds(10, 40, 150, 30);
        courseIdLabel.setBounds(110, 40, 200, 30);
        subjectId.setBounds(10, 80, 150, 30);
        subjectIdLabel.setBounds(110, 80, 200, 30);
        subjectName.setBounds(300, 80, 150, 30);
        subjectNameLabel.setBounds(430, 80, 200, 30);
        lecturer.setBounds(10, 120, 150, 30);
        lecturerLabel.setBounds(110, 120, 200, 30);
        studyTime.setBounds(300, 120, 150, 30);
        studyTimeLabel.setBounds(430, 120, 200, 30);

        topPanel.add(courseCombo);
        topPanel.add(chooseBtn);
        topPanel.add(courseId);
        topPanel.add(courseIdLabel);
        topPanel.add(subjectId);
        topPanel.add(subjectIdLabel);
        topPanel.add(subjectName);
        topPanel.add(subjectNameLabel);
        topPanel.add(lecturer);
        topPanel.add(lecturerLabel);
        topPanel.add(studyTime);
        topPanel.add(studyTimeLabel);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);
    }

    private void updateTable(ArrayList<Registration> registrations) {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        if (!registrations.isEmpty()) {
            for (Registration registration : registrations) {
                String studentId = registration.getPk().getStudent().getStudentId();
                String name = registration.getPk().getStudent().getName();
                String time = registration.getRegistrationTime().toString();
                String[] row = {studentId, name, time};
                model.addRow(row);

            }
        }
    }

    private void updateBySelect() {
        Course selectedCourse = activeCourses.get(courseCombo.getSelectedIndex());
        courseIdLabel.setText(selectedCourse.getCourseId());
        subjectIdLabel.setText(selectedCourse.getSubject().getSubjectId());
        subjectNameLabel.setText(selectedCourse.getSubject().getName());
        lecturerLabel.setText(selectedCourse.getLecturer());
        studyTimeLabel.setText(selectedCourse.getShift().getTime());
        String selectedCourseId = selectedCourse.getCourseId();
        ArrayList<Registration> registrations = RegistrationDAO.getRegistrationByCourseId(selectedCourseId);
        updateTable(registrations);
    }
}
