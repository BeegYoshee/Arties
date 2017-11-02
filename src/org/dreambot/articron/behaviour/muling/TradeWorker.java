package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class TradeWorker extends Node {

    @Override
    public String getStatus() {
        return "Trading worker";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMuleHandler().tradeWorker()) {
            MethodContext.sleepUntil(() -> context.getDB().getTrade().isOpen(),5000);
        }
        return 600;
    }
}
