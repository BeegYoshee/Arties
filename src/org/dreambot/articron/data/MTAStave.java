package org.dreambot.articron.data;

/**
 * Author: Articron
 * Date:   20/10/2017.
 */
public enum MTAStave {

    NONE("None",null),
    AIR_STAFF("Staff of air","https://vignette.wikia.nocookie.net/2007scape/images/5/5f/Staff_of_air.png/revision/latest?cb=20160123055902", MTARune.AIR_RUNE),
    EARTH_STAFF("Staff of earth", "https://vignette.wikia.nocookie.net/2007scape/images/1/10/Staff_of_earth.png/revision/latest?cb=20160123055903", MTARune.EARTH_RUNE),
    WATER_STAFF("Staff of water","https://vignette.wikia.nocookie.net/2007scape/images/7/7c/Staff_of_water.png/revision/latest?cb=20160123055903", MTARune.WATER_RUNE),
    FIRE_STAFF("Staff of fire", "https://vignette.wikia.nocookie.net/2007scape/images/b/ba/Staff_of_fire.png/revision/latest?cb=20160123055903", MTARune.FIRE_RUNE),
    LAVA_STAFF("Lava battlestaff","https://vignette.wikia.nocookie.net/2007scape/images/5/53/Lava_battlestaff.png/revision/latest?cb=20160123055801", MTARune.EARTH_RUNE, MTARune.FIRE_RUNE),
    MUD_STAFF("Mud battlestaff", "https://vignette.wikia.nocookie.net/2007scape/images/7/72/Mud_battlestaff.png/revision/latest?cb=20160123055802", MTARune.WATER_RUNE, MTARune.EARTH_RUNE);

    MTAStave(String name, String link, MTARune... runes) {
        this.name = name;
        this.link = link;
        this.runes = runes;
    }

    private String name;
    private String link;
    private MTARune[] runes;

    public MTARune[] getRunes() {
        return runes;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
	public static MTAStave reverseSearch(String name) {
		for (MTAStave staff : MTAStave.values()) {
			if (staff.getName().equals(name))
				return staff;
		}
		return MTAStave.NONE;
	}
}
