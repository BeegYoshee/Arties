package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class LogoutMule extends Node{
    @Override
    public String getStatus() {
        return "Waiting for new mule request";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        context.getRandomManager().disableSolver(RandomEvent.LOGIN);
        context.getDB().getTabs().logout();
        return 600;
    }
}
