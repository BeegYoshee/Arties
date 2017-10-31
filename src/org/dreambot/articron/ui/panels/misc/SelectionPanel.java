package org.dreambot.articron.ui.panels.misc;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.swing.special.HDragList;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas Date: 18.10.2017 Alias: Dinh Time: 23:02
 */

public class SelectionPanel extends HPanel {
	private HDragList<RoomObject> list;

	public SelectionPanel() {
		setLayout(new BorderLayout());
		add(new HLabel("Room Order", JLabel.CENTER, new Font("Arial", Font.PLAIN, 17)), BorderLayout.NORTH);
		add(list = new HDragList<>(new RoomObject(MTARoom.TELEKINETIC.getPortalName()),
				new RoomObject(MTARoom.ENCHANTING.getPortalName()), new RoomObject(MTARoom.ALCHEMY.getPortalName()),
				new RoomObject(MTARoom.GRAVEYARD.getPortalName())), BorderLayout.CENTER);
		list.setBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 1));
		setPreferredSize(new Dimension(0, 140));
	}

	private class RoomObject {
		private String name;

		public RoomObject(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public HDragList<RoomObject> getList() {
		return list;
	}

	public MTARoom[] getRoomOrder() {
		RoomObject[] rooms = new RoomObject[4];
		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = list.getModel().getElementAt(i);
		}
		MTARoom[] mtarooms = new MTARoom[4];
		for (int i = 0; i < rooms.length; i++) {
			mtarooms[i] = MTARoom.reverseSearch(rooms[i].toString());
		}
		return mtarooms;
	}
}
