package org.dreambot.articron.behaviour.mta.mule_interactions;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class TradeMule extends Node{
    @Override
    public String getStatus() {
        return "Trading the mule";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        Player mule = context.getMuleHandler().getMule();
        if (mule != null) {
            if (mule.interact("Trade with")) {
                MethodProvider.sleepUntil(() -> context.getDB().getTrade().isOpen(),5000);
            }
        }
        return 600;
    }
}
