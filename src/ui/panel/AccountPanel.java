package ui.panel;

import dao.AccountDAO;
import model.Account;
import ui.pane.account.newAccountPane;
import ui.pane.account.updatePwdPane;
import ui.util.ButtonEditor;
import ui.util.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AccountPanel extends JPanel {
    private final JTable accountTable;
    private final JPanel mainPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton addBtn;
    private ArrayList<Account> minAccs;

    public AccountPanel(JFrame containerFrame) {
        String[] columns = {"Username", "Type", "Update Password", "Delete"};
        accountTable = new JTable();
        accountTable.setModel(new DefaultTableModel(columns, 0));
        accountTable.setRowHeight(30);
        accountTable.setBackground(new Color(0xced4da));
        accountTable.getTableHeader().setOpaque(false);
        accountTable.getTableHeader().setBackground(new Color(0x495057));
        showMinistryAccountList();
        accountTable.getColumn("Update Password").setCellRenderer(new ButtonRenderer());
        accountTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        accountTable.getColumn("Update Password").setCellEditor(new ButtonEditor(new JCheckBox()));
        accountTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox()));
        accountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    if (column == 2) {
                        updatePassword(containerFrame, minAccs.get(row));
                    }
                    if (column == 3) {
                        deleteAccount(minAccs.get(row));
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(accountTable);
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
        addBtn = new JButton("Add minister");
        addBtn.setBounds(570, 0, 200, 30);
        addBtn.setBackground(new Color(0x6c757d));
        addBtn.setFocusable(false);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newAccountPane.display(containerFrame);
                showMinistryAccountList();
            }
        });
        searchField = new JTextField("Search account...");
        searchField.setBounds(50, 0, 398, 30);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search account...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search account...");
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
                searchAccounts(containerFrame);
            }
        });
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(addBtn);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0xadb5bd));
        this.setOpaque(true);
        this.add(mainPanel);
    }

    private void searchAccounts(JFrame parentFrame) {
        String stringToSearch = searchField.getText();
        if (stringToSearch.equals("Search account...")) {
            JOptionPane.showMessageDialog(parentFrame, "Invalid Input!");
            return;
        }
        ArrayList<Account> resultAccs = new ArrayList<>();
        minAccs.forEach(account -> {
            if (account.getUsername().toLowerCase().contains(stringToSearch)) {
                resultAccs.add(account);
            }
        });
        updateTable(resultAccs);
    }

    private void updateTable(ArrayList<Account> accounts) {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.setRowCount(0);
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                String username = account.getUsername();
                String type = account.getType();
                String[] row = {username, type, "Update", "Delete"};
                model.addRow(row);
            }
        }
    }
    private void showMinistryAccountList() {
        ArrayList<Account> accounts = AccountDAO.getAllAccounts();
        if (!accounts.isEmpty()) {
            minAccs = new ArrayList<>();
            for (Account account : accounts)
                if (account.getType().equals("ministry")) {
                    minAccs.add(account);
                }
        }
        updateTable(minAccs);
    }

    private void updatePassword(JFrame containerFrame, Account acc) {
        updatePwdPane.display(containerFrame, acc);
    }

    private void deleteAccount(Account acc) {
        try {
            AccountDAO.deleteAccount(acc);
        } catch (Exception e) {
            System.err.println(e);
        }
        showMinistryAccountList();
    }

}


