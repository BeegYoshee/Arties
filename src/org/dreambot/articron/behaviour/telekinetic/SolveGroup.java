package org.dreambot.articron.behaviour.telekinetic;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.nodes.NodeGroup;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class SolveGroup extends NodeGroup {

    public SolveGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Solving maze";
    }

    @Override
    public int priority() {
        return 0;
    }
}
