package org.dreambot.articron.data;

/**
 * Author: Articron
 * Date:   20/10/2017.
 */
public class RuneRequirement {

    public RuneRequirement(MTARune rune, int amount) {
        this.rune = rune;
        this.amount = amount;
    }

    private MTARune rune;
    private int amount;

    public MTARune getRune() {
        return rune;
    }

    public int getAmount() {
        return amount;
    }
}
