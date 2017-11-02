package org.dreambot.articron.net.server;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.net.MuleServer;
import org.dreambot.articron.net.protocol.Stream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Poller implements Runnable {

    private ServerSocket socket;
    private MuleServer server;
    private ScriptContext script;

    private int port;
    private boolean polling;

    public Poller(int port, ScriptContext script, MuleServer server) {
        this.server = server;
        this.port = port;
        this.script = script;
    }

    @Override
    public void run() {
        try {
            polling = true;
            socket = new ServerSocket(port);
            System.out.println("MuleServer is listening to connections");
            while (polling) {
                System.out.println("Looking for connections");
                Socket connection = socket.accept();
                System.out.println("Connection accepted from IP: " + connection.getInetAddress().toString());
                server.addBotConnection(new BotConnection(new Stream(connection), server, script));
            }
        } catch (SocketException e) {
            System.out.println("Connection closed");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public boolean isPolling() {
        return polling;
    }

    public void stopPolling() {
        polling = false;
    }

    public ServerSocket getSocket() {
        return socket;
    }
}
