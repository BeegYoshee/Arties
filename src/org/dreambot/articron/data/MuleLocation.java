package org.dreambot.articron.data;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;

public enum MuleLocation {
    MTA_UPSTAIRS(null),
    AL_KHARID_CHEST(new Area(new Tile(3380,3273), new Tile(3385,3266)));


    MuleLocation(Area area) {
        this.area = area;
    }

    private Area area;

    public Area getArea() {
        return area;
    }
}
