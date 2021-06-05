package ui.panel;

import dao.CourseDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import model.*;
import ui.util.ButtonEditor;
import ui.util.ButtonRenderer;
import utils.TimeValidateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AvailableCoursesPanel extends JPanel {
    private final JTable courseTable;
    private final JPanel mainPanel;
    ArrayList<Registration> registeredByStudent;
    ArrayList<Shift> busyShifts;
    private ArrayList<Course> availableCourses;
    private Student activeStudent;
    private Semester activeSemester = ActiveSemester.getActiveSemester();

    public AvailableCoursesPanel() {
        String[] columns = {"Course ID", "Subject", "Lecturer", "Room", "Study Time", "Register"};
        courseTable = new JTable();
        courseTable.setModel(new DefaultTableModel(columns, 0));
        courseTable.setRowHeight(30);
        courseTable.setBackground(new Color(0xced4da));
        courseTable.getTableHeader().setOpaque(false);
        courseTable.getTableHeader().setBackground(new Color(0x495057));
        getCourseList();
        courseTable.getColumn("Register").setCellRenderer(new ButtonRenderer());
        courseTable.getColumn("Register").setCellEditor(new ButtonEditor(new JCheckBox()));
        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 5) {
                        registerCourse(availableCourses.get(row));
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(courseTable);
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
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);
    }

    private void registerCourse(Course course) {
        int check = JOptionPane.showConfirmDialog(
                this,
                "Are you sure want to register this course?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (check == JOptionPane.OK_OPTION) {
            if (registeredByStudent.size() == 8) {
                JOptionPane.showMessageDialog(
                        this,
                        "You cannot register more than 8 courses per semester!");
                return;
            }
            if (!TimeValidateUtil.isDateValid(activeSemester)) {
                JOptionPane.showMessageDialog(this, "Invalid time for registering!");
                return;
            }
            if (busyShifts.contains(course.getShift())) {
                JOptionPane.showMessageDialog(this, "Invalid study shift!");
                return;
            }

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Registration newRegistration = new Registration(activeStudent, course, timestamp);
            Registration result = RegistrationDAO.addRegistration(newRegistration);
            if (result != null) {
                getCourseList();
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register!");
            }
        }
    }

    private void getCourseList() {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0);
        availableCourses = new ArrayList<>();
        busyShifts = new ArrayList<>();
        ArrayList<Course> registeredCourses = new ArrayList<>();
        activeStudent = StudentDAO.getStudentById(AccountManager.getActiveAccount().getUsername());
        registeredByStudent = RegistrationDAO.getRegistrationBySemesterIdNStudentId(activeSemester.getSemesterId(), activeStudent.getStudentId());
        registeredByStudent.forEach(r -> {
            registeredCourses.add(r.getPk().getCourse());
            busyShifts.add(r.getPk().getCourse().getShift());
        });
        ArrayList<Course> activeCourses = CourseDAO.getCoursesBySemesterId(activeSemester.getSemesterId());
        if (!activeCourses.isEmpty()) {
            for (Course course : activeCourses) {
                if (!registeredCourses.contains(course)) {
                    availableCourses.add(course);
                    String id = course.getCourseId();
                    String subject = course.getSubject().getName();
                    String lecturer = course.getLecturer();
                    String room = course.getRoom();
                    String shift = course.getShift().getTime();
                    Object[] row = {id, subject, lecturer, room, shift, "Register"};
                    model.addRow(row);
                }
            }
        }
    }

}
