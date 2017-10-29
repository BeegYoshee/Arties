package org.dreambot.articron.ui.panels.misc;

import java.awt.BorderLayout;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.swing.HPanel;

public class MiscellaneousPanel extends HPanel {
	private MulePanel mulePanel;
	private SelectionPanel roomPanel;
	private NotificationPanel notificationPanel;

	public MiscellaneousPanel(Border border) {
		super(new BorderLayout(), border);
		HPanel split = new HPanel(new BorderLayout());
		add(split, BorderLayout.CENTER);
		split.add(mulePanel = new MulePanel(), BorderLayout.CENTER);
		split.add(roomPanel = new SelectionPanel(), BorderLayout.SOUTH);
		roomPanel.setBorder(new EmptyBorder(0, 2, 2, 0));
		add(notificationPanel = new NotificationPanel(), BorderLayout.EAST);
	}

	public MulePanel getMulePanel() {
		return mulePanel;
	}

	public SelectionPanel getRoomPanel() {
		return roomPanel;
	}

	public NotificationPanel getNotificationPanel() {
		return notificationPanel;
	}

}
