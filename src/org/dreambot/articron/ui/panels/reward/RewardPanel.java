package org.dreambot.articron.ui.panels.reward;

import org.dreambot.articron.data.Reward;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HBin;
import org.dreambot.articron.swing.child.HImageLabel;
import org.dreambot.articron.swing.child.HScrollPane;
import org.dreambot.articron.swing.special.HDragList;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by: Niklas Date: 18.10.2017 Alias: Dinh Time: 22:39
 */

public class RewardPanel extends HPanel {
	private HPanel split;
	private HDragList<RewardItem> dragList;

	public RewardPanel(Border border) {
		super(new BorderLayout(), border);

		split = new HPanel(new BorderLayout());
		split.add(
				new HBin<RewardItem>("Drag here to remove",
						HImageLoader.loadIconImage(
								"https://cdn4.iconfinder.com/data/icons/standard-free-icons/100/TrashBin-32.png"),
						5),
				BorderLayout.NORTH);
		split.add(new HScrollPane(dragList = new HDragList<>()), BorderLayout.CENTER);
		add(split, BorderLayout.CENTER);

		RewardSelection rewardSelection = new RewardSelection();
		for (HImageLabel imageLabel : rewardSelection.getImageLabels()) {
			imageLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					dragList.getDefaultListModel().addElement(new RewardItem((Reward) imageLabel.getObject()));
				}
			});
		}

		add(rewardSelection, BorderLayout.WEST);
	}

	public HDragList<RewardItem> getDragList() {
		return dragList;
	}

	public Reward[] getQueuedRewards() {
		Reward[] items = new Reward[dragList.getModel().getSize()];
		for (int i = 0; i < items.length; i++) {
			items[i] = dragList.getModel().getElementAt(i).getReward();
		}
		return items;
	}

	public int getPreferredWidth() {
		return split.getWidth() - 16;
	}
}
