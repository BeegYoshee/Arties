package org.dreambot.articron.util.pathfinding;

import org.dreambot.api.methods.map.Tile;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class MazeTile {

    private int x, y;
    private Tile tile;

    public MazeTile(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getWorldTile() {
        return tile;
    }

    public boolean isClipped(MazeDirection direction) {
        return ((tile.getTileReference().getFlags() >> direction.getShift()) & 0b1) == 1;
    }

    @Override
    public boolean equals(Object obj) {
        MazeTile n = (MazeTile) obj;
        return n.getX() == x && n.getY() == y;
    }

    @Override
    public String toString() {
        return "["+x+","+y+"], -> " + tile.toString();
    }
}
