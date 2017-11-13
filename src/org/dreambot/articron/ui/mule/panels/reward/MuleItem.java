package org.dreambot.articron.ui.mule.panels.reward;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;

public class MuleItem extends HPanel {

	private HImageLabel icon;
	private HLabel price;
	private MTARune rune;
	private int amount;

	public MuleItem(MTARune rune, int amount) {
		setLayout(new BorderLayout());
		this.rune = rune;
		RewardIcon icon = new RewardIcon(HImageLoader.loadImage(rune.getLink()));
		add(new HImageLabel(rune, new ImageIcon(icon), 5), BorderLayout.WEST);
		add(price = new HLabel(String.valueOf(this.amount = amount)), BorderLayout.EAST);
		price.setBorder(new EmptyBorder(0, 0, 0, 5));

		setBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 1));
	}

	public MTARune getRune() {
		return rune;
	}

	public int getAmount() {
		return amount;
	}
}
