package ui.panel;

import dao.ClazzDAO;
import dao.StudentDAO;
import model.Clazz;
import model.Student;
import ui.pane.student.newStudentPane;
import ui.util.ButtonEditor;
import ui.util.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentPanel extends JPanel {
    private final JTable studentTable;
    private final JPanel mainPanel;
    private JTextField searchField;
    private JComboBox classCombo;
    private JButton searchBtn;
    private JButton addBtn;
    private ArrayList<Student> students;
    private ArrayList<Clazz> clazzes;

    public StudentPanel(JFrame containerFrame) {
        String[] columns = {"Student ID", "Name", "Gender", "DOB", "Username", "Class", "Update student", "Reset password"};
        studentTable = new JTable();
        studentTable.setModel(new DefaultTableModel(columns, 0));
        studentTable.setRowHeight(30);
        studentTable.setBackground(new Color(0xced4da));
        studentTable.getTableHeader().setOpaque(false);
        studentTable.getTableHeader().setBackground(new Color(0x495057));
        getStudentList();
        studentTable.getColumn("Update student").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Reset password").setCellRenderer(new ButtonRenderer());
        studentTable.getColumn("Update student").setCellEditor(new ButtonEditor(new JCheckBox()));
        studentTable.getColumn("Reset password").setCellEditor(new ButtonEditor(new JCheckBox()));
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 3) {
//                        updateSubject(subjects.get(row));
                        getStudentList();
                    }
                    if (column == 4) {
//                        deleteSubject(subjects.get(row));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentTable);
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
        addBtn = new JButton("Add student");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newStudentPane.display(containerFrame);
                getStudentList();
            }
        });
        //search ui component
        searchField = new JTextField("Search student by name...");
        searchField.setBounds(50, 0, 298, 30);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search student by name...")) {
                    searchField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search student by name...");
                }
            }
        });
        clazzes = ClazzDAO.getAllClasses();
        ArrayList<String> classNames = new ArrayList<>();
        clazzes.forEach(c -> classNames.add(c.getClassId()));
        classCombo = new JComboBox(classNames.toArray());
        classCombo.setBounds(350, 0, 98, 30);
        classCombo.setBackground(new Color(0x6c757d));
        classCombo.setFocusable(false);
        searchBtn = new JButton("Search");
        searchBtn.setBounds(450, 0, 100, 30);
        searchBtn.setBackground(new Color(0x6c757d));
        searchBtn.setFocusable(false);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchStudents(containerFrame);
            }
        });
        topPanel.add(searchField);
        topPanel.add(classCombo);
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

    private void searchStudents(JFrame parentFrame) {
        String nameToSearch = searchField.getText();
        if(nameToSearch.equals("Search student by name...")){
            JOptionPane.showMessageDialog(parentFrame, "Invalid Input!");
        }
        String classId = (String) classCombo.getSelectedItem();
        ArrayList<Student> studentsFromClass = StudentDAO.getStudentsByClassId(classId);
        ArrayList<Student> studentsWithName = new ArrayList<>();
        studentsFromClass.forEach(student -> {
            if(student.getName().toLowerCase().contains(nameToSearch)){
                studentsWithName.add(student);
            }
        });
        updateTable(studentsWithName);
    }

    private void updateTable(ArrayList<Student> students){
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        if (!students.isEmpty()) {
            for (Student student : students) {
                String studentId = student.getStudentId();
                String name = student.getName();
                String gender = student.getGender();
                String dob = student.getDob().toString();
                String username = student.getUsername();
                String clazz = student.getClazz().getClassId();
                String[] row = {studentId, name, gender, dob, username, clazz, "Update", "Reset"};
                model.addRow(row);

            }
        }
    }

    private void getStudentList() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        students = StudentDAO.getAllStudents();
        if (!students.isEmpty()) {
            for (Student student : students) {
                String studentId = student.getStudentId();
                String name = student.getName();
                String gender = student.getGender();
                String dob = student.getDob().toString();
                String username = student.getUsername();
                String clazz = student.getClazz().getClassId();
                String[] row = {studentId, name, gender, dob, username, clazz, "Update", "Reset"};
                model.addRow(row);

            }
        }
    }
}
