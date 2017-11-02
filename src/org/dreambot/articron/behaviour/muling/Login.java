package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class Login extends Node {
    @Override
    public String getStatus() {
        return "Logging in";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        context.getRandomManager().enableSolver(RandomEvent.LOGIN);
        return 0;
    }
}
