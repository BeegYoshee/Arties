package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 19:16
 */

public class HCheckSlider extends HPanel {

    private HCheckButton disabled, enabled;

    public HCheckSlider(int preferredWidth) {
        setLayout(new FlowLayout());
        HPanel buttonPanel = new HPanel(new GridLayout(0, 2));
        buttonPanel.setPreferredSize(new Dimension(preferredWidth, 30));
        buttonPanel.setMaximumSize(new Dimension(preferredWidth, 30));
        buttonPanel.setMinimumSize(new Dimension(preferredWidth, 30));
        buttonPanel.add(disabled = new HCheckButton(listener -> buttonHandler(enabled, disabled), 1), BorderLayout.WEST);
        buttonPanel.add(enabled = new HCheckButton(listener -> buttonHandler(disabled, enabled), 3), BorderLayout.EAST);
        add(buttonPanel);
        disabled.setSelected(true);
    }

    private void buttonHandler(JToggleButton first, JToggleButton second) {
        if (first.isSelected()) {
            first.setSelected(false);
            second.setSelected(true);
        } else {
            second.setSelected(false);
            first.setSelected(true);
        }
        repaint();
    }

    public boolean isSelected() {
        return enabled.isSelected();
    }

    public HCheckButton getDisabledButton() {
    	return disabled;
    }
    
    public HCheckButton getEnabledButton() {
    	return enabled;
    }
}
