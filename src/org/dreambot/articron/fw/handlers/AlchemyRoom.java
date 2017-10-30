package org.dreambot.articron.fw.handlers;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.magic.Spell;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.data.AlchemyDrop;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class AlchemyRoom extends Room {


    private ScriptContext e;
    private AlchemyDrop foundItem;
    private MTARoom room;
    private Tile foundCupboard;

    private final Tile[] CUPBOARD_LOCATIONS = {
            new Tile(3369,9644,2), //ADDY_KITE
            new Tile(3369,9640,2),
            new Tile(3369,9636,2), //here, EMERALD is found
            new Tile(3369,9632,2), //RUNE_LONG
            new Tile(3360,9632,2), //EMPTY
            new Tile(3360,9636,2),//EMPTY
            new Tile(3360,9640,2),//EMPTY
            new Tile(3360,9644,2)//LEATHER BOOT
    };

    public AlchemyRoom(ScriptContext e, MTARoom room) {
        super(room);
        this.e = e;
    }

    public Tile getAdjacentTile(GameObject cupboard) {
        Tile loc = cupboard.getTile();
        loc.setX(loc.getX() + (loc.getX() == 3360 ? 1 : -1));
        return loc;
    }

    public void setFoundItem(AlchemyDrop found, Tile cupboardLocation) {
        this.foundItem = found;
        this.foundCupboard = cupboardLocation;
    }

    public GameObject getBestCupboard() {
        if (foundItem == null ||foundCupboard == null) {
            return e.getDB().getGameObjects().closest("Cupboard");
        }
        if (foundItem == AlchemyDrop.EMPTY_1) {
            return getFilledCupboard();
        }
        int cupboardIndex = indexOf(foundCupboard,CUPBOARD_LOCATIONS); //cupboard [2]
        int foundIndex = foundItem.ordinal(); //[3] = EMERALDS
        int goalIndex = getBestItem().ordinal(); // [1] = ADAMANT_KITESHIELD
        int offset = goalIndex - foundIndex; //1 - 3 = -2
        int destIndex = cupboardIndex + offset;
        if (destIndex > 7)
            destIndex -= 8;
        if (destIndex < 0)
            destIndex += 8; // = 7
        return e.getDB().getGameObjects().getTopObjectOnTile(CUPBOARD_LOCATIONS[destIndex]);
    }

    private GameObject getFilledCupboard() {
        int foundCupboard = indexOf(e.getDB().getGameObjects().closest("Cupboard").getTile(),CUPBOARD_LOCATIONS);
        return e.getDB().getGameObjects().getTopObjectOnTile(
                CUPBOARD_LOCATIONS[(foundCupboard >= 3) ? foundCupboard - 3 : foundCupboard + 3]);
    }

    private int indexOf(Object o, Object[] chk) {
        for (int i = 0; i < chk.length; i++) {
            if (o.equals(chk[i])) {
                return i;
            }
        }
        return -1;
    }

    public Spell getAlchemySpell() {
        return getSpell().getSpell();
    }

    public AlchemyDrop getBestItem() {
        Widget alchemyWidget = e.getDB().getWidgets().getWidget(194);
        if (alchemyWidget != null) {
            for (int i = 10; i < 15; i++) {
                if (alchemyWidget.getChild(i).getText().equals("30")) {
                    return AlchemyDrop.values()[i - 10];
                }
            }
        }
        return null;
    }

    public boolean canAlch() {
        return e.getDB().getInventory().contains("Nature rune");
    }

    public boolean shouldAlch() {
        return e.getDB().getInventory().contains(getBestItem().getName());
    }

    public int getGoldToPointCount() {
        return Math.round(e.getDB().getInventory().count("Coins") / 100);
    }

    public boolean shouldDeposit() {
        return getGoldToPointCount() >= 10;
    }

    public boolean depositCoins() {
        GameObject deposit = e.getDB().getGameObjects().closest("Coin collector");
        if (deposit == null) return false;
        if (deposit.distance() > 10) {
            if (e.getDB().getWalking().walk(deposit)) {
                MethodProvider.sleepUntil(() -> deposit.distance() < 10, ScriptMath.getTravelTime(deposit, 0.5D));
            }
        }
        return deposit.interact("Deposit");
    }

}
