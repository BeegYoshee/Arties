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

    public PacketType readPacket() throws IOException {
        int packetId = this.input.read();
        return PacketType.forByte(packetId);
    }

    public String readUTF() throws IOException {
        return this.input.readLine();
    }


    public void writeUTF(String utf, PacketType packet) throws IOException {
        this.output.writeByte(packet.getID());
        writeUTF(utf);
    }

    public void writeSecureUTF(String utf, String key, PacketType packet) throws IOException {
        this.output.writeByte(packet.getID());
        writeUTF(key);
        writeUTF(utf);
    }

    private void writeUTF(String utf) throws IOException{
        this.output.writeBytes(utf + "\n");
    }
}
