package org.dreambot.articron.net.networking.adapters;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.networking.TCPRequest;

import java.io.*;
import java.net.Socket;

public class MuleClient<T extends CronScript> implements Runnable {

    private T script;
    private String ip;
    private int port;
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;
    private String muleName;

    private boolean connected;


    public MuleClient(T script, String ip, int port) {
        this.script = script;
        this.ip = ip.equals("") ? "localhost" : ip;
        this.port = port;
        try {
            socket = new Socket(ip,port);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new DataOutputStream(socket.getOutputStream());
            writeToMule("Test", TCPRequest.IDENTIFY);
            this.muleName = input.readLine();
            connected = true;
            System.out.println(muleName);
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        connected = false;
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToMule(String msg, TCPRequest request) throws IOException {
        this.output.write(request.getOpcode());
        this.output.writeUTF(msg + "\n");
    }

    public void writeToMule(int msg, TCPRequest request) throws IOException {
        this.output.write(request.getOpcode());
        this.output.write(msg);
        this.output.flush();

    }

    @Override
    public void run() {
        while (socket.isConnected() && connected) {

        }
    }
}
