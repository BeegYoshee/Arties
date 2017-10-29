package org.dreambot.articron.fw.nodes;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.ScriptContext;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class RootGroup extends NodeGroup {

    public RootGroup(String name, BooleanSupplier boolSupplier) {
        super(name, boolSupplier);
    }

    @Override
    public String getStatus() {
        return "Awaiting data";
    }

    @Override
    public int execute(ScriptContext context) {
        return super.execute(context);
    }

    @Override
    public int priority() {
        return 0;
    }
}
