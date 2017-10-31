package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 18:27
 */

class HComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {

    HComboBoxRenderer() {
        this(new Font("Arial", Font.PLAIN, 14));
    }

    HComboBoxRenderer(Font font) {
        setOpaque(true);
        setFont(font);
        setBackground(HFrame.ELEMENT_BG);
        setForeground(HFrame.FOREGROUND);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
        setBackground(isSelected ? HFrame.LOGO_GREEN : HFrame.ELEMENT_BG);
        setForeground(HFrame.FOREGROUND);
        setText(value.toString());
        return this;
    }

}