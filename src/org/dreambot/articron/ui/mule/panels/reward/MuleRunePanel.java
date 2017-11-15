package org.dreambot.articron.ui.mule.panels.reward;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.border.Border;

import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HBin;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HScrollPane;
import org.dreambot.articron.swing.special.HDragList;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;

public class MuleRunePanel extends HPanel {
	private HPanel split;
	private HDragList<MuleItem> dragList;

	public MuleRunePanel(Border border) {
		super(new BorderLayout(), border);

		split = new HPanel(new BorderLayout());
		split.add(
				new HBin("Drag here to remove",
						HImageLoader.loadIconImage(
								"https://cdn4.iconfinder.com/data/icons/standard-free-icons/100/TrashBin-32.png"),
						5),
				BorderLayout.NORTH);
		split.add(new HScrollPane(dragList = new HDragList<>()), BorderLayout.CENTER);
		add(split, BorderLayout.CENTER);
	}

	public HDragList<MuleItem> getDragList() {
		return dragList;
	}

	public MuleItem[] getQueuedRewards() {
		MuleItem[] items = new MuleItem[dragList.getModel().getSize()];
		for (int i = 0; i < items.length; i++) {
			items[i] = dragList.getModel().getElementAt(i);
		}
		return items;
	}

	public int getPreferredWidth() {
		return split.getWidth() - 16;
	}
}
