package org.dreambot.articron.net.networking.adapters;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.networking.adapters.server.AcceptorThread;
import org.dreambot.articron.net.networking.adapters.server.BotConnection;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MuleServer<T extends CronScript> {

    private T script;
    private int port;
    private ExecutorService acceptorService;
    private boolean running;
    private String key;

    private List<BotConnection> bots;

    public MuleServer(T script, int port, String key) {
        this.script = script;
        this.port = port;
        this.key = key;
    }

    public void cleanStart() {
        bots = new LinkedList<>();
        acceptorService = Executors.newSingleThreadExecutor();
        AcceptorThread<T> acceptor = new AcceptorThread(script, this, port);
        acceptorService.submit(acceptor);
        running = true;
    }

    public void shutdown() {
        running = false;
        acceptorService.shutdownNow();
    }


    public List<BotConnection> getBots() {
        return bots;
    }
}
