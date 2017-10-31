package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 21.09.2017
 * Alias: Dinh
 * Time: 17:54
 */

public class HTableRowRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setText(String.valueOf(value));
        if (row % 2 == 0) setBackground(HFrame.ELEMENT_BG);
        else setBackground(HFrame.TITLE_BG);
        return this;
    }

}
