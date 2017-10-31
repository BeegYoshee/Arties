package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:34
 */

class HRootButton extends HPanel {

    HRootButton() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
    }

    public void addLabel(JLabel label, MouseAdapter adapter) {
        label.addMouseListener(adapter);
        add(label);
    }
}
