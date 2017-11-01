package org.dreambot.articron.net.server;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.feature.MuleRequest;
import org.dreambot.articron.net.Connection;
import org.dreambot.articron.net.MuleServer;
import org.dreambot.articron.net.protocol.PacketType;
import org.dreambot.articron.net.protocol.Stream;

import java.io.IOException;


public class BotConnection extends Connection {

    private String botName;
    private MuleServer server;
    private CronScript script;

    public BotConnection(Stream stream, MuleServer server, CronScript script) {
        super(stream);
        this.server = server;
        this.script = script;
    }

    @Override
    public void run() {
        while (isActive()) {
            try {
                PacketType receivedPacket = getStream().readPacket();
                if (receivedPacket != null) {
                    switch (receivedPacket) {

                        case HANDSHAKE:
                            this.botName = getStream().readUTF();
                            getStream().writeUTF(server.getKey(), PacketType.KEY_SHARE);
                            break;

                        case IDENTIFY:
                            String botName = readSecureUTF();
                            this.botName = botName;
                            break;

                        case NEED_A_MULE:
                            int world = Integer.parseInt(readSecureUTF());
                            System.out.println(this.botName + " needs a trade @W" + world);
                            getStream().writeUTF("muleName",PacketType.MULE_IS_COMING);
                            //script.getContext().getMTA().getMuleQueue().addMuleRequest(new MuleRequest(world,this.botName));
                            break;

                        default:
                            System.out.println("Unidentified packet");
                            break;
                    }
                }
            } catch (IOException e) {
                setActive(false);
                getStream().close();
                server.removeBotConnection(this);
                System.out.println("["+botName+"] has disconnected");
            }
        }
    }

    private String readSecureUTF() throws IOException {
            String key = getStream().readUTF();
            if (!server.getKey().equals(key)) {
                System.out.println("Keys don't match, refusing connection");
                throw new IOException();
            } else {
                return getStream().readUTF();
            }
    }

    public String getBotName() {
        return botName;
    }
}
