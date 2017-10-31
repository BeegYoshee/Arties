package org.dreambot.articron.fw;

import org.dreambot.articron.fw.nodes.Node;
import org.dreambot.articron.fw.nodes.NodeGroup;
import org.dreambot.articron.fw.nodes.RootGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public class Manager {

    private static ScriptContext ctx;
    private static List<Node> nodeList;
    private static final NodeGroup BASE_GROUP = new RootGroup("ROOT", () -> true);

    public static void init(ScriptContext context) {
        ctx = context;
        nodeList = new ArrayList<>();
        nodeList.add(BASE_GROUP);
    }

    public static void commit(Node... nodes) {
        BASE_GROUP.addToGroup(nodes);
    }

    public static NodeGroup getGroupByName(String name) {
        for (Node n : nodeList) {
            if (n instanceof NodeGroup) {
                NodeGroup ng = (NodeGroup) n;
                if (ng.getName().equals(name)) {
                    return ng;
                }
            }
        }
        return null;
    }

    public static void removeAllGroups() {
        BASE_GROUP.clear();
    }

    public static void clearGroup(String name) {
        NodeGroup n = getGroupByName(name);
        n.clear();
    }

    public static void removeGroup(String name) {
        NodeGroup n = getGroupByName(name);
        n.clear();
        BASE_GROUP.remove(n);
    }

    public static int cycleNodes() {
        return BASE_GROUP.execute(ctx);
    }
}
