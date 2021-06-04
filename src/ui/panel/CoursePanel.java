package ui.panel;

import dao.CourseDAO;
import model.ActiveSemester;
import model.Course;
import model.Semester;
import ui.pane.course.newCoursePane;
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


public class CoursePanel extends JPanel {
    private final JTable courseTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<Course> courses;
    private Semester activeSemester = ActiveSemester.getActiveSemester();

    public CoursePanel(JFrame containerFrame) {
        String[] columns = {"Course ID", "Subject", "Semester", "Lecturer", "Room", "Shift", "Slots", "Delete"};
        courseTable = new JTable();
        courseTable.setModel(new DefaultTableModel(columns, 0));
        courseTable.setRowHeight(30);
        courseTable.setBackground(new Color(0xced4da));
        courseTable.getTableHeader().setOpaque(false);
        courseTable.getTableHeader().setBackground(new Color(0x495057));
        getCourseList();
        courseTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        courseTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 7) {
                        deleteCourse(courses.get(row));
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(courseTable);
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
        addBtn = new JButton("Add course");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newCoursePane.display(containerFrame);
                getCourseList();
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

    private void getCourseList() {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0);
        courses = CourseDAO.getCoursesBySemesterId(activeSemester.getSemesterId());
        if (!courses.isEmpty()) {
            for (Course course : courses) {
//                if (course.getSemester().equals(activeSemester)) {
                    String id = course.getCourseId();
                    String subject = course.getSubject().getName();
                    Semester s = course.getSemester();
                    String semester = s.getName() + " - " + s.getSchoolYear();
                    String lecturer = course.getLecturer();
                    String room = course.getRoom();
                    String shift = course.getShift().getTime();
                    int slots = course.getSlots();
                    Object[] row = {id, subject, semester, lecturer, room, shift, slots, "Delete"};
                    model.addRow(row);
//                }
            }
        }
    }

    private void deleteCourse(Course course) {
        try {
            CourseDAO.deleteCourse(course);
        } catch (Exception e) {
            System.err.println(e);
        }
        getCourseList();
    }


}
