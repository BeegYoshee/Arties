package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 18.10.2017
 * Alias: Dinh
 * Time: 22:18
 */

public class HTabbedPane extends JTabbedPane {

    public HTabbedPane() {
        setFocusable(false);
        setForeground(HFrame.FOREGROUND);
        setBackground(HFrame.ELEMENT_BG);
        UIManager.put("TabbedPane.selected", HFrame.TITLE_BG);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                shadow = HFrame.BACKGROUND;
                darkShadow = HFrame.BACKGROUND;
                lightHighlight = HFrame.BACKGROUND;
            }
        });
    }
}
