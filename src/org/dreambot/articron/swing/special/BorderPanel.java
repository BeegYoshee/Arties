package org.dreambot.articron.swing.special;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.child.HCheckSliderText;

import java.awt.*;
import java.util.Objects;

/**
 * Created by: Niklas
 * Date: 15.07.2017
 * Time: 20:15
 */

public class BorderPanel extends JPanel {

    public BorderPanel(HCheckSliderText slider) {
    	setBackground(HFrame.BACKGROUND);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        OptionPanel componentBorder = new OptionPanel(slider, this, BorderFactory.createEtchedBorder());
        slider.getSlider().getEnabledButton().addActionListener(listener -> {
        	System.out.println("HI");
            boolean enable = slider.getSlider().isSelected();
            setPanelEnabled(this, enable);
        });
        setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), componentBorder));
        setPreferredSize(new Dimension(230, 110));
    }

    void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);
        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (Objects.equals(component.getClass().getName(), "org.dreambot.dinh.component.login.Information")) {
                setPanelEnabled((JPanel) component, isEnabled);
            } else component.setEnabled(isEnabled);
        }
    }
}
