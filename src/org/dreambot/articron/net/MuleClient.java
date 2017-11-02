package org.dreambot.articron.net;

import org.dreambot.articron.fw.ScriptContext;
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
    private ScriptContext ctx;

    public MuleClient(int port, ScriptContext ctx) {
        this(null,port,ctx);
    }

    public MuleClient(String ip, int port, ScriptContext ctx) {
        this.ip = (ip == null || ip.equals("")) ? "localhost" : ip;
        this.port = port;
        this.ctx = ctx;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean connect() {
        try {
            socket = new Socket(ip, port);
            connection = new ClientConnection(new Stream(socket),this, ctx);
            executor.submit(connection);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to connect to " + ip);
        }
        return false;
    }


    public void disconnect() throws IOException{
        connection.setActive(false);
        connection.getStream().close();
        socket.close();
        executor.shutdown();
    }

    public ClientConnection getConnection() {
        return connection;
    }
}
