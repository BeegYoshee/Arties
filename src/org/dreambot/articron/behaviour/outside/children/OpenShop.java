package org.dreambot.articron.behaviour.outside.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class OpenShop extends Node {
    @Override
    public String getStatus() {
        return "Opening shop";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getMTA().getMTAShop().canOpen()) {
            if (context.getMTA().getMTAShop().open()) {
                MethodProvider.sleepUntil(() -> context.getMTA().getMTAShop().isOpen(), Calculations.random(1000,3000));
            }
        }
        return 600;
    }
}
