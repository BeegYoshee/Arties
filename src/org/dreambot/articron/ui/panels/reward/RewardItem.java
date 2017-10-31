package org.dreambot.articron.ui.panels.reward;

import org.dreambot.articron.data.Reward;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.price.PriceCheck;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.swing.child.HTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by: Niklas Date: 19.10.2017 Alias: Dinh Time: 21:56
 */

public class RewardItem extends HPanel {
	public static String[] columns = { "Telekinetic room", "Enchanting room", "Alchemy room", "Graveyard room" };

	private HImageLabel icon;
	private HLabel price;
	private Reward reward;
	
	public RewardItem(Reward reward) {
		setLayout(new BorderLayout());
		HPanel top = new HPanel(new BorderLayout()), split = new HPanel(new BorderLayout());
		split.add(top, BorderLayout.NORTH);
		this.reward=reward;
		RewardIcon icon = new RewardIcon(HImageLoader.loadImage(reward.getItemSprite()));
		top.add(new HImageLabel(reward.toString(), new ImageIcon(icon), 5), BorderLayout.WEST);
		top.add(price = new HLabel("Price: " + String.valueOf(PriceCheck.price(reward.getID()))), BorderLayout.EAST);
		price.setBorder(new EmptyBorder(0, 0, 0, 5));

		HTable table = new HTable(reward.getData(), columns, true);
		add(table, BorderLayout.CENTER);

		split.add(table.getTableHeader(), BorderLayout.CENTER);
		add(split, BorderLayout.NORTH);

		setBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 1));
	}
	
	public Reward getReward() {
		return reward;
	}
}
