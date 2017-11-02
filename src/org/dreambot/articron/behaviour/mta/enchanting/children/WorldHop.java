package org.dreambot.articron.behaviour.mta.enchanting.children;

import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.world.World;
import org.dreambot.articron.feature.WorldTracker;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class WorldHop extends Node{
    @Override
    public String getStatus() {
        return "Hopping worlds";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMTA().usePortal(context.getMTA().getCurrentRoom(),false)) {
            MethodProvider.sleepUntil(() -> context.getMTA().isOutside(), 1000);
        }
        if (context.getMTA().isOutside()) {
            if (WorldTracker.getSpawningWorld() == context.getDB().getClient().getCurrentWorld()) {
                WorldTracker.resetWorld(context.getDB().getClient().getCurrentWorld());
                return 100;
            }
            if (WorldTracker.getSpawningWorld() != -1) {
                context.getDB().getWorldHopper().hopWorld(WorldTracker.getSpawningWorld());
            } else {
                World w = context.getDB().getWorlds().getRandomWorld(world -> !world.isPVP() && !world.isDeadmanMode() && !world.isF2P()
                        && world.getMinimumLevel() < context.getDB().getSkills().getTotalLevel() && !WorldTracker.getTrackedWorldIds().contains(world.getID()));
                context.getDB().getWorldHopper().hopWorld(w.getID());
                MethodProvider.sleep(1000);
                MethodProvider.sleepUntil(() -> context.getDB().getClient().getGameState() != GameState.HOPPING
                && context.getDB().getClient().getGameState() != GameState.LOADING, 5000);
            }
        }
        return 1000;
    }
}
