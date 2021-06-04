package ui.pane.student;

import dao.ClazzDAO;
import dao.StudentDAO;
import model.Clazz;
import model.Student;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class updateStudentPane {
    public static void display(JFrame parentFrame, Student student) {
        JTextField studentIdField = new JTextField(student.getStudentId());
        studentIdField.setEditable(false);
        JTextField nameField = new JTextField(student.getName());

        String[] genders = {"Male", "Female"};
        JComboBox genderCombo = new JComboBox(genders);
        genderCombo.setSelectedItem(student.getGender());

        UtilDateModel model = new UtilDateModel();
        Date utilDate = new Date(student.getDob().getTime());
        Calendar myCal = new GregorianCalendar();
        myCal.setTime(utilDate);
        int day = myCal.get(Calendar.DAY_OF_MONTH);
        int month = myCal.get(Calendar.MONTH);
        int year = myCal.get(Calendar.YEAR);
        model.setDate(year, month, day);
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl dobPicker = new JDatePickerImpl(datePanel);

        ArrayList<Clazz> clazzes = ClazzDAO.getAllClasses();
        ArrayList<String> classNames = new ArrayList<>();
        clazzes.forEach(c -> classNames.add(c.getClassId()));
        JComboBox classCombo = new JComboBox(classNames.toArray());
        classCombo.setSelectedItem(student.getClazz().getClassId());

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Choose gender:"));
        panel.add(genderCombo);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(dobPicker);
        panel.add(new JLabel("Choose  class:"));
        panel.add(classCombo);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Update Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String studentId = student.getStudentId();
            String name = nameField.getText();
            String gender = (String) genderCombo.getSelectedItem();
            Date dobInUtil = (Date) dobPicker.getModel().getValue();

            java.sql.Date dob = new java.sql.Date(dobInUtil.getTime());
            String username = studentId;
            Clazz clazz = clazzes.get(classCombo.getSelectedIndex());

            Student newStudent = new Student(studentId, name, gender, dob, username, clazz);

            if (StudentDAO.updateStudent(newStudent) != null) {
                JOptionPane.showMessageDialog(parentFrame, "Updated successfully!");
            } else {
                System.out.println("Not updated");
            }
        }

    }
}
