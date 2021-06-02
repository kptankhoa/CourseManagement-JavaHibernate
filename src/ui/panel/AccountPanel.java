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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccountPanel extends JPanel {
    private final JTable accountTable;
    private final JPanel mainPanel;
    private JButton addBtn;
    private ArrayList<Account> minAccs;

    public AccountPanel() {
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
                        updatePassword(minAccs.get(row));
                    }
                    if (column == 3) {
                        deleteAccount(minAccs.get(row));
                    }
                }
            }
        });
//
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
                newAccountPane.display();
                showMinistryAccountList();
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

    private void showMinistryAccountList() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.setRowCount(0);
        ArrayList<Account> accounts = AccountDAO.getAllAccounts();
        if (!accounts.isEmpty()) {
            minAccs = new ArrayList<>();
            for (Account account : accounts)
                if (account.getType().equals("ministry")) {
                    String username = account.getUsername();
                    String type = account.getType();
                    String[] row = {username, type, "Update", "Delete"};
                    model.addRow(row);
                    minAccs.add(account);
                }
        }
    }

    private void updatePassword(Account acc) {
//        return AccountDAO.updateAccount(acc);
        updatePwdPane.display(acc);
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


