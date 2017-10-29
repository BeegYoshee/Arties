package org.dreambot.articron.behaviour.graveyard.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class DepositFruit extends Node {
    @Override
    public String getStatus() {
        return "Depositing fruit";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        GameObject chute = context.getDB().getGameObjects().closest("Food chute");
        if (chute != null && chute.interact("Deposit")) {
            if (MethodProvider.sleepUntil((() -> context.getDB().getLocalPlayer().isMoving()), Calculations.random(600,800))) {
                MethodProvider.sleepUntil(() -> !context.getDB().getInventory().contains("Banana", "Peach"), ScriptMath.getTravelTime(chute, 0.4D));
            } else {
                MethodProvider.log("Breaking cuz not moving");
            }
        }
        return 200;
    }
}
