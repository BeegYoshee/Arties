package org.dreambot.articron.fw.handlers;

import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.util.TradingUtil;

public class MuleHandler {

    private ScriptContext context;
    private TradingUtil tradeUtil;
    private String muleName;


    public MuleHandler(ScriptContext context) {
        this.context = context;
        tradeUtil = new TradingUtil(context);
    }

    public boolean hasAWorkerWaiting() {
        return context.getMTA().getMuleQueue().getCurrentRequest() != null;
    }

    public Player getMule() {
        return context.getDB().getPlayers().closest(p -> p.getName().equals(muleName));
    }

    public Player getWorker() {
        String playerName = context.getMTA().getMuleQueue().getCurrentRequest().getBotName();
        return context.getDB().getPlayers().closest(p -> p.getName().equals(playerName));
    }

    public boolean isInMulingSpot() {
        MuleLocation loc = context.getMuleLocation();
        if (loc == null) {
            return false;
        }

        if (!context.getDB().getClient().isLoggedIn()) {
            return false;
        }
        Player local = context.getDB().getLocalPlayer();
        if (loc == MuleLocation.MTA_UPSTAIRS) {
            return context.getMTA().isInsideMTABuilding() && local.getTile().getZ() == 1;
        }
        else {
            return loc.getArea().contains(local);
        }
    }

    public boolean isWorkerHere() {
       return getWorker() != null;
    }

    public boolean isMuleHere() {
        return getMule() != null;
    }

    public boolean shouldHop() {
        return context.getDB().getClient().getCurrentWorld() != context.getMTA().getMuleQueue().getCurrentRequest().getWorld();
    }

    public boolean tradeWorker() {
        Player worker = getWorker();
        if (!isWorkerHere()) {
            return false;
        }
        return worker.interact("Trade with");
    }

    public TradingUtil getTrading() {
        return tradeUtil;
    }

    public void setMuleName(String name) {
        this.muleName = name;
    }
}
