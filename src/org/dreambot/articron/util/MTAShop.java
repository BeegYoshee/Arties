package org.dreambot.articron.util;

import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.fw.ScriptContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class MTAShop {

    private ScriptContext e;

    private final int SHOP_WIDGET = 197;

    private final int FIRST_X = 40, FIRST_Y = 79;
    private final int Y_OFFSET = 52,X_OFFSET = 68;
    private final int WIDTH = 31, HEIGHT = 31;

    private final int SHOPKEEPER_ID = 5984;

    public MTAShop(ScriptContext e) {
        this.e = e;
    }

    public boolean canOpen() {
        NPC shopkeeper =  e.getDB().getNpcs().closest(SHOPKEEPER_ID);
        return shopkeeper != null;
    }

    public boolean open() {
        return canOpen() && e.getDB().getNpcs().closest(SHOPKEEPER_ID).interact("Trade-with");
    }

    public boolean isOpen() {
        Widget shop = e.getDB().getWidgets().getWidget(SHOP_WIDGET);
        return (shop != null) && shop.isVisible();
    }

    public Rectangle[] getContainers() {
        List<Rectangle> containerList = new ArrayList<>();
        int row = 0;
        for (int i = 0; i < Reward.values().length; i++) {
            if (i % 7 == 0)
                row++;
            int y = FIRST_Y + (Y_OFFSET * (row - 1));
            int x = FIRST_X + (X_OFFSET * (i % 7));
            containerList.add(new Rectangle(x,y,WIDTH,HEIGHT));
        }
        return containerList.toArray(new Rectangle[containerList.size()]);
    }

    public boolean buyReward(Reward reward) {
        Rectangle toClick = getContainers()[reward.ordinal()];
        if (e.getDB().getMouse().click(toClick.getLocation(),false)) {
            return e.getDB().getClient().getMenu().clickAction("Buy");
        }
        return false;
    }
}
