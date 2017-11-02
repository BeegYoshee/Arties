package org.dreambot.articron.util;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.magic.cost.Rune;
import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.data.RuneRequirement;
import org.dreambot.articron.fw.ScriptContext;

import java.util.ArrayList;
import java.util.List;

public class TradingUtil {

    private ScriptContext context;

    private List<RuneRequirement> runesToTrade = new ArrayList<>();

    public TradingUtil(ScriptContext context) {
        this.context = context;
    }

    public void addRunesToTrade(MTARune rune, int amount) {
        runesToTrade.add(new RuneRequirement(rune,amount));
    }

    public boolean hasSupplies() {
        for (RuneRequirement rune : runesToTrade) {
            int count = context.getDB().getInventory().count(rune.getRune().getName());
            if (count < rune.getAmount()) {
                return false;
            }
        }
        return true;
    }


    public boolean offerComplete() {
        if (!context.getDB().getTrade().isOpen()) {
            return false;
        }
        for (RuneRequirement rune : runesToTrade) {
            if (!context.getDB().getTrade().contains(true,rune.getRune().getName())) {
                return false;
            }
            int count = context.getDB().getTrade().getItem(true,rune.getRune().getName()).getAmount();
            if (count < rune.getAmount()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOfferingMTAReward() {
        for (Reward reward : Reward.values()) {
            if (reward.shouldMule()) {
                if (context.getDB().getTrade().contains(false,reward.getID())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean offerRunes() {
        if (!context.getDB().getTrade().isOpen(1)) {
            return false;
        }
        if (offerComplete()) {
            return true;
        }
        for (RuneRequirement rune : runesToTrade) {
            int amount = (!context.getDB().getTrade().contains(true,rune.getRune().getName())) ? rune.getAmount() :
                    rune.getAmount() - context.getDB().getTrade().getItem(true,rune.getRune().getName()).getAmount();
            if (amount > 0) {
                if (context.getDB().getTrade().addItem(rune.getRune().getName(), amount)) {
                    MethodProvider.sleepUntil(() -> context.getDB().getTrade().contains(true, rune.getRune().getName()), 2000);
                }
            }
        }
        return true;
    }


}
