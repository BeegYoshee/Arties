package org.dreambot.articron.ui;

import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;

public class ProfilePicker extends HFrame {

    public ProfilePicker(String title) {
        super(title, HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"));

        prepare(null);
    }
}
