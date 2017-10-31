package org.dreambot.articron.swing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:03
 */

public class HPanel extends JPanel {

    public HPanel() {
        this(null, null);
    }

    public HPanel(LayoutManager layout) {
        this(layout, null);
    }

    public HPanel(Border border) {
        this(null, border);
    }

    public HPanel(LayoutManager layout, Border border) {
        super.setLayout(layout);
        super.setBackground(HFrame.BACKGROUND);
        super.setBorder(border);
    }

    public HPanel addComponent(Component component) {
        add(component);
        return this;
    }
}
