package org.dreambot.articron.swing.special;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 19.10.2017
 * Alias: Dinh
 * Time: 18:55
 */

public class HDragList<T> extends JList<T> {
    private DefaultListModel<T> defaultListModel;

    @SafeVarargs
    public HDragList(T... objects) {
        setModel(defaultListModel = getModel(objects));
        setBackground(HFrame.ELEMENT_BG);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTransferHandler(new HListItemTransferHandler<>());
        setLayoutOrientation(JList.VERTICAL);
        setDropMode(DropMode.INSERT);
        setDragEnabled(true);
        setCellRenderer((list1, value, index, isSelected, cellHasFocus) -> {
            if (value instanceof JComponent) return (JComponent) value;
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel(value.toString(), JLabel.CENTER);
            label.setForeground(HFrame.FOREGROUND);
            panel.add(label, BorderLayout.CENTER);
            panel.setBackground(isSelected ? HFrame.LOGO_GREEN : getBackground());
            return panel;
        });
    }

    @SafeVarargs
    private final DefaultListModel<T> getModel(T... objects) {
        DefaultListModel<T> defaultListModel = new DefaultListModel<>();
        for (T t : objects) defaultListModel.addElement(t);
        return defaultListModel;
    }

    public DefaultListModel<T> getDefaultListModel() {
        return defaultListModel;
    }

    public void updateWidth(int width) {
        setFixedCellWidth(width);
    }
}
