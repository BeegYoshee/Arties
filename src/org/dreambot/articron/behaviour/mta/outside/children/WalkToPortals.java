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
public class WalkToPortals extends Node{

    @Override
    public String getStatus() {
        return "Returning to portals";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        GameObject stairs = context.getDB().getGameObjects().closest(10776);
        if (stairs != null) {
            if (stairs.distance() > 6) {
               if (context.getDB().getWalking().walk(stairs)) {
                   MethodProvider.sleepUntil(() -> stairs.distance() <= 6, ScriptMath.getTravelTime(stairs,0.5D));
               }
            }
            if (stairs.interact("Climb-down")) {
                MethodProvider.sleepUntil(() -> context.getDB().getLocalPlayer().getTile().getZ() != 1, ScriptMath.getTravelTime(stairs,1.0D));
            }
        }
        return 600;
    }
}
