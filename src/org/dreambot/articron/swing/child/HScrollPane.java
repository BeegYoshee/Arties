package org.dreambot.articron.swing.child;

import java.awt.Component;

import javax.swing.JScrollPane;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 16:56
 */

public class HScrollPane extends JScrollPane {

    public HScrollPane(Component component) {
        super(component);
        setBorder(null);
        setBackground(HFrame.SEL_GREEN);
        getViewport().setBackground(HFrame.ELEMENT_BG);
        getVerticalScrollBar().setUI(new HScrollBar());
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
