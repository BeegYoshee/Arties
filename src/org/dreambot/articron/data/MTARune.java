package org.dreambot.articron.data;

/**
 * Author: Articron
 * Date:   20/10/2017.
 */
public enum MTARune {

    AIR_RUNE("Air rune",556, "https://vignette.wikia.nocookie.net/2007scape/images/b/bf/Air_rune.png/revision/latest?cb=20151107053227"),
    WATER_RUNE("Water rune", 555,"https://vignette.wikia.nocookie.net/2007scape/images/d/d1/Water_rune.png/revision/latest?cb=20151107053236"),
    EARTH_RUNE("Earth rune", 557,"https://vignette.wikia.nocookie.net/2007scape/images/d/d2/Earth_rune.png/revision/latest?cb=20151107053231"),
    FIRE_RUNE("Fire rune", 554,"https://vignette.wikia.nocookie.net/2007scape/images/a/a2/Fire_rune.png/revision/latest?cb=20151107053231"),
    COSMIC_RUNE("Cosmic rune", 564,"https://vignette.wikia.nocookie.net/2007scape/images/a/ae/Cosmic_rune.png/revision/latest?cb=20151107053229"),
    NATURE_RUNE("Nature rune",561, "https://vignette.wikia.nocookie.net/2007scape/images/8/88/Nature_rune.png/revision/latest?cb=20151107053234"),
    LAW_RUNE("Law rune", 563,"https://vignette.wikia.nocookie.net/2007scape/images/f/f6/Law_rune.png/revision/latest?cb=20151107053232"),
    SOUL_RUNE("Soul rune",566, "https://vignette.wikia.nocookie.net/2007scape/images/9/9b/Soul_rune.png/revision/latest?cb=20151107053235"),
    BLOOD_RUNE("Blood rune", 565,"https://vignette.wikia.nocookie.net/2007scape/images/2/2a/Blood_rune.png/revision/latest?cb=20151107053228");

    MTARune(String name, int id, String link) {
        this.name = name;
        this.id = id;
        this.link = link;
    }

    private String name, link;
    private int id;
    
    public int getID() {
    	return id;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        String[] share = this.name().split("_");
        StringBuilder name = new StringBuilder();
        for (String s : share) {
            name.append(s.charAt(0)).append(s.substring(1).toLowerCase()).append(" ");
        }
        return name.toString();
    }
    
	public static MTARune reverseSearch(String name) {
		for (MTARune rune : MTARune.values()) {
			if (rune.getName().equals(name))
				return rune;
		}
		return null;
	}
}
