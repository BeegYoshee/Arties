package org.dreambot.articron.net.networking;

import org.dreambot.articron.ArtiMTA;
import org.dreambot.articron.net.networking.adapters.MuleServer;

public class TestNet {

    public static void main(String... args) {
        MuleServer<ArtiMTA> server = new MuleServer<>(null,43594,"test");
        server.cleanStart();


    }
}
