package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.Calculations;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

import java.awt.*;

public class EnableRunning extends Node {
    @Override
    public String getStatus() {
        return "Enabling run";
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

        if (context.getDB().getWalking().toggleRun()) {
            return 100;
        }
        return 1;
    }
}
