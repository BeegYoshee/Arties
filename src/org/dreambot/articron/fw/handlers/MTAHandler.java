package org.dreambot.articron.fw.handlers;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.feature.RewardQueue;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.util.MTAShop;
import org.dreambot.articron.util.ScriptMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class MTAHandler {

    private ScriptContext context;
    private MTAShop mtaShop;
    private RewardQueue rewardQueue;
    private AlchemyRoom alchemyHandler;
    private EnchantingRoom enchantingHandler;
    private TelekineticRoom telekineticHandler;
    private GraveyardRoom graveyardHandler;

    private List<Room> roomList = new ArrayList<>();

    public MTAHandler(ScriptContext context) {
        this.context = context;
        this.mtaShop = new MTAShop(context);
        this.alchemyHandler = new AlchemyRoom(context, MTARoom.ALCHEMY);
        this.telekineticHandler = new TelekineticRoom(context, MTARoom.TELEKINETIC);
        this.enchantingHandler = new EnchantingRoom(context, MTARoom.ENCHANTING);
        this.graveyardHandler = new GraveyardRoom(context, MTARoom.GRAVEYARD);
        roomList.addAll(Arrays.asList(alchemyHandler,telekineticHandler,enchantingHandler,graveyardHandler));
        this.rewardQueue = new RewardQueue();
    }

    public boolean hasProgressHat() {
        return ((context.getDB().getPlayerSettings().getConfig(628) >> 13) & 0b1) == 1;
    }

    public boolean isOutside() {
        return getCurrentRoom() == null;
    }

    public MTARoom getCurrentRoom() {
        int id = ((context.getDB().getPlayerSettings().getConfig(625) >> 28) & 0x7);
        if (id == 0) {
            return null;
        }
        return MTARoom.values()[id - 1];
    }

    public boolean hasPeachSpellUnlocked() {
        return ((context.getDB().getPlayerSettings().getConfig(629) >> 22) & 0b1) == 1;
    }

    public int getPizzazPoints(MTARoom room) {
        return ((context.getDB().getPlayerSettings().getConfig(room.getConfig()) >> room.getShift()) & room.getMask());
    }

    public boolean usePortal(MTARoom room, boolean enter) {
        if (enter != isOutside()) {
            return false;
        }
        GameObject portal = context.getDB().getGameObjects().closest(enter ? room.getPortalName() : "Exit Teleport");
        if (portal != null && portal.exists()) {
            if (portal.distance() > 10) {
                if (context.getDB().getWalking().walk(portal)) {
                    MethodProvider.sleepUntil(() -> context.getDB().getWalking().getDestination().distance() < 3, ScriptMath.getTravelTime(portal,0.8D));
                    return usePortal(room,enter);
                }

            }
            if (portal.interact("Enter")) {
                MethodProvider.sleepUntil(() -> enter != isOutside(), ScriptMath.getTravelTime(portal, 1.0));
            }
        }
        return enter != isOutside();
    }

    public boolean canBuyReward() {
        return getRoomToEnter() == null;
    }


    public MTARoom getRoomToEnter() {
        for (MTARoom room : MTARoom.values()) {
            if (getPizzazPoints(room) < getRewardQueue().getCurrentReward().getRequiredPoints(room)) {
                return room;
            }
        }
        return null;
    }

    public TelekineticRoom getTelekineticHandler() {
        return telekineticHandler;
    }

    public int getTotalPoints() {
        return context.getMTA().getPizzazPoints(MTARoom.ENCHANTING) +
                context.getMTA().getPizzazPoints(MTARoom.ALCHEMY) +
                context.getMTA().getPizzazPoints(MTARoom.TELEKINETIC) +
                context.getMTA().getPizzazPoints(MTARoom.GRAVEYARD);
    }

    public Room getRoom(MTARoom room) {
        for (Room r : roomList) {
            if (r.getRoom() == room) {
                return r;
            }
        }
        return null;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public MTAShop getMTAShop() {
        return mtaShop;
    }

    public AlchemyRoom getAlchemyHandler() {
        return alchemyHandler;
    }

    public RewardQueue getRewardQueue() {
        return rewardQueue;
    }

    public EnchantingRoom getEnchantingHandler() {
        return enchantingHandler;
    }

    public GraveyardRoom getGraveyardHandler() {
        return graveyardHandler;
    }
}
