package org.dreambot.articron.swing.child;

import java.awt.Color;

import javax.swing.JSeparator;

/**
 * Created by: Niklas
 * Date: 18.10.2017
 * Alias: Dinh
 * Time: 23:11
 */

public class HSeperator extends JSeparator {

    public HSeperator(Color foreground, Color background, int orientation) {
        super(orientation);
        setForeground(foreground);
        setBackground(background);
    }
}
