package org.dreambot.articron.ui.mule.panels.information;

import java.awt.GridLayout;

import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MainCombinedPanel extends HPanel {

	private MainSettingPanel settings;

	public MainCombinedPanel(MuleRunePanel panel) {
		setLayout(new GridLayout(0, 1,0,10));
		add(settings = new MainSettingPanel());
		add(new MainTradePanel(panel));
	}

	public MainSettingPanel getSettings() {
		return settings;
	}

}
