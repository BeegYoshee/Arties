package org.dreambot.articron.behaviour.graveyard.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class ConvertBones extends Node {
    @Override
    public String getStatus() {
        return "Casting B2B";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getMagic().castSpell(context.getMTA().getGraveyardHandler().getGraveyardSpell())) {
            MethodProvider.sleepUntil(() -> !context.getDB().getInventory().contains("Bones"),500);
        }
        return 1000;
    }
}
