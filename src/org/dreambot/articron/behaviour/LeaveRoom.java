package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.feature.WorldTracker;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

import java.awt.*;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class LeaveRoom extends Node {
    @Override
    public String getStatus() {
        return "Leaving room";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getMagic().isSpellSelected()) {
            context.getDB().getMouse().click(new Point(Calculations.random(0,517),Calculations.random(0,337)));
        }

        if (context.getMTA().usePortal(context.getMTA().getCurrentRoom(),false)) {
            MethodProvider.sleepUntil(() -> context.getMTA().isOutside(), 1000);
        }
        if (context.getMTA().isOutside() && WorldTracker.contains(context.getDB().getClient().getCurrentWorld())) {
            WorldTracker.resetWorld(context.getDB().getClient().getCurrentWorld());
        }
        return 600;
    }
}
