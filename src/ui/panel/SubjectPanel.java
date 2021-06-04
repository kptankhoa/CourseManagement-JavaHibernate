package ui.panel;

import dao.SemesterDAO;
import dao.SubjectDAO;
import model.Account;
import model.Subject;
import ui.pane.subject.newSubjectPane;
import ui.pane.subject.updateSubjectPane;
import ui.util.ButtonEditor;
import ui.util.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SubjectPanel extends JPanel {
    private final JTable subjectTable;
    private final JPanel mainPanel;
    private JTextField searchField;
    private JButton searchBtn;
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
        searchField = new JTextField("Search subject...");
        searchField.setBounds(50, 0, 398, 30);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search subject...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search subject...");
                }
            }
        });
        searchBtn = new JButton("Search");
        searchBtn.setBounds(450, 0, 100, 30);
        searchBtn.setBackground(new Color(0x6c757d));
        searchBtn.setFocusable(false);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchSubjects(containerFrame);
            }
        });
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(addBtn);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 550));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);

    }

    private void searchSubjects(JFrame parentFrame) {
        String stringToSearch = searchField.getText();
        if (stringToSearch.equals("Search subject...")) {
            JOptionPane.showMessageDialog(parentFrame, "Invalid Input!");
            return;
        }
        ArrayList<Subject> resultSubjects = new ArrayList<>();
        subjects.forEach(subject -> {
            if (subject.getName().toLowerCase().contains(stringToSearch)) {
                resultSubjects.add(subject);
            }
        });
        updateTable(resultSubjects);
    }

    private void updateTable(ArrayList<Subject> subjects) {
        DefaultTableModel model = (DefaultTableModel) subjectTable.getModel();
        model.setRowCount(0);
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

    private void getSubjectList() {
        subjects = SubjectDAO.getAllSubjects();
        updateTable(subjects);
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
