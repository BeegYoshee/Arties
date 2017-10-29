package org.dreambot.articron.fw.nodes;

import java.util.function.BooleanSupplier;

import org.dreambot.articron.fw.ScriptContext;


/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public abstract class Node {

    protected BooleanSupplier booleanSupplier;

    public boolean isValid() {
        return booleanSupplier.getAsBoolean();
    }

    public abstract String getStatus();
    public abstract int priority();
    public abstract int execute(ScriptContext context);

    public Node when(BooleanSupplier supplier) {
        this.booleanSupplier = supplier;
        return this;
    }
}
