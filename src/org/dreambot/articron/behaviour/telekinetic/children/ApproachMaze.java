package org.dreambot.articron.behaviour.telekinetic.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class ApproachMaze extends Node {


    @Override
    public String getStatus() {
        return "Approaching maze";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        GameObject wall = context.getDB().getGameObjects().closest(10755);
        if (context.getDB().getWalking().walk(wall.getTile())) {
            MethodProvider.sleepUntil(
                    () -> context.getDB().getGameObjects().closest(10755).distance() <= 1,
                    ScriptMath.getTravelTime(wall,0.2D)
            );
        }
        return 600;
    }
}
