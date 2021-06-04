package ui.panel;

import dao.SemesterDAO;
import dao.SubjectDAO;
import model.Subject;
import ui.pane.subject.newSubjectPane;
import ui.pane.subject.updateSubjectPane;
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

public class SubjectPanel extends JPanel {
    private final JTable subjectTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<Subject> subjects;

    public SubjectPanel(JFrame containerFrame) {
        String[] columns = {"Subject ID", "Name", "Credits", "Update", "Delete"};
        subjectTable = new JTable();
        subjectTable.setModel(new DefaultTableModel(columns, 0));
        subjectTable.setRowHeight(30);
        subjectTable.setBackground(new Color(0xced4da));
        subjectTable.getTableHeader().setOpaque(false);
        subjectTable.getTableHeader().setBackground(new Color(0x495057));
        getSubjectList();
        subjectTable.getColumn("Update").setCellRenderer(new ButtonRenderer());
        subjectTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        subjectTable.getColumn("Update").setCellEditor(new ButtonEditor(new JCheckBox()));
        subjectTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        subjectTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 3) {
                        updateSubject(containerFrame, subjects.get(row));
                        getSubjectList();
                    }
                    if (column == 4) {
                        deleteSubject(subjects.get(row));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(subjectTable);
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
        addBtn = new JButton("Add subject");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newSubjectPane.display(containerFrame);
                getSubjectList();
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

    private void getSubjectList() {
        DefaultTableModel model = (DefaultTableModel) subjectTable.getModel();
        model.setRowCount(0);
        subjects = SubjectDAO.getAllSubjects();
        if (!subjects.isEmpty()) {
            for (Subject subject : subjects) {
                String subjectId = subject.getSubjectId();
                String name = subject.getName();
                int credits = subject.getCredits();
                Object[] row = {subjectId, name, credits, "Update", "Delete"};
                model.addRow(row);
            }
        }
    }

    private void updateSubject(JFrame containerFrame, Subject subject) {
        updateSubjectPane.display(containerFrame, subject);
    }

    private void deleteSubject(Subject subject) {
        try {
            SubjectDAO.deleteSubject(subject);
        } catch (Exception e){
            System.err.println(e);
        }
        getSubjectList();
    }
}
