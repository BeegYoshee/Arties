package org.dreambot.articron.swing.child;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 18:24
 */

public class HComboBoxIconEditor extends BasicComboBoxEditor {
    private JLabel displayLabel;
    private JPanel elementPanel;
    private Object selectedItem;

    HComboBoxIconEditor(Font font) {
        displayLabel = new HLabel(JTextField.LEFT);
        displayLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        displayLabel.setOpaque(false);
        displayLabel.setFont(font);
        displayLabel.setForeground(HFrame.FOREGROUND);
        elementPanel = new HPanel(new BorderLayout());
        elementPanel.add(displayLabel, BorderLayout.CENTER);
        elementPanel.setBackground(HFrame.ELEMENT_BG);
        setSpellIcon(new ImageIcon(new BufferedImage(36, 32, BufferedImage.TYPE_INT_ARGB)));
    }
    
    public void setSpellIcon(Icon icon) {
    	displayLabel.setIcon(icon);
    }

    public Component getEditorComponent() {
        return elementPanel;
    }

    public Object getItem() {
        return selectedItem;
    }

    public void setItem(Object item) {
        this.selectedItem = item;
        displayLabel.setText(item.toString());
    }
}