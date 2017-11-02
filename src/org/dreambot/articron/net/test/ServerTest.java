package org.dreambot.articron.net.test;

import org.dreambot.articron.net.MuleServer;

public class ServerTest {

    public static void main(String... args) {
        MuleServer server = new MuleServer(null,43594,"testkey","iPrototype");
        server.start();
    }
}
