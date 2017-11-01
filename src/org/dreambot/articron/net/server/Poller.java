package org.dreambot.articron.net.server;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.MuleServer;
import org.dreambot.articron.net.protocol.Stream;

import java.net.ServerSocket;
import java.net.Socket;

public class Poller implements Runnable {

    private ServerSocket socket;
    private MuleServer server;
    private CronScript script;
    private String key;

    private int port;
    private boolean polling;

    public Poller(int port, CronScript script, MuleServer server) {
        this.server = server;
        this.port = port;
        this.script = script;
    }

    @Override
    public void run() {
        try {
            polling = true;
            socket = new ServerSocket(port);
            while (polling) {
                System.out.println("Looking for connections");
                Socket connection = socket.accept();
                System.out.println("Connection accepted from IP: " + connection.getInetAddress().toString());
                server.addBotConnection(new BotConnection(new Stream(connection), server, script));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPolling() {
        return polling;
    }

    public void stopPolling() {
        polling = false;
    }
}
