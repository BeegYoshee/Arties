package org.dreambot.articron.net;

import org.dreambot.articron.net.protocol.Stream;

import java.io.IOException;

public class Connection implements Runnable {

    private Stream stream;
    private boolean active;

    public Connection(Stream stream) {
        try {
            this.stream = stream.open();
            this.active = true;
        } catch (IOException e) {
            this.active = false;
            System.out.println("Error opening stream");
        }
    }

    public Stream getStream() {
        return stream;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            this.stream.close();
        }
    }

    @Override
    public void run() {

    }
}
