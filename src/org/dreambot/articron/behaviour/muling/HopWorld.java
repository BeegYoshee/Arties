package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class HopWorld extends Node {
    @Override
    public String getStatus() {
        return "Hopping to mule";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        int world = context.getMTA().getMuleQueue().getCurrentRequest().getWorld();
        if (context.getDB().getWorldHopper().hopWorld(world)) {
            MethodProvider.sleepUntil(() -> context.getDB().getClient().getCurrentWorld() == world, 5000);
        }
        return 600;
    }
}
