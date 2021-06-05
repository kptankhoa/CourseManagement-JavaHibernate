package ui.panel;

import dao.CourseDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StudentRegistrationPanel extends JPanel {
    private final JTable studentTable;
    private final JPanel mainPanel;
    private ArrayList<Student> students;
    private ArrayList<Course> activeCourses;
    private Semester activeSemester = ActiveSemester.getActiveSemester();

    public StudentRegistrationPanel() {
        ArrayList<String> columns = new ArrayList<>(Arrays.asList("Student ID", "Student Name"));
        activeCourses = CourseDAO.getCoursesBySemesterId(activeSemester.getSemesterId());
        activeCourses.forEach(course -> {
            columns.add(course.getSubject().getName());
        });
        studentTable = new JTable();
        studentTable.setModel(new DefaultTableModel(columns.toArray(), 0));
        studentTable.setRowHeight(30);
        studentTable.setBackground(new Color(0xced4da));
        studentTable.getTableHeader().setOpaque(false);
        studentTable.getTableHeader().setBackground(new Color(0x495057));
        getStudentTableContent();
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
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);
    }

    private void getStudentTableContent() {
        students = StudentDAO.getAllStudents();
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        if (!students.isEmpty()) {
            for (Student student : students) {
                ArrayList<Registration> registrationsByStudent = RegistrationDAO.getRegistrationsByStudentId(student.getStudentId());
                ArrayList<Course> registeredCourses = new ArrayList<>();
                registrationsByStudent.forEach(registration -> registeredCourses.add(registration.getPk().getCourse()));
                ArrayList<String> row = new ArrayList<>();
                row.add(student.getStudentId());
                row.add(student.getName());
                for (Course course : activeCourses) {
                    if (registeredCourses.contains(course)) {
                        row.add("x");
                    } else {
                        row.add("");
                    }
                }
                model.addRow(row.toArray());
            }
        }

    }

}
