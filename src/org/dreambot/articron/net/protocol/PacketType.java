package org.dreambot.articron.net.protocol;

public enum PacketType {

    HANDSHAKE(),
    IDENTIFY(),
    KEY_SHARE(),
    NEED_A_MULE(),
    MULE_IS_COMING(),
    LOOT_RECEIVED(),
    LOOT_GIVEN()
    ;

    public int getID() {
        return ordinal();
    }

    public static PacketType forByte(int id) {
        for (PacketType p : PacketType.values()) {
            if (p.getID() == id) {
                return p;
            }
        }
        return null;
    }
}
