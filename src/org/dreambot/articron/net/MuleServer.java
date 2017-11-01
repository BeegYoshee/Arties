package org.dreambot.articron.net;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.server.BotConnection;
import org.dreambot.articron.net.server.Poller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MuleServer {

    private int port;
    private String key;
    private CronScript ctx;
    private Poller poller;
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    private List<BotConnection> bots = new ArrayList<>();

    public MuleServer(CronScript ctx, int port, String key) {
        this.port = port;
        this.ctx = ctx;
        this.key = key;
    }

    public MuleServer(CronScript ctx) {
        this.ctx = ctx;
    }

    public MuleServer setPort(int port) {
        this.port = port;
        return this;
    }

    public int getPort() {
        return port;
    }

    public void start() {
        poller = new Poller(port,ctx,this);
        executor.submit(poller);
    }

    public boolean isRunning() {
        return poller.isPolling();
    }



    public void stop() {
        poller.stopPolling();
        bots.forEach(bot -> {
            bot.setActive(false);
            bot.getStream().close();
        });
        bots.clear();
    }

    public boolean isPortOpen() {
        boolean taken = false;
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            taken = true;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return taken;
    }

    public void addBotConnection(BotConnection connection) {
        executor.submit(connection);
        bots.add(connection);
    }

    public void removeBotConnection(BotConnection connection) {
        bots.remove(connection);
    }

    public BotConnection[] getAllConnections() {
        return bots.toArray(new BotConnection[bots.size()]);
    }

    public BotConnection[] getIdentifiedConnections() {
        List<BotConnection> botList;
        botList = bots.stream()
                .filter(connection -> connection.getBotName() != null)
                .collect(Collectors.toList());
        return botList.toArray(new BotConnection[botList.size()]);
    }

    public BotConnection getConnection(String botName) {
        return bots.stream()
                .filter(connection -> connection.getBotName().equals(botName))
                .findFirst()
                .orElse(null);
    }

    public String getKey() {
        return key;
    }

}
