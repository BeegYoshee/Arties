package org.dreambot.articron.behaviour.telekinetic.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

import java.awt.*;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class ObserveMaze extends Node {

    @Override
    public String getStatus() {
        return "Observing maze";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        NPC mazeGuard = context.getDB().getNpcs().closest(6777);

        if (mazeGuard != null) {
            if (context.getDB().getMagic().isSpellSelected()) {
                context.getDB().getMouse().click(new Point(Calculations.random(0,517),Calculations.random(0,337)));
            }
            if (mazeGuard.interact("Observe")) {
                MethodProvider.sleepUntil(() -> context.getMTA().getTelekineticHandler().isObserving(), 2000);
            }
        }
        if (context.getMTA().getTelekineticHandler().isObserving()) {
            context.getMTA().getTelekineticHandler().getSolver().readMaze();
        }
        return 600;
    }
}
