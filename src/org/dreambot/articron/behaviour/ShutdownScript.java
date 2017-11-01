package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class ShutdownScript extends Node{
    @Override
    public String getStatus() {
        return "shutdown";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        MethodProvider.log("Shutting down script");
        return -1;
    }
}
