package org.dreambot.articron.behaviour.telekinetic.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

import java.awt.*;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class EndMaze extends Node {
    @Override
    public String getStatus() {
        return "Maze ending";
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

        NPC guard = context.getDB().getNpcs().closest(6779);
        if (guard.interact("Talk-to")) {
            MethodProvider.sleepUntil(() -> context.getDB().getDialogues().inDialogue(),2000);
        }
        return 600;
    }
}
