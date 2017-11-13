package org.dreambot.articron.ui.bot.panels.misc;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HCheckSliderText;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.swing.child.HTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by: Niklas Date: 18.10.2017 Alias: Dinh Time: 23:02
 */

public class NotificationPanel extends HPanel {

	private HCheckSliderText level, action, reward, end;
	private HTextField mail, alias;

	public NotificationPanel() {
		setLayout(new BorderLayout());

		HPanel split = new HPanel(new GridLayout(0, 1, 0, 3));
		split.add(new HLabel("Notifications", JLabel.CENTER, new Font("Arial", Font.PLAIN, 17)), BorderLayout.NORTH);
		split.add(mail = new HTextField("E-Mail: ", HFrame.ELEMENT_BG, HFrame.FOREGROUND), BorderLayout.CENTER);
		split.add(alias = new HTextField("Bot-Alias: ", HFrame.ELEMENT_BG, HFrame.FOREGROUND), BorderLayout.SOUTH);

		add(split, BorderLayout.NORTH);
		HPanel grid = new HPanel(new GridLayout(0, 1, 0, 20));
		TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 1),
				"E-Mail conditions");
		border.setTitleColor(HFrame.FOREGROUND);
		border.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		grid.setBorder(border);
		grid.add(level = new HCheckSliderText("Magic Level up"));
		grid.add(reward = new HCheckSliderText("Reward received"));
		grid.add(action = new HCheckSliderText("Mule action complete"));
		grid.add(end = new HCheckSliderText("End of Script"));
		add(grid, BorderLayout.CENTER);

		setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
	}

	public HCheckSliderText getLevel() {
		return level;
	}

	public HCheckSliderText getAction() {
		return action;
	}

	public HCheckSliderText getReward() {
		return reward;
	}

	public HCheckSliderText getEnd() {
		return end;
	}

	public String getEMail() {
		return mail.toString();
	}

	public String getAlias() {
		return alias.toString();
	}
}
