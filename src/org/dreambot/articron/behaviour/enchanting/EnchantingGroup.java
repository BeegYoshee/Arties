package org.dreambot.articron.behaviour.enchanting;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.nodes.NodeGroup;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class EnchantingGroup extends NodeGroup {

    public EnchantingGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Enchanting room";
    }

    @Override
    public int priority() {
        return 0;
    }
}
