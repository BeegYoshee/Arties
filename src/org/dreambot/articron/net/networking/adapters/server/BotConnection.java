package org.dreambot.articron.net.networking.adapters.server;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.net.networking.TCPRequest;

import java.io.*;
import java.net.Socket;

public class BotConnection implements Runnable{

    private Socket socket;
    private CronScript script;
    private String botName;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean active;

    public BotConnection(Socket socket, CronScript script) {
        this.script = script;
        this.socket = socket;
        try {
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        active = true;
    }


    public void stop() throws IOException {
        socket.close();
        active = false;
    }

    public String getBotName() {
        return botName;
    }

    @Override
    public void run() {
        while (active) {
            try {
                int opcode;
                TCPRequest request = TCPRequest.forOpcode(opcode = input.read());
                if (request != null) {
                    switch (request) {
                        case IDENTIFY:
                            this.botName = input.readUTF();
                            System.out.println("identified as " + this.botName);
                            writeToClient("IPrototype", TCPRequest.IDENTIFY);
                        case NEED_A_MULE:
                            int fuck = input.read();
                            int worldId = input.read();
                            System.out.println("A mule is needed for " + this.botName + " in world " + worldId);
                            writeToClient("Coming", TCPRequest.MULE_IS_COMING);
                    }
                } else {
                    System.out.println("Unrecognised opcode" + opcode);
                }
            } catch (IOException e) {
                System.out.println("Connection was ended");
                active = false;
                try {
                    input.close();
                    output.close();
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void writeToClient(String msg, TCPRequest request) throws IOException {
        this.output.write(request.getOpcode());
        this.output.writeBytes(msg + "\n");
    }
}
