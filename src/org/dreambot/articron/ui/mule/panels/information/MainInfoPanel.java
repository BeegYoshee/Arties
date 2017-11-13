package org.dreambot.articron.ui.mule.panels.information;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.ui.mule.MuleUI;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MainInfoPanel extends HPanel {

	private MainCombinedPanel mainCombinedPanel;

	public MainInfoPanel(MuleUI muleUI, MuleRunePanel panel) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		add(mainCombinedPanel = new MainCombinedPanel(panel), BorderLayout.CENTER);
		add(new HButton("Start", listener -> muleUI.dispose()), BorderLayout.SOUTH);
	}

	public MainCombinedPanel getMainCombinedPanel() {
		return mainCombinedPanel;
	}

}
