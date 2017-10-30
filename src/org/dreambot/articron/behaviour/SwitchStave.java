package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.articron.data.MTAStave;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class SwitchStave extends Node {
    @Override
    public String getStatus() {
        return "Switching staves";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        MTAStave stave = context.getMTA().getRoom(context.getMTA().getCurrentRoom()).getStave();
        Item staveItem = context.getDB().getInventory().get(stave.getName());
        if (staveItem != null) {
            if (!context.getDB().getTabs().isOpen(Tab.INVENTORY)) {
                if (context.getDB().getTabs().open(Tab.INVENTORY)) {
                    MethodProvider.sleepUntil(() -> context.getDB().getTabs().isOpen(Tab.INVENTORY),1000);
                }
            }
            if (staveItem.interact("Wield")) {
                MethodProvider.sleepUntil(() -> context.getDB().getEquipment().contains(stave.getName()), Calculations.random(600,800));
            }
        }
        return 600;
    }
}
