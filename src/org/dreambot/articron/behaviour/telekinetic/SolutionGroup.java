package org.dreambot.articron.behaviour.telekinetic;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.nodes.NodeGroup;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class SolutionGroup extends NodeGroup{

    public SolutionGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Ending current maze";
    }

    @Override
    public int priority() {
        return 0;
    }
}
