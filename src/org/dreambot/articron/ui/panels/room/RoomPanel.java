package org.dreambot.articron.ui.panels.room;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;

public class RoomPanel extends HPanel {

	private MTARoomPanel telekinetic, alchemy, enchanting, graveyard;

	public RoomPanel(Border border) {
		super(new GridLayout(0, 2, 10, 10), border);
		add(telekinetic = new MTARoomPanel(getBorder(MTARoom.TELEKINETIC.getPortalName()), MTARoom.TELEKINETIC));
		add(alchemy = new MTARoomPanel(getBorder(MTARoom.ENCHANTING.getPortalName()), MTARoom.ENCHANTING));
		add(enchanting = new MTARoomPanel(getBorder(MTARoom.ALCHEMY.getPortalName()), MTARoom.ALCHEMY));
		add(graveyard = new MTARoomPanel(getBorder(MTARoom.GRAVEYARD.getPortalName()), MTARoom.GRAVEYARD));
	}

	private TitledBorder getBorder(String title) {
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG),
				title);
		border.setTitleColor(HFrame.FOREGROUND);
		return border;
	}

	public MTARoomPanel getTelekinetic() {
		return telekinetic;
	}

	public MTARoomPanel getAlchemy() {
		return alchemy;
	}

	public MTARoomPanel getEnchanting() {
		return enchanting;
	}

	public MTARoomPanel getGraveyard() {
		return graveyard;
	}
}
