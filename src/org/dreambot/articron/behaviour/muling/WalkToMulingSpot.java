package org.dreambot.articron.behaviour.muling;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.util.ScriptMath;

public class WalkToMulingSpot extends Node{



    @Override
    public String getStatus() {
        return "Walking to muling location";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        MuleLocation loc = context.getMuleLocation();
        if (loc != null) {
            if (loc == MuleLocation.MTA_UPSTAIRS) {
                if (!context.getMTA().isInsideMTABuilding()) {
                    if (context.getMTA().getEntranceTile().distance() > 3) {
                        if (context.getDB().getWalking().walk(context.getMTA().getEntranceTile())) {
                            MethodProvider.sleep(600);
                            Tile dest = context.getDB().getWalking().getDestination();
                                MethodProvider.sleepUntil(() -> ((dest == null) || dest.distance() <= 5) || !context.getDB().getLocalPlayer().isMoving(), ScriptMath.getTravelTime(dest,0.5D));
                        }
                    } else {
                        GameObject portal = context.getDB().getGameObjects().getTopObjectOnTile(context.getMTA().getEntranceTile());
                        if (portal != null) {
                            if (portal.interact("Enter")) {
                                MethodProvider.sleepUntil(() -> context.getMTA().isInsideMTABuilding(), ScriptMath.getTravelTime(portal,1.0D));
                            }
                        }
                    }
                } else if (context.getMTA().isInsideMTABuilding() && context.getDB().getLocalPlayer().getZ() == 0) {
                    GameObject stairs = context.getDB().getGameObjects().closest(10775);
                    if (stairs != null) {
                        if (stairs.interact("Climb-up")) {
                            MethodProvider.sleepUntil(() -> context.getDB().getLocalPlayer().getTile().getZ() == 1, ScriptMath.getTravelTime(stairs,1D));
                        }
                    }
                }
            } else {
                Area area = loc.getArea();
                Tile ran = area.getRandomTile();
                if (!area.contains(context.getDB().getLocalPlayer())) {
                    if (context.getMTA().isInsideMTABuilding()) {
                            if (context.getMTA().leaveMTABuilding()) {
                                MethodProvider.sleepUntil(() -> !context.getMTA().isInsideMTABuilding(), Calculations.random(600,1000));
                            }
                            return 1000;
                    }
                    if (context.getDB().getWalking().walk(ran)) {
                        MethodProvider.sleep(1000);
                        Tile dest = context.getDB().getWalking().getDestination();
                        MethodProvider.sleepUntil(() -> ((dest == null) || dest.distance() <= 5) || !context.getDB().getLocalPlayer().isMoving(), ScriptMath.getTravelTime(dest,0.5D));
                    }
                }
            }
        }
        return 0;
    }
}
