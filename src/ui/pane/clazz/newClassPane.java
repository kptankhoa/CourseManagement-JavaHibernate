package ui.pane.clazz;

import dao.ClazzDAO;
import model.Clazz;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class newClassPane extends JPanel {
    public static void display(JFrame parentFrame) {
        JTextField classIdField = new JTextField();
        JTextField totalStudentField = new JTextField();
        JTextField totalMaleField = new JTextField();
        JTextField totalFemaleField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("New Class ID:"));
        panel.add(classIdField);
        panel.add(new JLabel("Number of Students:"));
        panel.add(totalStudentField);
        panel.add(new JLabel("Number of Male Students:"));
        panel.add(totalMaleField);
        panel.add(new JLabel("Number of Female Students:"));
        panel.add(totalFemaleField);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "New Class",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String classId = classIdField.getText();
            int total = parseInt(totalStudentField.getText());
            int males = parseInt(totalMaleField.getText());
            int females = parseInt(totalFemaleField.getText());
            if (males + females != total) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid Inputs!");
            } else {
                Clazz newClazz = new Clazz(classId, total, males, females);
                if (ClazzDAO.addClass(newClazz) != null) {
                    JOptionPane.showMessageDialog(parentFrame, "Added successfully!");
                } else {
                    System.out.println("Not added");
                }
            }
        } else {
            System.out.println("Cancelled");
        }
    }
}
