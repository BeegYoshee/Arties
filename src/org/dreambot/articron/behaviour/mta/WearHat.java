package org.dreambot.articron.behaviour.mta;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class WearHat extends Node {
    @Override
    public String getStatus() {
        return "Equipping hat";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        Item hat = context.getDB().getInventory().get("Progress hat");
        if (hat != null) {
            if (!context.getDB().getTabs().isOpen(Tab.INVENTORY)) {
                if (context.getDB().getTabs().open(Tab.INVENTORY)) {
                    MethodProvider.sleepUntil(() -> context.getDB().getTabs().isOpen(Tab.INVENTORY), 200);
                }
            }
            if (hat.interact("Wear")) {
                MethodProvider.sleep(Calculations.random(600,700));
            }
        }
        return 100;
    }
}
