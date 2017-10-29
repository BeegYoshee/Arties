package org.dreambot.articron.fw.handlers;

import java.awt.Point;

import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.mouse.CrosshairState;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.util.pathfinding.MazeSolver;


/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class TelekineticRoom extends Room {

    private ScriptContext context;
    private MazeSolver solver;

    public TelekineticRoom(ScriptContext context, MTARoom room) {
        super(room);
        this.context = context;
        this.solver = new MazeSolver(context);
    }

    public boolean isObserving() {
        return ((context.getDB().getPlayerSettings().getConfig(629) >> 30) & 0b1) == 1;
    }

    public MazeSolver getSolver() {
        return solver;
    }

    public boolean castTelegrab() {
        if (!context.getDB().getMagic().isSpellSelected()) {
            if (context.getDB().getMagic().castSpell(Normal.TELEKINETIC_GRAB)) {
                return castTelegrab();
            }
        }
        Point currentPoint = context.getDB().getMouse().getPosition();
        NPC guard = context.getDB().getNpcs().closest(6777);
        if (guard.getModel().getVertexPoints().contains(currentPoint)) {
            return context.getDB().getMouse().click();
        }
        Point centroid = guard.getModel().getEntity().getCenterPoint();
        if (centroid != null) {
            return context.getDB().getMouse().move(centroid)  && context.getDB().getClient().getMenu().getDefaultAction().contains("Cast") &&
                    context.getDB().getMouse().click(centroid) && context.getDB().getMouse().getCrosshairState() == CrosshairState.INTERACTED;
        }
        return false;
    }

        public boolean solvedMaze() {
            NPC guard = context.getDB().getNpcs().closest(6779);
            return (guard != null && guard.hasAction("Talk-to"));
        }

}
