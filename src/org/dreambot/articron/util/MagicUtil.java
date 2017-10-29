package org.dreambot.articron.util;

import org.dreambot.api.methods.magic.Spell;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class MagicUtil {

    public static Spell getBestForLevel(int magicLevel, Spell... spells) {
        Spell toReturn = null;
        for (Spell spell : spells) {
            if (spell.getLevel() < magicLevel) {
                toReturn = spell;
            }
        }
        return toReturn;
    }


}
