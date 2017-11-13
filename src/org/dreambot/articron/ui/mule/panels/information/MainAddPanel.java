package org.dreambot.articron.ui.mule.panels.information;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;

import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HTextField;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;
import org.dreambot.articron.ui.mule.panels.reward.MuleItem;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MainAddPanel extends HPanel {

	private HTextField amount;

	public MainAddPanel(MainTradePanel mainTradePanel, MuleRunePanel runePanel) {
		setLayout(new BorderLayout());
		add(amount = new HTextField("Amount: ", HFrame.ELEMENT_BG, HFrame.FOREGROUND), BorderLayout.CENTER);
		add(new HButton("Add", listener -> {
			int a = Integer.parseInt(amount.toString());
			if (a < 0) {
				return;
			}
			MTARune rune = mainTradePanel.getSelected();
			runePanel.getDragList().getDefaultListModel()
					.addElement(new MuleItem((MTARune) new HImageLabel(rune,
							new ImageIcon(new RewardIcon(HImageLoader.loadImage(rune.getLink()))), a).getObject(),
							a));
		}), BorderLayout.EAST);
	}

}
