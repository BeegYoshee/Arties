package org.dreambot.articron.behaviour.mta;

import org.dreambot.articron.fw.nodes.NodeGroup;

import java.util.function.BooleanSupplier;

public class OutsideMTAGroup extends NodeGroup {

    public OutsideMTAGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Outside MTA building";
    }

    @Override
    public int priority() {
        return 0;
    }
}
