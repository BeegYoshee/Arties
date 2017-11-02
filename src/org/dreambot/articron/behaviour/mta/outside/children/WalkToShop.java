package org.dreambot.articron.behaviour.mta.outside.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class WalkToShop extends Node {
    @Override
    public String getStatus() {
        return "Walking to MTA shop";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
            GameObject stairs = context.getDB().getGameObjects().closest(10775);
            if (stairs != null) {
                if (stairs.distance() > 8) {
                    if (context.getDB().getWalking().walk(stairs)) {
                        MethodProvider.sleepUntil(() -> stairs.distance() <= 8, ScriptMath.getTravelTime(stairs,1D));
                    }
                }

                if (stairs.interact("Climb-up")) {
                    MethodProvider.sleepUntil(() -> context.getDB().getLocalPlayer().getTile().getZ() == 1, ScriptMath.getTravelTime(stairs,1D));
                }
            }
        return 600;
    }
}
