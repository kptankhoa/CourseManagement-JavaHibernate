package ui.util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(new Color(0x6c757d));
        } else {
            setForeground(table.getForeground());
            setBackground(new Color(0x6c757d));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
