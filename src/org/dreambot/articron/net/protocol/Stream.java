package org.dreambot.articron.net.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Stream {

    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;
    private boolean blockedOut = false;
    private boolean blockedIn = false;

    public Stream(Socket socket) {
        this.socket = socket;
    }

    public Stream open() throws IOException {
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new DataOutputStream(socket.getOutputStream());
        return this;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void close() {
        try {
            this.input.close();
            this.output.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blockStream(TCPStream stream, boolean blocked) {
        this.blockedOut = (stream == TCPStream.OUTGOING_TRAFIIC || stream == TCPStream.ALL_TRAFFIC) && blocked;
        this.blockedIn = (stream == TCPStream.INBOUND_TRAFFIC || stream == TCPStream.ALL_TRAFFIC) && blocked;
    }

    public PacketType readPacket() throws IOException {
        return PacketType.forByte(this.input.read());
    }

    public String readUTF() throws IOException {
        return this.input.readLine();
    }


    public void sendUTF(String utf, PacketType packet) throws IOException {
        if (!blockedOut) {
            this.output.writeByte(packet.getID());
            sendUTF(utf);
        }
    }

    public void sendPacket(PacketType packet) throws IOException {
        if (!blockedOut) {
            this.output.writeByte(packet.getID());
        }
    }

    public void sendSecureUTF(String utf, String key, PacketType packet) throws IOException {
        if (!blockedOut) {
            this.output.writeByte(packet.getID());
            sendUTF(key);
            sendUTF(utf);
        }
    }

    private void sendUTF(String utf) throws IOException {
        this.output.writeBytes(utf + "\n");
    }
}
