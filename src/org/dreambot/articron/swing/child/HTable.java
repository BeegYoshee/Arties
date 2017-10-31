package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

/**
 * Created by: Niklas
 * Date: 21.09.2017
 * Alias: Dinh
 * Time: 16:25
 */

public class HTable extends JTable {

    public HTable(Object[][] objects, Object[] header, boolean adjust) {
        super(objects, header);
        setBorder(null);
        setBackground(HFrame.ELEMENT_BG);
        setForeground(HFrame.FOREGROUND);
        getTableHeader().setReorderingAllowed(false);
        setRowSelectionAllowed(false);
        setFocusable(false);
        setShowGrid(false);
        setEnabled(false);

        Enumeration<TableColumn> enumeration = getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            TableColumn column = enumeration.nextElement();
            column.setCellRenderer(new HTableRowRenderer());
        }

        if (adjust) {
            Font font = getFont();
            TableColumnModel tableColumnModel = getColumnModel();
            for (int i = 0; i < getColumnCount(); i++) {
                TableColumn column = tableColumnModel.getColumn(i);
                column.setHeaderRenderer(new HTableHeaderRenderer(i % 2 == 0 ? HFrame.HOV_GREEN : HFrame.SEL_GREEN));
                int longestColumnElement = determine(i, font), requiredColumnWidth = determine(column.getHeaderValue(), font);
                column.setPreferredWidth(longestColumnElement > requiredColumnWidth ? longestColumnElement : requiredColumnWidth);
            }
            revalidate();
            repaint();
        }
    }

    private int determine(Object element, Font font) {
        Graphics graphics = getGraphicsObject();
        graphics.setFont(font);
        FontMetrics metrics = graphics.getFontMetrics();
        return metrics.stringWidth(String.valueOf(element)) + 3;
    }

    private int determine(int column, Font font) {
        Graphics graphics = getGraphicsObject();
        graphics.setFont(font);

        int width = 0, calculatedWidth;
        FontMetrics metrics = graphics.getFontMetrics();

        TableModel tableModel = getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object object = tableModel.getValueAt(i, column);
            if ((calculatedWidth = metrics.stringWidth(String.valueOf(object))) > width) width = calculatedWidth;
        }
        return width + 3;
    }

    Graphics getGraphicsObject() {
        return new BufferedImage(1, 1, 1).createGraphics();
    }
}
