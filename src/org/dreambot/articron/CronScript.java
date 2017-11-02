package org.dreambot.articron;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.articron.fw.ScriptContext;

public abstract class CronScript extends AbstractScript {

    private ScriptContext context;

    public void setContext() {
        context = new ScriptContext(this, getManifest(), getRandomManager());
    }

    public ScriptContext getContext() {
        return context;
    }
}
