package org.dreambot.articron.behaviour.mta.mule_interactions;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.net.MuleClient;
import org.dreambot.articron.net.protocol.PacketType;

import java.io.IOException;

public class RequestMule extends Node {
    @Override
    public String getStatus() {
        return "Calling mule...";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public int execute(ScriptContext context) {
        MuleClient client = context.getMuleClient();
        if (client.isConnected()) {
            try {
                client.getConnection().getStream().sendSecureUTF(Integer.toString(context.getDB().getClient().getCurrentWorld()),
                        client.getConnection().getKey(), PacketType.NEED_A_MULE);
                MethodProvider.sleep(5000);
            } catch (IOException e) {
                MethodProvider.log("Failed calling the mule... logging out");
                return -1;
            }
        }
        return 0;
    }
}
