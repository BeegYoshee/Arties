package org.dreambot.articron.net.client;

import org.dreambot.articron.net.Connection;
import org.dreambot.articron.net.protocol.PacketType;
import org.dreambot.articron.net.protocol.Stream;

import java.io.IOException;

public class ClientConnection extends Connection {

    private String privateKey;

    public ClientConnection(Stream stream) {
        super(stream);
    }

    @Override
    public void run() {
        while (isActive() && getStream().isConnected()) {
            try {
                PacketType receivedPacket = getStream().readPacket();
                switch (receivedPacket) {

                    case KEY_SHARE:
                        this.privateKey = getStream().readUTF();
                        System.out.println("key received: " + this.privateKey);
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {
                setActive(false);
                System.out.println("client connection got phucked");
            }
        }
    }

    public String getKey() {
        return privateKey;
    }


}
