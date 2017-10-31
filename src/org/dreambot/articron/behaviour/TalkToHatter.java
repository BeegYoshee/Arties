package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

public class TalkToHatter extends Node {
    @Override
    public String getStatus() {
        return "Getting progress hat";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        NPC entranceGuard = context.getDB().getNpcs().closest(5978);
        if (entranceGuard != null && entranceGuard.interact("Talk-to")) {//5978
            MethodProvider.sleepUntil(() -> context.getDB().getDialogues().inDialogue(), ScriptMath.getTravelTime(entranceGuard,0.5D));
        }
        return 100;
    }
}
