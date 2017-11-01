package org.dreambot.articron.data;

/**
 * Created by: Niklas
 * Date: 19.10.2017
 * Alias: Dinh
 * Time: 21:57
 */

public enum Reward {
 //boolean canMule
    BEGINNER_WAND(30, 30, 300, 30, "https://vignette.wikia.nocookie.net/2007scape/images/3/3d/Beginner_wand.png/revision/latest?cb=20140727070722",6908,false),
    APPRENTICE_WAND(60, 60, 600, 60, "https://vignette.wikia.nocookie.net/2007scape/images/5/54/Apprentice_wand.png/revision/latest?cb=20140727070721",6910,false),
    TEACHER_WAND(150, 200, 1500, 150, "https://vignette.wikia.nocookie.net/2007scape/images/5/55/Teacher_wand.png/revision/latest?cb=20140727070722",6912,false),
    MASTER_WAND(240, 240, 2400, 240, "https://vignette.wikia.nocookie.net/2007scape/images/4/45/Master_wand.png/revision/latest?cb=20140510172850",6914,true),
    INFINITY_TOP(400, 450, 4000, 400, "https://vignette.wikia.nocookie.net/2007scape/images/3/34/Infinity_top.png/revision/latest?cb=20131206213325",6916,true),
    INFINITY_HAT(350, 400, 3000, 350, "https://vignette.wikia.nocookie.net/2007scape/images/9/9b/Infinity_hat.png/revision/latest?cb=20131206213325",6918,true),
    INFINITY_BOOTS(120, 120, 1200, 120, "https://vignette.wikia.nocookie.net/2007scape/images/d/d8/Infinity_boots.png/revision/latest?cb=20131206213324",6920,true),
    INFINITY_GLOVES(175, 225, 1500, 175, "https://vignette.wikia.nocookie.net/2007scape/images/c/c2/Infinity_gloves.png/revision/latest?cb=20131206213324",6922,true),
    INFINITY_BOTTOM(450, 500, 5000, 450, "https://vignette.wikia.nocookie.net/2007scape/images/b/b7/Infinity_bottoms.png/revision/latest?cb=20131206213324",6924,true),
    MAGE_BOOK(500, 550, 6000, 500, "https://vignette.wikia.nocookie.net/2007scape/images/4/49/Mage%27s_book.png/revision/latest?cb=20131031184450",6889,true),
    BONES_TO_PEACHES(200, 300, 2000, 200, "https://vignette.wikia.nocookie.net/2007scape/images/f/fe/Bones_to_peaches_icon.png/revision/latest?cb=20130920213624",-1,false);


    Reward(int telePoints, int alchemyPoints, int enchantPoints, int graveyardPoints, String itemSprite, int id, boolean canMule) {
        this.telePoints = telePoints;
        this.enchantPoints = enchantPoints;
        this.graveyardPoints = graveyardPoints;
        this.alchemyPoints = alchemyPoints;
        this.itemSprite = itemSprite;
        this.id=id;
        this.muleable = canMule;
    }

    private int telePoints;
    private int enchantPoints;
    private int graveyardPoints;
    private int alchemyPoints;
    private String itemSprite;
    private boolean muleable;
    private int id;

    public boolean shouldMule() {
        return muleable;
    }

    public int getRequiredPoints(MTARoom room) {
        switch (room) {
            case TELEKINETIC:
                return telePoints;
            case GRAVEYARD:
                return graveyardPoints;
            case ALCHEMY:
                return alchemyPoints;
            case ENCHANTING:
                return enchantPoints;
        }
        return 0;
    }

    public int getID() {
    	return id;
    }
    
    public Object[][] getData() {
        return new Object[][]{{getRequiredPoints(MTARoom.TELEKINETIC), getRequiredPoints(MTARoom.ENCHANTING), getRequiredPoints(MTARoom.ALCHEMY), getRequiredPoints(MTARoom.GRAVEYARD)}};
    }
    public String getItemSprite() {
        return itemSprite;
    }

    public static Reward reverseSearch(String rewardName) {
        for (Reward r : Reward.values()) {
            if (r.name().equals(rewardName)) {
                return r;
            }
        }
        return null;
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
}