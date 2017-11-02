package org.dreambot.articron.behaviour.muling;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class TradeSupplies extends Node {

    @Override
    public String getStatus() {
        return "Trading supplies";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {

        return 0;
    }
}
