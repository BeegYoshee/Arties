package org.dreambot.articron.behaviour;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class PortalEnter extends Node {
    @Override
    public String getStatus() {
        return "Entering MTA room";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        MTARoom room = context.getMTA().getRoomToEnter();
        if (room != null) {
            if (context.getMTA().usePortal(room, true)) {
                MethodProvider.sleepUntil(() -> !context.getMTA().isOutside(), 5000);
            }
        }
        return 600;
    }
}
