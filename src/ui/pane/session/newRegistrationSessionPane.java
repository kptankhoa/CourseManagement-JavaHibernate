package ui.pane.session;

import dao.RegistrationSessionDAO;
import model.ActiveSemester;
import model.RegistrationSession;
import model.Semester;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class newRegistrationSessionPane extends JPanel {
    public static void display(JFrame parentFrame, int newId) {
        Semester activeSemester = ActiveSemester.getActiveSemester();
        JTextField semesterField = new JTextField(activeSemester.getName() + " - " + activeSemester.getSchoolYear());
        semesterField.setEditable(false);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(datePanel);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(datePanel2);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("New Registration Session for Semester:"));
        panel.add(semesterField);
        panel.add(new JLabel("Start Date: "));
        panel.add(startDatePicker);
        panel.add(new JLabel("End Date: "));
        panel.add(endDatePicker);
        int result = JOptionPane.showConfirmDialog(parentFrame, panel, "New Registration Session",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Date startDateInUtil = (Date) startDatePicker.getModel().getValue();
            Date endDateInUtil = (Date) endDatePicker.getModel().getValue();
            java.sql.Date startDate = new java.sql.Date(startDateInUtil.getTime());
            java.sql.Date endDate = new java.sql.Date(endDateInUtil.getTime());
            RegistrationSession newRS = new RegistrationSession(newId, activeSemester, startDate, endDate);
            if (RegistrationSessionDAO.addRegistrationSession(newRS) != null) {
                JOptionPane.showMessageDialog(parentFrame, "Added successfully!");
            } else {
                System.out.println("Not added");
            }
        } else {
            System.out.println("Cancelled");
        }
    }
}
