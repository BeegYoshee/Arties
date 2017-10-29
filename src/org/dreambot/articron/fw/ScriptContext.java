package org.dreambot.articron.fw;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.articron.fw.handlers.MTAHandler;
import org.dreambot.articron.util.PaintDrawer;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public class ScriptContext {

    private final MethodContext CONTEXT;
    private final MTAHandler mtaHandler;
    private final PaintDrawer paintDrawer;
    private final ScriptManifest manifest;
    private final Timer timer;

    public ScriptContext(MethodContext methodContext, ScriptManifest manifest) {
        CONTEXT = methodContext;
        this.manifest = manifest;
        mtaHandler = new MTAHandler(this);
        paintDrawer = new PaintDrawer(this);
        this.timer = new Timer();
        CONTEXT.getSkillTracker().start();
    }

    public MethodContext getDB() {
        return CONTEXT;
    }

    public MTAHandler getMTA() {
        return mtaHandler;
    }


    public PaintDrawer getPaint() {
        return paintDrawer;
    }

    public String getRuntime() {
        return timer.formatTime();
    }

    public ScriptManifest getManifest() {
        return manifest;
    }
}
