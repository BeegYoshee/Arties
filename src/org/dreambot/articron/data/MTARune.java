package org.dreambot.articron.data;

/**
 * Author: Articron
 * Date:   20/10/2017.
 */
public enum MTARune {

    AIR_RUNE("Air rune", "https://vignette.wikia.nocookie.net/2007scape/images/b/bf/Air_rune.png/revision/latest?cb=20151107053227"),
    WATER_RUNE("Water rune", "https://vignette.wikia.nocookie.net/2007scape/images/d/d1/Water_rune.png/revision/latest?cb=20151107053236"),
    EARTH_RUNE("Earth rune", "https://vignette.wikia.nocookie.net/2007scape/images/d/d2/Earth_rune.png/revision/latest?cb=20151107053231"),
    FIRE_RUNE("Fire rune", "https://vignette.wikia.nocookie.net/2007scape/images/a/a2/Fire_rune.png/revision/latest?cb=20151107053231"),
    COSMIC_RUNE("Cosmic rune", "https://vignette.wikia.nocookie.net/2007scape/images/a/ae/Cosmic_rune.png/revision/latest?cb=20151107053229"),
    NATURE_RUNE("Nature rune", "https://vignette.wikia.nocookie.net/2007scape/images/8/88/Nature_rune.png/revision/latest?cb=20151107053234"),
    LAW_RUNE("Law rune", "https://vignette.wikia.nocookie.net/2007scape/images/f/f6/Law_rune.png/revision/latest?cb=20151107053232"),
    SOUL_RUNE("Soul rune", "https://vignette.wikia.nocookie.net/2007scape/images/9/9b/Soul_rune.png/revision/latest?cb=20151107053235"),
    BLOOD_RUNE("Blood rune", "https://vignette.wikia.nocookie.net/2007scape/images/2/2a/Blood_rune.png/revision/latest?cb=20151107053228");

    MTARune(String name, String link) {
        this.name = name;
        this.link = link;
    }

    private String name, link;

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
