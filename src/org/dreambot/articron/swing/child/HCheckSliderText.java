package org.dreambot.articron.swing.child;

import java.awt.BorderLayout;

import org.dreambot.articron.swing.HPanel;

/**
 * Created by: Niklas
 * Date: 16.10.2017
 * Alias: Dinh
 * Time: 23:25
 */

public class HCheckSliderText extends HPanel {
    private HCheckSlider slider;

    public HCheckSliderText(String text) {
        setLayout(new BorderLayout());
        add(new HLabel(text), BorderLayout.CENTER);
        add(slider = new HCheckSlider(50), BorderLayout.EAST);
    }

    public HCheckSlider getSlider() {
        return slider;
    }
}
