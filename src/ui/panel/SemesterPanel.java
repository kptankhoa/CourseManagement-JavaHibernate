package ui.panel;

import dao.SemesterDAO;
import model.ActiveSemester;
import model.Semester;
import ui.pane.semester.newSemesterPane;
import ui.util.ButtonEditor;
import ui.util.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SemesterPanel extends JPanel {
    private final JTable semesterTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<Semester> semesters;

    public SemesterPanel(JFrame containerFrame) {
        String[] columns = {"Active Semester", "Name", "School Year", "Start Date", "End Date", "Set Active", "Delete"};
        semesterTable = new JTable();
        semesterTable.setModel(new DefaultTableModel(columns, 0));
        semesterTable.setRowHeight(30);
        semesterTable.setBackground(new Color(0xced4da));
        semesterTable.getTableHeader().setOpaque(false);
        semesterTable.getTableHeader().setBackground(new Color(0x495057));
        getSemesterList();
        semesterTable.getColumn("Set Active").setCellRenderer(new ButtonRenderer());
        semesterTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        semesterTable.getColumn("Set Active").setCellEditor(new ButtonEditor(new JCheckBox()));
        semesterTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        semesterTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 5) {
                        setActiveSemester(semesters.get(row));
                        getSemesterList();
                    }
                    if (column == 6) {
                        deleteSemester(semesters.get(row));
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(semesterTable);
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
        addBtn = new JButton("Add semester");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newSemesterPane.display(containerFrame, semesters.get(semesters.size()-1).getSemesterId()+1);
                getSemesterList();
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

    private void getSemesterList() {
        DefaultTableModel model = (DefaultTableModel) semesterTable.getModel();
        model.setRowCount(0);
        semesters = SemesterDAO.getAllSemesters();
        if (!semesters.isEmpty()) {
            for (Semester semester : semesters) {
                String active = semester.getActive() == 1 ? "Active" : "";
                String name = semester.getName();
                String schoolYear = semester.getSchoolYear();
                String startDate = semester.getStartDate().toString();
                String endDate = semester.getEndDay().toString();
                String[] row = {active, name, schoolYear, startDate, endDate, "Set", "Delete"};
                model.addRow(row);
            }
        }
    }

    private void setActiveSemester(Semester semester) {
        ActiveSemester.setNewActiveSemester(semester);
    }

    private void deleteSemester(Semester semester) {
        try {
            SemesterDAO.deleteSemester(semester);
        } catch (Exception e) {
            System.err.println(e);
        }
        getSemesterList();
    }
}
