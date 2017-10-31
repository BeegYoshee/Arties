package org.dreambot.articron.fw.nodes;

import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.Calculations;
import org.dreambot.articron.fw.ScriptContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public abstract class NodeGroup extends Node {

    private List<Node> nodeList = new ArrayList<>();
    private String name;

    public NodeGroup(String name, BooleanSupplier boolSupplier) {
        this.name = name;
        super.booleanSupplier = boolSupplier;
    }

    @Override
    public int execute(ScriptContext context) {
        if (context.getDB().getClient().isLoggedIn() && context.getDB().getClient().getGameState() != GameState.HOPPING
                && context.getDB().getClient().getGameState() !=GameState.LOADING ) {
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).isValid()) {
                    context.getPaint().setStatus(nodeList.get(i).getStatus());
                    System.out.println("Status = " + nodeList.get(i).getStatus());
                    return nodeList.get(i).execute(context);
                }
            }
        }
        return 50 + Calculations.random(600,1200);
    }

    public NodeGroup addToGroup(Node... nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodeList.add(nodes[i]);
        }
        return this;
    }

    public void clear() {
        nodeList.clear();
    }

    public void remove(Node node) {
        nodeList.remove(node);
    }


    public String getName() {
        return name;
    }
}
