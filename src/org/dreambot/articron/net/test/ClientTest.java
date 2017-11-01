package org.dreambot.articron.net.test;

import org.dreambot.articron.net.MuleClient;
import org.dreambot.articron.net.protocol.PacketType;

import java.io.IOException;

public class ClientTest {

    public static void main(String... args) {
        MuleClient client = new MuleClient(43594);
        if (client.connect()) {
            try {

                client.getConnection().getStream().writeUTF("h'D",PacketType.HANDSHAKE);

                Thread.sleep(5 * 1000);

                client.getConnection().getStream().writeSecureUTF(
                        "394",
                        client.getConnection().getKey(),
                        PacketType.NEED_A_MULE
                );

            } catch (IOException e) {
                System.out.println("Couldn't send client packet");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     }
}
