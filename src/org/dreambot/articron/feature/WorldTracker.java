package org.dreambot.articron.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dreambot.api.utilities.Timer;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class WorldTracker {

    private static Map<Integer,Timer> trackMap;

    private static final int RESPAWN_MILLIS = 420000;

    static {
        trackMap = new ConcurrentHashMap<>();
    }

    public static boolean contains(int worldId) {
        return getTrackedWorldIds().contains(worldId);
    }

    public static List<Integer> getTrackedWorldIds() {
        List<Integer> worldList = new ArrayList<>();
        for (Map.Entry<Integer,Timer> entry : trackMap.entrySet()) {
            worldList.add(entry.getKey());
        }
        return worldList;
    }

    public static void addWorld(int worldId) {
        trackMap.put(worldId, new Timer());
    }

    public static void resetWorld(int worldId) {
        if (trackMap.containsKey(worldId)) {
            trackMap.get(worldId).reset();
        } else {
            addWorld(worldId);
        }
    }

    public static int getSecondsForSpawn(int worldId) {
        return ScriptMath.millisToSeconds(RESPAWN_MILLIS - trackMap.get(worldId).elapsed());
    }

    public static int getSpawningWorld() {
        for (Map.Entry<Integer,Timer> entry : trackMap.entrySet()) {
            int spawnTime = getSecondsForSpawn(entry.getKey());
            if (spawnTime < 5) {
                return entry.getKey();
            }
        }
        return -1;
    }


}
