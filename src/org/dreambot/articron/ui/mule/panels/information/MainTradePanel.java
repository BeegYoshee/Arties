package org.dreambot.articron.ui.mule.panels.information;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;

import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HImageComboBox;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;
import org.dreambot.articron.ui.bot.panels.room.DisplayObject;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MainTradePanel extends HPanel {

	private HImageComboBox<DisplayObject> runeBox;

	public MainTradePanel(MuleRunePanel panel) {
		setLayout(new GridLayout(0, 1, 0, 10));
		MTARune[] runes;
		DisplayObject[] objects = new DisplayObject[(runes = MTARune.values()).length];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = new DisplayObject(runes[i].getName(), HImageLoader.loadImage(runes[i].getLink()));
		}
		HPanel extra = new HPanel(new BorderLayout());
		extra.add(new HLabel("Trade-off:"),BorderLayout.WEST);
		extra.add(runeBox = new HImageComboBox<>(objects), BorderLayout.CENTER);
		add(extra,BorderLayout.CENTER);
		runeBox.getEditor().setSpellIcon(new ImageIcon(new RewardIcon(objects[0].getImage())));
		add(new MainAddPanel(this, panel), BorderLayout.SOUTH);
		runeBox.addActionListener(listener -> {
			DisplayObject object = (DisplayObject) runeBox.getSelectedItem();
			runeBox.getEditor().setSpellIcon(new ImageIcon(new RewardIcon(object.getImage())));
		});
	}

	public MTARune getSelected() {
		DisplayObject object = (DisplayObject) runeBox.getSelectedItem();
		return MTARune.reverseSearch(object.toString());
	}

}
