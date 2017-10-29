package org.dreambot.articron.behaviour.alchemy.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class AlchItem extends Node{

    @Override
    public String getStatus() {
        return "Alch item";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        String itemName = context.getMTA().getAlchemyHandler().getBestItem().getName();
        int count = context.getDB().getInventory().count(itemName);
        if (context.getDB().getMagic().castSpell(context.getMTA().getAlchemyHandler().getAlchemySpell())) {
            if (context.getDB().getInventory().getRandom(itemName).interact()) {
                MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().count(itemName),2000);
            }
        }
        return 0;
    }
}
