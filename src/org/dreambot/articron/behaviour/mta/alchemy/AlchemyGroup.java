package org.dreambot.articron.behaviour.mta.alchemy;

import org.dreambot.articron.fw.nodes.NodeGroup;

import java.util.function.BooleanSupplier;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class AlchemyGroup extends NodeGroup {

    public AlchemyGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Alchemy room";
    }

    @Override
    public int priority() {
        return 0;
    }
}
