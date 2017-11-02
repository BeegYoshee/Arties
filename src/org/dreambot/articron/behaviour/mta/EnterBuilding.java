package org.dreambot.articron.behaviour.mta;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class EnterBuilding extends Node{
    @Override
    public String getStatus() {
        return "Going to MTA";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMTA().enterMTABuilding()) {
            MethodProvider.sleepUntil(() -> context.getMTA().isInsideMTABuilding(),5000);
        }
        return 600;
    }
}
