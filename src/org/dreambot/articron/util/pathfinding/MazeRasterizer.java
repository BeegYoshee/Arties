package org.dreambot.articron.util.pathfinding;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MazeRasterizer {

    private ScriptContext context;

    private MazeTile startLocation;
    private MazeTile destination;

    public MazeRasterizer(ScriptContext context) {
        this.context = context;
    }

    public void reset() {
        startLocation = null;
        destination = null;
    }

    public List<MazeTile> calculateRaster() {
        List<MazeTile> tileList = new ArrayList<>();
        MazeTile rasterRoot = new MazeTile(0,0,getRasterRoot());
        tileList.add(rasterRoot);
        for (int columnIndex = 0; columnIndex < 10; columnIndex++) { // columns
            for (int rowIndex = 0; rowIndex < 10; rowIndex++) { //rows
                tileList.add(new MazeTile(
                        rasterRoot.getX() + rowIndex,
                        rasterRoot.getY() + columnIndex,
                        new Tile(rasterRoot.getWorldTile().getX() - rowIndex, rasterRoot.getWorldTile().getY() - columnIndex)));
            }
        }
        startLocation = getTile(tile -> tile.getWorldTile().equals(context.getDB().getNpcs().closest(6777).getTile()), tileList);
        destination = getTile(m -> m.getWorldTile().getTileReference().getFloorDecoration() != null,tileList);
        return tileList;
    }


    public MazeTile getTile(Predicate<MazeTile> predicate, List<MazeTile> tiles) {
        for (MazeTile node : tiles) {
            if (node != null) {
                if (predicate.test(node)) {
                    return node;
                }
            }
        }
        return null;
    }

    private Tile getRasterRoot() {
        List<GameObject> walls = context.getDB().getGameObjects().all(10755);
        if (walls != null) {
            walls.sort((GameObject wall1, GameObject wall2) -> wall1.getX() + wall2.getX());
            walls.sort((GameObject wall1, GameObject wall2) -> wall1.getY() + wall2.getY());
            Tile tile = walls.get(0).getTile();
            tile.setX(tile.getX() - 1);
            tile.setY(tile.getY() - 1);
            return tile;
        }
        return null;
    }

    public MazeTile getStartLocation() {
        return startLocation;
    }

    public MazeTile getDestination() {
        return destination;
    }

    public MazeTile getNeighbour(MazeTile node, MazeDirection direction, List<MazeTile> raster) {
        MazeTile end = node;
        int maxamount = 0;
        while (maxamount < 11) {
            if (end == null || end.isClipped(direction)) {
                break;
            }
            switch (direction) {
                case WEST:
                    MazeTile fEnd = end;
                    end = getTile(n ->
                            new Tile(fEnd.getWorldTile().getX() - 1, fEnd.getWorldTile().getY()).equals(n.getWorldTile()),raster);
                    break;
                case EAST:
                    fEnd = end;
                    end = getTile(n ->
                            new Tile(fEnd.getWorldTile().getX() + 1, fEnd.getWorldTile().getY()).equals(n.getWorldTile()),raster);
                    break;
                case NORTH:
                    fEnd = end;
                    end = getTile(n ->
                            new Tile(fEnd.getWorldTile().getX(), fEnd.getWorldTile().getY() + 1).equals(n.getWorldTile()),raster);
                    break;
                case SOUTH:
                    fEnd = end;
                    end = getTile(n ->
                            new Tile(fEnd.getWorldTile().getX(), fEnd.getWorldTile().getY() - 1).equals(n.getWorldTile()),raster);
                    break;
            }
            maxamount++;
        }
        return end;
    }
}
