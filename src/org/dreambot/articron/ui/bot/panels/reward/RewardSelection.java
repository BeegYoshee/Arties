package org.dreambot.articron.ui.bot.panels.reward;

import org.dreambot.articron.data.Reward;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 20.10.2017
 * Alias: Dinh
 * Time: 20:59
 */

public class RewardSelection extends HPanel {

    private HImageLabel[] imageLabels = new HImageLabel[Reward.values().length];

    public RewardSelection() {
        setLayout(new BorderLayout());
        HPanel scroll = new HPanel(new GridLayout(0, 1));
        Reward[] values = Reward.values();
        for (int i = 0; i < values.length; i++) {
            Reward reward = values[i];
            HImageLabel imageLabel = new HImageLabel(reward, new ImageIcon(new RewardIcon(HImageLoader.loadImage(reward.getItemSprite()))), 5);
            imageLabel.setPreferredSize(new Dimension(180, 32));
            scroll.add(imageLabels[i] = imageLabel);
        }
        add(new HScrollPane(scroll), BorderLayout.CENTER);
    }

    public HImageLabel[] getImageLabels() {
        return imageLabels;
    }
}
