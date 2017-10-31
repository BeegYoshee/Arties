package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 18:14
 */

class HCellRenderer extends JLabel implements ListCellRenderer<Object> {

    HCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(String.valueOf(value));
        setBackground(HFrame.ELEMENT_BG);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setForeground(HFrame.FOREGROUND);
        if (isSelected) {
            setBackground(HFrame.LOGO_GREEN);
        }
        return this;
    }
}
