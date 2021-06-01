package ui.pane.subject;

import dao.SubjectDAO;
import model.Subject;

import javax.swing.*;
import java.awt.*;

public class updateSubjectPane {
    public static void display(Subject subject) {
        JTextField subjectIdField = new JTextField(subject.getSubjectId());
        subjectIdField.setEditable(false);
        JTextField subjectNameField = new JTextField(subject.getName());
        Integer[] credit = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox subjectCreditsCombo = new JComboBox<Integer>(credit);
        subjectCreditsCombo.setSelectedItem(subject.getCredits());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Subject ID:"));
        panel.add(subjectIdField);
        panel.add(new JLabel("Subject name:"));
        panel.add(subjectNameField);
        panel.add(new JLabel("Number of credits:"));
        panel.add(subjectCreditsCombo);
        int result = JOptionPane.showConfirmDialog(null, panel, "New Ministry",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String subjectId = subjectIdField.getText();
            String subjectName = subjectNameField.getText();
            int credits = (int) subjectCreditsCombo.getSelectedItem();
            if ((subjectNameField.equals(""))) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
            } else {
                Subject newSubject = new Subject(subjectId, subjectName, credits);
                if (SubjectDAO.updateSubject(newSubject) != null) {
                    JOptionPane.showMessageDialog(null, "Added successfully!");
                } else {
                    System.out.println("Not added");
                }
            }
        } else {
            System.out.println("Cancelled");
        }
    }
}
