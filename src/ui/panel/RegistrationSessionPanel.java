package ui.panel;

import dao.RegistrationSessionDAO;
import model.RegistrationSession;
import model.Semester;
import ui.pane.session.newRegistrationSessionPane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegistrationSessionPanel extends JPanel {
    private final JTable registrationSessionsTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<RegistrationSession> registrationSessions;

    public RegistrationSessionPanel(JFrame containerFrame) {
        String[] columns = {"Semester", "Start Date", "End Date"};
        registrationSessionsTable = new JTable();
        registrationSessionsTable.setModel(new DefaultTableModel(columns, 0));
        registrationSessionsTable.setRowHeight(30);
        registrationSessionsTable.setBackground(new Color(0xced4da));
        registrationSessionsTable.getTableHeader().setOpaque(false);
        registrationSessionsTable.getTableHeader().setBackground(new Color(0x495057));
        getRegistrationSessionList();
        JScrollPane scrollPane = new JScrollPane(registrationSessionsTable);
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
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(30, 30));
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0xadb5bd));
        addBtn = new JButton("Add new session");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int newId;
                if (registrationSessions.size() == 0) {
                    newId = 1;
                } else {
                    newId = registrationSessions.get(registrationSessions.size() - 1).getSessionId() + 1;
                }
                newRegistrationSessionPane.display(containerFrame, newId);
                getRegistrationSessionList();
            }
        });
        topPanel.add(addBtn);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 550));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);
    }

    private void getRegistrationSessionList() {
        DefaultTableModel model = (DefaultTableModel) registrationSessionsTable.getModel();
        model.setRowCount(0);
        registrationSessions = RegistrationSessionDAO.getAllRegistrationSessions();
        if (!registrationSessions.isEmpty()) {
            for (RegistrationSession session : registrationSessions) {
                Semester s = session.getSemester();
                String semester = s.getName() + " - " + s.getSchoolYear();
                String startDate = session.getStartDate().toString();
                String endDate = session.getEndDate().toString();
                String[] row = {semester, startDate, endDate};
                model.addRow(row);
            }
        }
    }
}
