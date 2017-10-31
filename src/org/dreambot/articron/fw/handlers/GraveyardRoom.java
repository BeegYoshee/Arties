package org.dreambot.articron.fw.handlers;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.input.mouse.CrosshairState;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.magic.Spell;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.articron.data.GraveyardBone;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.util.ScriptMath;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class GraveyardRoom extends Room {

    private ScriptContext e;
    private int eatThreshold = 70;
    private int healthThreshold = 50;

    public GraveyardRoom(ScriptContext e, MTARoom room) {
        super(room);
        this.e = e;
    }

    private int getB2BAmount() {
        int amt = 0;
        for (Item item : e.getDB().getInventory().all()) {
            if (item != null) {
                amt += GraveyardBone.getBananaValue(item.getID());
            }
        }
        return amt;
    }

    public boolean hasBones() {
        return e.getDB().getInventory().contains(f->f.getName().contains("bones"));
    }
    public boolean shouldCastB2B() {
        return getB2BAmount() > getRealFreeSpace() || shouldEat() && hasBones();
    }

    public int getRealFreeSpace() {
        return e.getDB().getInventory().getEmptySlots() + e.getDB().getInventory().count(f -> f.getName().contains("bones"));
    }
    public boolean shouldEat() {
        return getHPPercent() <= healthThreshold;
    }

    public boolean canEat() {
        return e.getDB().getInventory().contains("Banana", "Peach");
    }

    public boolean shouldDeposit() {
        return (e.getDB().getInventory().contains("Banana","Peach") && getHPPercent() > healthThreshold) || e.getDB().getInventory().isFull();
    }

    public Spell getGraveyardSpell() {
        return getSpell().getSpell();
    }

    public GameObject getPileOfBones() {
        List<GameObject> bonePiles = e.getDB().getGameObjects().all(f-> f!= null && f.getName().contains("Bones") && f.hasAction("Grab"));
        GameObject collector = e.getDB().getGameObjects().closest("Food chute");
        if (collector != null) {
            bonePiles.sort(boneSorter);
        }
        if (bonePiles.size() == 0)
            return null;
        return bonePiles.get(0);
    }


    public boolean interactWithBones(GameObject bones) {
        int count =  e.getDB().getInventory().getEmptySlots();
        if (bones.distance() > 4) {
            return bones.interact("Grab");
        }
        Rectangle hulls = bones.getModel().getHullBounds(0.3f).getBounds();
        if (e.getDB().getMouse().move(hulls) && MethodProvider.sleepUntil(() -> e.getDB().getClient().getMenu().contains("Grab"),2000)) {
            if (e.getDB().getClient().getMenu().getDefaultAction().contains("Grab")) {
                if (e.getDB().getMouse().click()) {
                    MethodProvider.sleep(100);
                    if (e.getDB().getMouse().getCrosshairState() == CrosshairState.INTERACTED) {
                        return MethodProvider.sleepUntil(() -> count != e.getDB().getInventory().getEmptySlots(), Calculations.random(50, 600));
                    }
                }
            } else {
                if (e.getDB().getClient().getMenu().clickAction("Grab")) {
                    MethodProvider.sleep(100);
                    if (e.getDB().getMouse().getCrosshairState() == CrosshairState.INTERACTED) {
                        return MethodProvider.sleepUntil(() -> count != e.getDB().getInventory().getEmptySlots(), Calculations.random(50, 600));
                    }
                }
            }

        }
        return false;
    }

    private Comparator<GameObject> boneSorter = (o1, o2) -> {
        if (o1.distance() < o2.distance()) return -1;
        if (o2.distance() > o2.distance()) return +1;
        return 0;
    };


    public boolean shouldLoot() {
        return (!shouldCastB2B()) && !e.getDB().getInventory().isFull();
    }

    public int getEatThreshold() {
        return eatThreshold;
    }

    public void setEatThreshold(int eatThreshold) {
        this.eatThreshold = eatThreshold;
    }

    public int getHealthThreshold() {
        return healthThreshold;
    }

    public void setHealthThreshold(int healthThreshold) {
        this.healthThreshold = healthThreshold;
    }

    public int getHPPercent() {
        int currentHp = e.getDB().getSkills().getBoostedLevels(Skill.HITPOINTS);
        int maxHp = e.getDB().getSkills().getRealLevel(Skill.HITPOINTS);
        return ScriptMath.getPercentage(currentHp,maxHp);
    }
}
