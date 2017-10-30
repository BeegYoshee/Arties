package org.dreambot.articron.behaviour.telekinetic.children;

import java.awt.Point;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class RunToCastTile extends Node {
    @Override
    public String getStatus() {
        return "Walking to tile";
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public int execute(ScriptContext context) {

        if (context.getDB().getMagic().isSpellSelected()) {
            context.getDB().getMouse().click(new Point(Calculations.random(0,517),Calculations.random(0,337)));
        }

        Tile toBe = context.getMTA().getTelekineticHandler().getSolver().getCastTile();
        if (toBe != null) {
            if (context.getDB().getWalking().walkExact(toBe)) {
                MethodProvider.sleep(Calculations.random(600,800));
                MethodProvider.sleepUntil(
                        () -> context.getDB().getLocalPlayer().isMoving(), 600
                );
            }
            Tile dest = context.getDB().getClient().getDestination();
            if (dest != null) {
                if (context.getMTA().getTelekineticHandler().getSolver().getCorrectRow().contains(dest)) {
                    Tile toGo = context.getMTA().getTelekineticHandler().getSolver().getExpectedLocationTile().getWorldTile();
                    if (toGo != null) {
                        if (context.getDB().getMagic().castSpell(Normal.TELEKINETIC_GRAB)) {
                            if (context.getDB().getMouse().move(toGo)) {

                            }
                        }
                    }
                }
            }

            MethodProvider.sleepUntil(
                    () -> context.getMTA().getTelekineticHandler().getSolver().isInCorrectRow() || !context.getDB().getLocalPlayer().isMoving(), ScriptMath.getTravelTime(toBe,0.8D)
            );
        }

        return 100;
    }
}
