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
        return "Solving maze";
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
