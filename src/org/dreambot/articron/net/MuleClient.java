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
        this.ip = (ip == null || ip.equals("")) ? "localhost" : ip;
        this.port = port;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean connect() {
        try {
            socket = new Socket(ip, port);
            connection = new ClientConnection(new Stream(socket),this);
            executor.submit(connection);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to connect to " + ip);
        }
        return false;
    }


    public void disconnect() {
        connection.setActive(false);
        connection.getStream().close();
        executor.shutdown();
    }

    public ClientConnection getConnection() {
        return connection;
    }
}
