package org.dreambot.articron.behaviour.graveyard.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class LootBones extends Node {
    @Override
    public String getStatus() {
        return "Looting bones";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        GameObject bones = context.getMTA().getGraveyardHandler().getPileOfBones();
        if (bones != null) {
            if (context.getDB().getLocalPlayer().getHealthPercent() < context.getMTA().getGraveyardHandler().getEatThreshold()) {
                for (int i = 0; i < 5; i++) {
                    if (bones.interact("Grab")) {
                        MethodProvider.sleep(Calculations.random(100, 300));
                    }
                }
            } else {
                if (bones.interact("Grab")) {
                    MethodProvider.sleep(Calculations.random(100, 300));
                }
            }
        }
        return 200;
    }
}
