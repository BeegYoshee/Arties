package org.dreambot.articron.data;

public enum GraveyardBone {

    BONE_1(6904),
    BONE_2(6905),
    BONE_3(6906),
    BONE_4(6907);

    GraveyardBone(int itemId) {
        this.itemId = itemId;
    }

    private int itemId;

    public int getId() {
        return itemId;
    }

    public static int getBananaValue(int id) {
        for (GraveyardBone b : GraveyardBone.values()) {
            if (b.getId() == id) {
                return b.ordinal() + 1;
            }
        }
        return 0;
    }

}