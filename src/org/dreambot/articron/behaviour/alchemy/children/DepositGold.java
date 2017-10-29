package org.dreambot.articron.behaviour.alchemy.children;

import java.awt.Point;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */

public class DepositGold extends Node {
    @Override
    public String getStatus() {
        return "Depositing gold";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getMagic().isSpellSelected()) {
            context.getDB().getMouse().click(new Point(Calculations.random(0,517),Calculations.random(0,337)));
        }
        if (context.getMTA().getAlchemyHandler().depositCoins()) {

                MethodProvider.sleepUntil(() -> context.getDB().getInventory().count("Coins") == 0, 3000);

        }
        return 600;
    }
}
