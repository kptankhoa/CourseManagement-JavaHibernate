package ui.pane.semester;

import dao.AccountDAO;
import dao.SemesterDAO;
import model.Account;
import model.Semester;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class newSemesterPane {
    public static void display(int newId) {
        String[] names = {"HK1", "HK2", "HK3"};
        JComboBox semesterNameCombo = new JComboBox(names);
        String[] schoolYears = {"2020-2021", "2021-2022", "2022-2023", "2023-2024", "2024-2025"};
        JComboBox schoolYearCombo = new JComboBox(schoolYears);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(datePanel);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(datePanel2);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(semesterNameCombo);
        panel.add(new JLabel("School Year:"));
        panel.add(schoolYearCombo);
        panel.add(new JLabel("Start Date: "));
        panel.add(startDatePicker);
        panel.add(new JLabel("End Date: "));
        panel.add(endDatePicker);
        int result = JOptionPane.showConfirmDialog(null, panel, "New Semester",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = (String) semesterNameCombo.getSelectedItem();
            String schoolYear = (String) schoolYearCombo.getSelectedItem();
            Date startDateInUtil = (Date) startDatePicker.getModel().getValue();
            Date endDateInUtil = (Date) endDatePicker.getModel().getValue();
            java.sql.Date startDate = new java.sql.Date(startDateInUtil.getTime());
            java.sql.Date endDate = new java.sql.Date(endDateInUtil.getTime());
            Semester newSemester = new Semester(newId, name, schoolYear, startDate, endDate, 0);
            if(SemesterDAO.addSemester(newSemester)!=null){
                JOptionPane.showMessageDialog(null, "Added successfully!");
            } else {
                System.out.println("Not added");
            }
        }  else {
            System.out.println("Cancelled");
        }
    }
}
