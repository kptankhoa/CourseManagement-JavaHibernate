package ui.panel;

import dao.ClazzDAO;
import model.Clazz;
import ui.pane.clazz.newClassPane;
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

public class ClassPanel extends JPanel {
    private final JTable classTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<Clazz> classes;

    public ClassPanel(JFrame containerFrame) {
        String[] columns = {"Class ID", "No. of Students", "Males", "Females", "Delete"};
        classTable = new JTable();
        classTable.setModel(new DefaultTableModel(columns, 0));
        classTable.setRowHeight(30);
        classTable.setBackground(new Color(0xced4da));
        classTable.getTableHeader().setOpaque(false);
        classTable.getTableHeader().setBackground(new Color(0x495057));
        getClassList();
        classTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        classTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        classTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 4) {
                        deleteClass(classes.get(row));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(classTable);
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
        addBtn = new JButton("Add class");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newClassPane.display(containerFrame);
                getClassList();
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

    private void getClassList() {
        DefaultTableModel model = (DefaultTableModel) classTable.getModel();
        model.setRowCount(0);
        classes = ClazzDAO.getAllClasses();
        if (!classes.isEmpty()) {
            for (Clazz clazz : classes) {
                String classId = clazz.getClassId();
                int total = clazz.getTotal();
                int totalMales = clazz.getTotalMale();
                int totalFemales = clazz.getTotalFemale();
                Object[] row = {classId, total, totalMales, totalFemales, "Delete"};
                model.addRow(row);
            }
        }
    }

    private void deleteClass(Clazz clazz) {
        try {
            ClazzDAO.deleteClass(clazz);
        } catch (Exception e) {
            System.err.println(e);
        }
        getClassList();
    }
}
