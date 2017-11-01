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
        while (isActive()) {
            try {
                System.out.println("Waiting for packet...");
                PacketType receivedPacket = getStream().readPacket();
                System.out.println("Packet received: " + receivedPacket.name());
                switch (receivedPacket) {

                    case KEY_SHARE:
                        this.privateKey = getStream().readUTF();
                        System.out.println("key received: " + this.privateKey);
                        break;

                    case MULE_IS_COMING:
                        String test = getStream().readUTF();
                        System.out.println("A mule is coming for me! yay");
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {
                setActive(false);
                System.out.println("client connection got phucked");
            }
        }
        System.out.println("Outside of the packet checking");
    }

    public String getKey() {
        return privateKey;
    }


}
