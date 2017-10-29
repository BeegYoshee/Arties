package org.dreambot.articron.util.pathfinding;


/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class MazeNode {

    private MazeTile tile;
    private MazeNode parent;
    private double weight;

    public MazeNode(MazeTile tile, MazeNode parent, double weight) {
        this.tile = tile;
        this.parent = parent;
        this.weight = weight;
    }

    public MazeTile getTile() {
        return tile;
    }

    public MazeNode getParent() {
        return parent;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        MazeNode param = (MazeNode) obj;
        return tile.equals(param.getTile());
    }
}
