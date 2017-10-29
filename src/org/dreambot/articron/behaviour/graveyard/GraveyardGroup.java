package org.dreambot.articron.behaviour.graveyard;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.nodes.NodeGroup;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class GraveyardGroup extends NodeGroup {

    public GraveyardGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Inside Graveyard room";
    }

    @Override
    public int priority() {
        return 0;
    }
}
