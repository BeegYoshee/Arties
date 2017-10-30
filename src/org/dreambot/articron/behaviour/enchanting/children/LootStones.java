package org.dreambot.articron.behaviour.enchanting.children;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.articron.feature.WorldTracker;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;


/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class LootStones extends Node {

    @Override
    public String getStatus() {
        return "Looting dragon stones";
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public int execute(ScriptContext context) {
        GroundItem stone = context.getMTA().getEnchantingHandler().getViableStone();
        if (stone != null && stone.distance() < 10) {
            if (stone.interact("Take")) {
                MethodProvider.sleepUntil(() -> !stone.exists(), ScriptMath.getTravelTime(stone, 0.5D));
                if (!context.getMTA().getEnchantingHandler().roomContainsStones()) {
                    WorldTracker.addWorld(context.getDB().getClient().getCurrentWorld());
                }
            }
        } else {
            if (stone != null) {
                if (context.getDB().getWalking().walk(stone.getTile())) {
                MethodProvider.sleepUntil(() -> !stone.exists() || stone.distance() < 10, ScriptMath.getTravelTime(stone,0.3D));
                }
            }
        }
        return Calculations.random(400,660);
    }
}
