package org.dreambot.articron.behaviour.alchemy.children;

import java.awt.*;

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
public class LootItem extends Node {
    @Override
    public String getStatus() {
        return "Grabbing alch item";
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

        final GameObject cupboard = context.getMTA().getAlchemyHandler().getBestCupboard();
        int count = context.getDB().getInventory().getEmptySlots();
        if (cupboard != null && cupboard.exists()) {

            if (cupboard.distance() > 2) {
               if (cupboard.interact("Search")) {
                   MethodProvider.sleepUntil(() -> cupboard.getTile().distance() <= 2, ScriptMath.getTravelTime(cupboard, 0.5D));
               } else {
                   if (context.getDB().getWalking().walkExact(context.getMTA().getAlchemyHandler().getAdjacentTile(cupboard))) {
                       MethodProvider.sleepUntil(() -> cupboard.getTile().distance() <= 2, ScriptMath.getTravelTime(cupboard, 0.5D));
                       return execute(context);
                   }
               }
                return 600;
            }
            Rectangle hulls = cupboard.getModel().getHullBounds(0.5f).getBounds2D().getBounds();
            if (context.getDB().getMouse().move(hulls)) {
                if (context.getDB().getClient().getMenu().getDefaultAction().contains("Search")) {
                    if (context.getDB().getMouse().click()) {
                        MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().getEmptySlots(), Calculations.random(50, 600));
                    }
                } else {
                    if (context.getDB().getClient().getMenu().clickAction("Search")) {
                        MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().getEmptySlots(), Calculations.random(50, 600));
                    }
                }

            }
        }
        return 600;
    }
}
