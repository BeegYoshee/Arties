package org.dreambot.articron.util;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.Entity;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class ScriptMath {

    public static Long getTravelTime(Entity object, double weight) {
        double distance = (object == null) ? 1 : object.distance();
        return Math.round(distance * (1000 * weight));
    }

    public static Long getTravelTime(Tile tile, double weight) {
        double distance = (tile == null) ? 1 : tile.distance();
        return Math.round(distance * (1000 * weight));
    }

    public static int millisToSeconds(long millis) {
        return Math.round(millis / 1000);
    }

    public static int getPercentage(int part, int total) {
        int percent = (( part * 100) / total);
        return percent > 100 ? 100 : percent;
    }

}
