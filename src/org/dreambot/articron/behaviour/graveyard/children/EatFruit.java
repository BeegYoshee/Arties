package org.dreambot.articron.behaviour.graveyard.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class EatFruit extends Node{
    @Override
    public String getStatus() {
        return "Eating fruit";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        while (context.getMTA().getGraveyardHandler().getHPPercent() < context.getMTA().getGraveyardHandler().getEatThreshold()) {
            if (!context.getDB().getInventory().contains("Banana", "Peach")) {
                break;
            }
            Item fruit = context.getDB().getInventory().get("Banana","Peach");
            int count = context.getDB().getInventory().count((i -> i.getName().equals("Banana") || i.getName().equals("Peach")));
            if (!context.getDB().getTabs().isOpen(Tab.INVENTORY)) {
                if (context.getDB().getTabs().open(Tab.INVENTORY)) {
                    MethodProvider.sleepUntil(() -> context.getDB().getTabs().isOpen(Tab.INVENTORY),100);
                }
            }
            if (fruit != null && fruit.interact("Eat")) {
                MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().count((i -> i.getName().equals("Banana") || i.getName().equals("Peach"))), 500);
            }
        }
        return 200;
    }
}
