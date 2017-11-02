package org.dreambot.articron.behaviour.mta.telekinetic.children;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class RunToFutureCastTile extends Node {
    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {

        return 600;
    }
}
