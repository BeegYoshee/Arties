package org.dreambot.articron.swing.child;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:16
 */

public class HLabel extends JLabel {
    public HLabel() {
        this(null, JTextField.LEFT);
    }

    public HLabel(int alignment) {
        this(null, alignment);
    }

    public HLabel(String text) {
        this(text, JTextField.LEFT);
    }

    public HLabel(String text, int alignment) {
        this(text, alignment, new JLabel().getFont());
    }

    public HLabel(String text, int alignment, Font font) {
        super(text);
        setOpaque(true);
        setBackground(HFrame.BACKGROUND);
        setHorizontalAlignment(alignment);
        setFont(font);
        setForeground(HFrame.FOREGROUND);
    }
}
