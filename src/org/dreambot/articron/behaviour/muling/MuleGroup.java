package org.dreambot.articron.behaviour.muling;

import org.dreambot.articron.fw.nodes.NodeGroup;

import java.util.function.BooleanSupplier;

public class MuleGroup extends NodeGroup {

    public MuleGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Muling";
    }

    @Override
    public int priority() {
        return 0;
    }
}
