package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class GiveSupplies extends Node {

    @Override
    public String getStatus() {
        return "Trading over supplies";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMuleHandler().getTrading().offerRunes()) {
            MethodProvider.sleepUntil(() -> context.getMuleHandler().getTrading().offerComplete(),1000);
        }
        return 0;
    }
}
