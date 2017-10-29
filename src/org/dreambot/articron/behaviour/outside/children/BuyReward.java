package org.dreambot.articron.behaviour.outside.children;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class BuyReward extends Node {

    @Override
    public String getStatus() {
        return "Buying reward";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        int count = context.getMTA().getTotalPoints();

        if (context.getMTA().getMTAShop().buyReward(context.getMTA().getRewardQueue().getCurrentReward())) {
            MethodProvider.sleepUntil(() -> count != context.getMTA().getTotalPoints(), 3000);
        }
        if (count != context.getMTA().getTotalPoints()) {
            context.getMTA().getRewardQueue().markCurrentDone();
            context.getPaint().loadRewards();
        }
        return 600;
    }
}
