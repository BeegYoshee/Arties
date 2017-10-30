package org.dreambot.articron.behaviour;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class EnableRunning extends Node {
    @Override
    public String getStatus() {
        return "Enabling run";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getWalking().toggleRun()) {
            return 100;
        }
        return 1;
    }
}
