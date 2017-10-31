package org.dreambot.articron.net.networking;

import org.dreambot.articron.ArtiMTA;
import org.dreambot.articron.net.networking.adapters.MuleClient;

import java.io.IOException;

public class TestClient {

    public static void main(String... args) {

        MuleClient<ArtiMTA> client = new MuleClient<>(null,"localhost",43594);
        Thread t = new Thread(client);
        t.start();
        try {
            Thread.sleep(10 * 1000);
            client.writeToMule(2,TCPRequest.NEED_A_MULE);
            Thread.sleep(10 * 1000);
            client.writeToMule(64,TCPRequest.NEED_A_MULE);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
