package org.dreambot.articron.swing.child;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 18:33
 */

class HComboBoxUI extends BasicComboBoxUI {
    private final Dimension DIMENSION = new Dimension(16, 16);

    @Override
    protected JButton createArrowButton() {
        return new HButton(HImageLoader.getImage("down"), HFrame.LIST_BG, HFrame.SEL_GREEN) {
            @Override
            public Dimension getPreferredSize() {
                return DIMENSION;
            }
        };
    }

    @Override
    protected ComboPopup createPopup() {
        return new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scroller.getVerticalScrollBar().setUI(new HScrollBar());
                return scroller;
            }
        };
    }

}
