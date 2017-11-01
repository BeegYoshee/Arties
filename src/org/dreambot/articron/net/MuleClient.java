package org.dreambot.articron.net;

import org.dreambot.articron.net.client.ClientConnection;
import org.dreambot.articron.net.protocol.Stream;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MuleClient {

    private String ip;
    private int port;
    private Socket socket;
    private ClientConnection connection;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public MuleClient(int port) {
        this(null,port);
    }

    public MuleClient(String ip, int port) {
        this.ip = (ip == null) ? "localhost" : ip;
        this.port = port;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean connect() {
        try {
            socket = new Socket(ip, port);
            connection = new ClientConnection(new Stream(socket));
            executor.submit(connection);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void disconnect() {
        connection.setActive(false);
        connection.getStream().close();
    }

    public ClientConnection getConnection() {
        return connection;
    }
}
