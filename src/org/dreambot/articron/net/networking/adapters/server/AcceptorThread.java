package org.dreambot.articron.net.networking.adapters.server;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.networking.adapters.MuleServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AcceptorThread<T extends CronScript> implements Runnable {

    private int port;
    private ServerSocket server;
    private T script;
    private MuleServer<CronScript> muleServer;
    private ExecutorService service;


    public AcceptorThread(T script, MuleServer<CronScript> muleServer, int port) {
        this.port = port;
        this.script = script;
        this.muleServer = muleServer;
        this.service = Executors.newFixedThreadPool(10);
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (true) {
                System.out.println("Looking for worker connections...");
                Socket connection = server.accept();
                System.out.println("Bot connection found");
                BotConnection con = new BotConnection(connection, script);
                this.service.submit(con);
                this.muleServer.getBots().add(con);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
