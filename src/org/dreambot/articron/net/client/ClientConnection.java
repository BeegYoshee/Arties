package org.dreambot.articron.net.client;

import org.dreambot.articron.net.Connection;
import org.dreambot.articron.net.MuleClient;
import org.dreambot.articron.net.protocol.PacketType;
import org.dreambot.articron.net.protocol.Stream;
import org.dreambot.articron.net.protocol.TCPStream;

import java.io.IOException;

public class ClientConnection extends Connection {

    private String privateKey;
    private MuleClient client;

    public ClientConnection(Stream stream, MuleClient client) {
        super(stream);
        this.client = client;
    }

    @Override
    public void run() {
        while (isActive() && getStream().isConnected()) {
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
                        String muleName = getStream().readUTF();
                        System.out.println("A mule is coming for me: " + muleName);
                        getStream().blockStream(TCPStream.OUTGOING_TRAFIIC,true);
                        break;

                    case END_CONNECTION:
                        System.out.println("End connection packet received");
                        client.disconnect();
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {
                setActive(false);
                System.out.println("client connection got closed");
            }
        }
        System.out.println("The stream was closed, connection closed");
    }

    public String getKey() {
        return privateKey;
    }


}
