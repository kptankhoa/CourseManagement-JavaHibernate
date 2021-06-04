package ui.pane.student;

import dao.AccountDAO;
import dao.ClazzDAO;
import dao.StudentDAO;
import model.Account;
import model.Clazz;
import model.Student;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class newStudentPane {
    public static void display(JFrame parentFrame) {
        JTextField studentIdField = new JTextField();
        JTextField nameField = new JTextField();

        String[] genders = {"Male", "Female"};
        JComboBox genderCombo = new JComboBox(genders);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl dobPicker = new JDatePickerImpl(datePanel);

        ArrayList<Clazz> clazzes = ClazzDAO.getAllClasses();
        ArrayList<String> classNames = new ArrayList<>();
        clazzes.forEach(c -> classNames.add(c.getClassId()));
        JComboBox classCombo = new JComboBox(classNames.toArray());

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("New student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Choose gender:"));
        panel.add(genderCombo);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobPicker);
        panel.add(new JLabel("Choose  class:"));
        panel.add(classCombo);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "New Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            String gender = (String) genderCombo.getSelectedItem();
            Date dobInUtil = (Date) dobPicker.getModel().getValue();
            java.sql.Date dob = new java.sql.Date(dobInUtil.getTime());
            String username = studentId;
            Clazz clazz = clazzes.get(classCombo.getSelectedIndex());

            Account newAcc = new Account(studentId, studentId, "student");
            AccountDAO.addAccount(newAcc);

            Student newStudent = new Student(studentId, name, gender, dob, username, clazz);

            if (StudentDAO.addStudent(newStudent) != null) {
                JOptionPane.showMessageDialog(parentFrame, "Added successfully!");
            } else {
                System.out.println("Not added");
            }
        }

    }
}
