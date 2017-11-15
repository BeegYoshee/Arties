package org.dreambot.articron.ui.mule.panels.information;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.border.EmptyBorder;

import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.data.ScriptMode;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.swing.special.HDragList;
import org.dreambot.articron.ui.mule.MuleUI;
import org.dreambot.articron.ui.mule.panels.reward.MuleItem;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MainInfoPanel extends HPanel {

	private MainCombinedPanel mainCombinedPanel;

	public MainInfoPanel(MuleUI muleUI, MuleRunePanel panel) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		add(mainCombinedPanel = new MainCombinedPanel(panel), BorderLayout.CENTER);
		add(new HButton("Start", listener -> {

            muleUI.getContext().setMuleLocation(MuleLocation.values()[mainCombinedPanel.getSettings().getMuleLocation()]);

            MuleItem[] muleItems = panel.getQueuedRewards();
            for (MuleItem item : muleItems) {
                muleUI.getContext().getMuleHandler().getTrading().addRunesToTrade(item.getRune(),item.getAmount());
            }
            int port = mainCombinedPanel.getSettings().getPort();
            muleUI.getContext().setMuleServer(port,"kdORjh/e^^zd///===");
            muleUI.getContext().getMuleServer().start();
            muleUI.getContext().loadMode(ScriptMode.MULE);
            muleUI.getContext().setShouldPaint(true);

            muleUI.dispose();

        }), BorderLayout.SOUTH);
	}

	public MainCombinedPanel getMainCombinedPanel() {
		return mainCombinedPanel;
	}

}
