package org.dreambot.articron.feature;

public class MuleRequest {

    private int world;
    private String botName;

    public MuleRequest(int worldId, String botName) {
        this.world = worldId;
        this.botName = botName;
    }

    public int getWorld() {
        return world;
    }

    public String getBotName() {
        return botName;
    }

}
