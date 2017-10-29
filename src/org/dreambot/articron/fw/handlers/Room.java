package org.dreambot.articron.fw.handlers;


import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.MTASpell;
import org.dreambot.articron.data.MTAStave;

/**
 * Created by Arno on 28-10-2017.
 */
public class Room {

    private MTASpell spell;
    private MTAStave staff;
    private MTARoom room;


    public Room(MTARoom room) {
        this.room = room;
    }

    public MTARoom getRoom() {
        return room;
    }

    public MTASpell getSpell() {
        return spell;
    }

    public MTAStave getStave() {
        return staff;
    }

    public void setStave(MTAStave stave) {
        this.staff = stave;
    }

    public void setSpell(MTASpell spell) {
        this.spell = spell;
    }

}
