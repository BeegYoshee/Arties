package org.dreambot.articron.behaviour.outside;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.nodes.NodeGroup;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class OutsideGroup extends NodeGroup {

    public OutsideGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Outside of MTA";
    }

    @Override
    public int priority() {
        return 0;
    }
}
