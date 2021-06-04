package ui.pane.course;

import dao.*;
import model.Course;
import model.Semester;
import model.Shift;
import model.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class newCoursePane {
    public static void display(JFrame parentFrame) {
        JTextField courseIdField = new JTextField();

        ArrayList<Subject> subjects = SubjectDAO.getAllSubjects();
        ArrayList<String> subjectStrings = new ArrayList<>();
        subjects.forEach(subject -> subjectStrings.add(subject.getName()));
        JComboBox subjectCombo = new JComboBox(subjectStrings.toArray());

        ArrayList<Semester> semesters = SemesterDAO.getAllSemesters();
        ArrayList<String> semesterStrings = new ArrayList<>();
        semesters.forEach(semester -> semesterStrings.add(semester.getName()+" - "+semester.getSchoolYear()));
        JComboBox semesterCombo = new JComboBox(semesterStrings.toArray());

        JTextField lecturerField = new JTextField();
        JTextField roomField = new JTextField();

        ArrayList<Shift> shifts = ShiftDAO.getAllShifts();
        ArrayList<String> shiftStrings = new ArrayList<>();
        shifts.forEach(shift -> shiftStrings.add(shift.getTime()));
        JComboBox shiftCombo = new JComboBox(shiftStrings.toArray());

        JTextField slotsField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("New Course ID:"));
        panel.add(courseIdField);
        panel.add(new JLabel("Choose subject:"));
        panel.add(subjectCombo);
        panel.add(new JLabel("Choose semester:"));
        panel.add(semesterCombo);
        panel.add(new JLabel("Course Lecturer:"));
        panel.add(lecturerField);
        panel.add(new JLabel("Room:"));
        panel.add(roomField);
        panel.add(new JLabel("Choose shift:"));
        panel.add(shiftCombo);
        panel.add(new JLabel("Number of slots:"));
        panel.add(slotsField);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "New Course",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String courseId = courseIdField.getText();
            Subject subject = subjects.get(subjectCombo.getSelectedIndex());
            Semester semester = semesters.get(semesterCombo.getSelectedIndex());
            String lecturer = lecturerField.getText();
            String room = roomField.getText();
            Shift shift = shifts.get(shiftCombo.getSelectedIndex());
            int slots = parseInt(slotsField.getText());

            Course newCourse = new Course(courseId, subject, semester, lecturer, room, shift, slots);
            if (CourseDAO.addCourse(newCourse) != null) {
                JOptionPane.showMessageDialog(parentFrame, "Added successfully!");
            } else {
                System.out.println("Not added");
            }
        }
    }
}
