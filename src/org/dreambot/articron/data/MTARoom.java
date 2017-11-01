package org.dreambot.articron.data;

import java.awt.*;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public enum MTARoom {

    TELEKINETIC("Telekinetic Teleport", 624,0,0xFFF,new Color(101, 139, 255)),
    ENCHANTING("Enchanters Teleport",625,0,0x3FFF,new Color(255, 71, 66)),
    ALCHEMY("Alchemists Teleport",624,13,0x1FFF,new Color(220, 195, 62)),
    GRAVEYARD("Graveyard Teleport", 625,15,0xFFF,new Color(199, 127, 82));

    MTARoom(String portalId, int config, int shift, int mask, Color paintColor) {
        this.portalId = portalId;
        this.config = config;
        this.shift = shift;
        this.mask = mask;
        this.paintColor = paintColor;
    }

    private int config, shift, mask;
    private String portalId;
    private Color paintColor;


    public int getConfig() {
        return config;
    }

    public int getShift() {
        return shift;
    }

    public int getMask() {
        return mask;
    }

    public Color getPaintColor() {
        return paintColor;
    }

    public String getPortalName() {
        return portalId;
    }
	public static MTARoom reverseSearch(String name) {
		for (MTARoom room : MTARoom.values()) {
			if (room.getPortalName().equals(name))
				return room;
		}
		return null;
	}

	public static MTARoom forName(String name) {
        for (MTARoom room : MTARoom.values()) {
            if (room.name().equals(name)) {
                return room;
            }
        }
        return null;
    }
}
