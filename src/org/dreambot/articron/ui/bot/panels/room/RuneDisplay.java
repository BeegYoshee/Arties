package org.dreambot.articron.ui.bot.panels.room;

import org.dreambot.articron.data.MTASpell;
import org.dreambot.articron.data.RuneRequirement;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;

import javax.swing.*;
import java.awt.*;

public class RuneDisplay extends HPanel {

	private HPanel display;

	public RuneDisplay() {
		super(new BorderLayout());
		add(display = new HPanel(new FlowLayout()));
	}

	public void update(MTASpell spell) {
		display.removeAll();
		if (spell == MTASpell.NONE)return;
		RuneRequirement[] requirement = spell.getRequirements();
		for (RuneRequirement r : requirement) {
			display.add(new Display(r));
		}
		display.repaint();
	}

	private class Display extends HPanel {
		public Display(RuneRequirement requirement) {
			setLayout(new BorderLayout());
			add(new JLabel(new ImageIcon(new RewardIcon(HImageLoader.loadImage(requirement.getRune().getLink())))),
					BorderLayout.CENTER);
			add(new HLabel(String.valueOf(requirement.getAmount()), JLabel.CENTER), BorderLayout.SOUTH);
		}
	}
}
