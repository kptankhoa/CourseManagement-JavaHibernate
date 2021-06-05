package ui.panel;

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
import java.util.ArrayList;

public class RegisteredCoursesPanel extends JPanel {
    private final JTable courseTable;
    private final JPanel mainPanel;
    ArrayList<Registration> registeredByStudent;
    private ArrayList<Course> registeredCourses;
    private Student activeStudent;
    private Semester activeSemester = ActiveSemester.getActiveSemester();

    public RegisteredCoursesPanel() {
        String[] columns = {"Course ID", "Subject", "Lecturer", "Room", "Study Time", "Registration Time", "Unregister"};
        courseTable = new JTable();
        courseTable.setModel(new DefaultTableModel(columns, 0));
        courseTable.setRowHeight(30);
        courseTable.setBackground(new Color(0xced4da));
        courseTable.getTableHeader().setOpaque(false);
        courseTable.getTableHeader().setBackground(new Color(0x495057));
        getCourseList();
        courseTable.getColumn("Unregister").setCellRenderer(new ButtonRenderer());
        courseTable.getColumn("Unregister").setCellEditor(new ButtonEditor(new JCheckBox()));
        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 6) {
                        removeRegistration(registeredByStudent.get(row));
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

    private void removeRegistration(Registration registration) {
        int check = JOptionPane.showConfirmDialog(
                this,
                "Are you sure want to unregister this course?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (check == JOptionPane.OK_OPTION) {
            if (TimeValidateUtil.isDateValid(activeSemester)) {
                Registration result = RegistrationDAO.deleteRegistration(registration);
                if (result != null) {
                    getCourseList();
                    JOptionPane.showMessageDialog(this, "Unregistered successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid time for unregistering!");
            }
        }
    }

    private void getCourseList() {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0);
        registeredCourses = new ArrayList<>();
        activeStudent = StudentDAO.getStudentById(AccountManager.getActiveAccount().getUsername());
        registeredByStudent = RegistrationDAO.getRegistrationBySemesterIdNStudentId(activeSemester.getSemesterId(), activeStudent.getStudentId());
        if (!registeredByStudent.isEmpty()) {
            for (Registration r : registeredByStudent) {
                Course course = r.getPk().getCourse();
                registeredCourses.add(course);
                String id = course.getCourseId();
                String subject = course.getSubject().getName();
                String lecturer = course.getLecturer();
                String room = course.getRoom();
                String shift = course.getShift().getTime();
                String rTime = r.getRegistrationTime().toString();
                Object[] row = {id, subject, lecturer, room, shift, rTime, "Unregister"};
                model.addRow(row);
            }
        }
    }
}
