package org.dreambot.articron.behaviour.telekinetic.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

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
        Tile castLocation = context.getMTA().getTelekineticHandler().getSolver().getFutureCastLocation();
        if ( castLocation != null) {
            if (context.getDB().getWalking().walkExact(castLocation)) {
                MethodProvider.sleepUntil(() -> context.getDB().getLocalPlayer().getTile().equals(castLocation),
                        ScriptMath.getTravelTime(castLocation,2D));
            }
        }
        return 600;
    }
}
