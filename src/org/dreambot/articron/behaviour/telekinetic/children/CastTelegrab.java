package org.dreambot.articron.behaviour.telekinetic.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class CastTelegrab extends Node{
    @Override
    public String getStatus() {
        return "Casting telegrab";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMTA().getTelekineticHandler().castTelegrab()) {
            context.getMTA().getTelekineticHandler().getSolver().saveStatueLocation();
            MethodProvider.sleepUntil(() -> context.getDB().getNpcs().closest(6777) == null, 5000);
        }
        return 100;
    }
}
