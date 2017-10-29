package org.dreambot.articron.fw.handlers;

import java.util.Comparator;
import java.util.List;

import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.magic.Spell;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.ScriptContext;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class EnchantingRoom extends Room {

    private ScriptContext e;

    private final int DRAGONSTONE_ID = 6903;

    public EnchantingRoom(ScriptContext e, MTARoom room) {
        super(room);
        this.e = e;
    }

    public boolean roomContainsStones() {
        return getViableStone() != null;
    }

    public GroundItem getViableStone() {
        List<Player> players = e.getDB().getPlayers().all();
        if (players.size() == 1) {
            return e.getDB().getGroundItems().closest(DRAGONSTONE_ID);
        }
        if (!e.getDB().getWalking().isRunEnabled()) {
            e.getDB().getWalking().toggleRun();
        }
        List<GroundItem> stones = e.getDB().getGroundItems().all(DRAGONSTONE_ID);
        if (stones.size() == 0) return null;
        stones.sort(Comparator.comparingDouble(Entity::distance));
        players.sort(Comparator.comparingDouble(p -> p.distance(stones.get(0))));
        return stones.size() < 2 ? null : stones.get(e.getDB().getLocalPlayer().equals(players.get(0)) ? 0 : 1);
    }

    public boolean hasStones() {
        return e.getDB().getInventory().contains(DRAGONSTONE_ID);
    }

    public Spell getEnchantSpell() {
        return Normal.LEVEL_4_ENCHANT;
    }
}
