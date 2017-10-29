package org.dreambot.articron.swing.child;

import java.awt.Color;

import javax.swing.JCheckBox;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 19:07
 */

public class HCheckBox extends JCheckBox {

    public HCheckBox(String text) {
        this(text, HFrame.ELEMENT_BG);
    }

    public HCheckBox(String text, Color background) {
        super(text);
        setBackground(background);
        setForeground(HFrame.FOREGROUND);
        setBorderPainted(false);
        setFocusPainted(false);
        setFocusable(false);
    }

}
