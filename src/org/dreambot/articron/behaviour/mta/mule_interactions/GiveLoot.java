package org.dreambot.articron.behaviour.mta.mule_interactions;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;

public class GiveLoot extends Node {
    @Override
    public String getStatus() {
        return "Offering loot";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        Item[] rewardItems = context.getMTA().getRewardsInInventory();
        for (Item reward : rewardItems) {
            int count = context.getDB().getInventory().count(reward.getName());
            if (context.getDB().getTrade().addItem(reward.getName(), reward.getAmount())) {
                MethodProvider.sleepUntil(() -> count != context.getDB().getInventory().count(reward.getName()), 5000);
            }
        }
        return 600;
    }
}
