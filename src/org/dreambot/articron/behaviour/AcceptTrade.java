package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class AcceptTrade extends Node {
    @Override
    public String getStatus() {
        return "Accepting trade";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getTrade().getTradingWith()
                .equals(context.getMTA().getMuleQueue().getCurrentRequest().getBotName())) {
            if (context.getDB().getTrade().acceptTrade()) {
                MethodProvider.sleep(1000);
            }
        } else {
            if (context.getDB().getTrade().declineTrade()) {
                MethodProvider.sleep(1000);
            }
        }
        return 1000;
    }
}
