package ui.pane.subject;

import dao.SubjectDAO;
import model.Subject;

import javax.swing.*;
import java.awt.*;

public class newSubjectPane {
    public static void display() {
        JTextField subjectIdField = new JTextField();
        JTextField subjectNameField = new JTextField();
        Integer[] credit = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox subbjectCreditsCombo = new JComboBox<Integer>(credit);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("New subject ID:"));
        panel.add(subjectIdField);
        panel.add(new JLabel("New subject name:"));
        panel.add(subjectNameField);
        panel.add(new JLabel("Number of credits:"));
        panel.add(subbjectCreditsCombo);
        int result = JOptionPane.showConfirmDialog(null, panel, "New Subject",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String subjectId = subjectIdField.getText();
            String subjectName = subjectNameField.getText();
            int credits = (int) subbjectCreditsCombo.getSelectedItem();
            if ((subjectIdField.equals("")) || (subjectNameField.equals(""))) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
            } else {
                Subject newSubject = new Subject(subjectId, subjectName, credits);
                if (SubjectDAO.addSubject(newSubject) != null) {
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
