package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

import java.awt.*;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class DialogueHandler extends Node {
    @Override
    public String getStatus() {
        return "Dialogue";
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

        Dialogues d = context.getDB().getDialogues();
            if (d.canContinue()) {
                d.spaceToContinue();
            } else {
                if (d.chooseOption(d.getOptionIndexContaining("I'm new to this place. Where am I?"))) {
                    return 100;
                }
                if (d.chooseOption(d.getOptionIndexContaining("bye"))) {
                    return 100;
                }
                else {
                    context.getDB().getDialogues().clickOption(1);
                }
            }
        return 100;
    }
}
