package org.dreambot.articron.data;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public enum AlchemyDrop {
    LEATHER_BOOTS(),
    ADAMANT_KITESHIELD(),
    ADAMANT_MED_HELM(),
    EMERALD(),
    RUNE_LONGSWORD(),
    EMPTY_1(),
    EMPTY_2(),
    EMPTY_3();

    public static AlchemyDrop forMessage(String message) {
        if (message.contains("empty")) {
            return AlchemyDrop.EMPTY_1;
        }
        message = message.replace("You found: ","");
        for (AlchemyDrop item : AlchemyDrop.values()) {
            if (item.getName().toLowerCase().equals(message.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public String getName() {
        return name().substring(0,1).toUpperCase() + name().substring(1).replace("_"," ").toLowerCase();
    }

}
