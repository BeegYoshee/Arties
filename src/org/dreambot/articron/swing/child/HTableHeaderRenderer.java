package org.dreambot.articron.swing.child;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 21.09.2017
 * Alias: Dinh
 * Time: 16:39
 */

public class HTableHeaderRenderer extends JLabel implements TableCellRenderer {

    HTableHeaderRenderer(Color background) {
        setOpaque(true);
        setBackground(background);
        setForeground(HFrame.FOREGROUND);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

}