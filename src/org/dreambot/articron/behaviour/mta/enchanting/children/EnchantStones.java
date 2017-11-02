package org.dreambot.articron.behaviour.mta.enchanting.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class EnchantStones extends Node {

    @Override
    public String getStatus() {
        return "Enchanting stones";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        int count = context.getDB().getInventory().count(6903);
        if (context.getDB().getMagic().castSpell(context.getMTA().getEnchantingHandler().getEnchantSpell())) {
            if (context.getDB().getInventory().getRandom(6903).interact()) {
                MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().count(6903),2000);
            }
        }
        return 600;
    }
}
