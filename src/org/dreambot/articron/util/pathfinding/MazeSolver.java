package org.dreambot.articron.util.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.articron.fw.ScriptContext;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class MazeSolver {

    private ScriptContext ctx;
    private List<MazeTile> tileList = new ArrayList<>();
    private MazeTile destination;
    private MazeTile start;
    private List<MazeNode> path = new ArrayList<>();
    private HashMap<MazeDirection, List<Tile>> directionMap = new HashMap<>();
    private boolean isRead = false;
    private MazeTile statueNode;

    public MazeSolver(ScriptContext ctx) {
        this.ctx = ctx;
    }

    public void reset() {
        tileList.clear();
        directionMap.clear();
        path.clear();
        destination = null;
        statueNode = null;
        start = null;
        isRead = false;
    }

    public void readMaze() {
        createRaster();
        createDirectionMap();
        calculatePath();
        isRead = true;
    }

    private void createDirectionMap() {
        for (MazeDirection d : MazeDirection.values()) {
            directionMap.put(d,getTilesForDirection(d));
        }
    }

    public boolean isInCorrectRow() {
        return directionMap.get(getMazeDirection(false)).contains(ctx.getDB().getLocalPlayer().getTile());
    }

    public Tile getCastLocation() {
        MazeDirection d = getMazeDirection(false);
        if (d == null) return null;
        List<Tile> tiles = directionMap.get(d);
        tiles.sort(Comparator.comparingDouble(Tile::distance));
        return tiles.get(0);
    }

    public Tile getFutureCastLocation() {
        MazeDirection m = getMazeDirection(true);
        if (m == null) {
            return null;
        }
        List<Tile> tiles = directionMap.get(m);
        tiles.sort(Comparator.comparingDouble(Tile::distance));
        return tiles.get(0);
    }

    public MazeTile getStartTile() {
        return start;
    }

    public MazeTile endTile() {
        return destination;
    }

    public MazeTile getNextMazeTile() {
        try {
            return path.get(forNodeIndex(getStatueNode()) + 1).getTile();
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public MazeTile getFutureMazeTile() {
        try {
            return path.get(forNodeIndex(getStatueNode()) + 2).getTile();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private MazeDirection getMazeDirection(boolean future) {
        MazeTile current = getStatueNode();
        if (current == null) return null;
        MazeTile goal = future ? getFutureMazeTile() : getNextMazeTile();
        if (goal != null) {
            if (goal.getY() > current.getY()) {
                return MazeDirection.SOUTH;
            } else if (goal.getY() < current.getY()) {
                return MazeDirection.NORTH;
            } else if (goal.getX() > current.getX()) {
                return MazeDirection.WEST;
            } else if (goal.getX() < current.getX()) {
                return MazeDirection.EAST;
            }
        }
        return null;
    }

    private List<Tile> getTilesForDirection(MazeDirection d) {
        List<Tile> tileList = new ArrayList<>();
        switch (d) {
            case SOUTH:
            case NORTH:
                Tile rasterTile = getMazeTile(n -> n.getX() == ((d == MazeDirection.NORTH)? 0 : 9) && n.getY() == ((d == MazeDirection.NORTH)? 0 : 9)).getWorldTile();
                if (rasterTile != null) {
                    Tile startTile = new Tile(rasterTile.getX(), rasterTile.getY() + ((d == MazeDirection.NORTH) ? 1 : -1));
                    tileList.add(startTile);
                    for (int i = 1; i < 10; i++) {
                        tileList.add(new Tile(startTile.getX() + ((d == MazeDirection.NORTH) ? -i : i), startTile.getY()));
                    }
                }
                break;
            case WEST:
            case EAST:
                rasterTile = getMazeTile(n -> n.getX() == ((d == MazeDirection.EAST) ? 0 : 9) && n.getY() == 0).getWorldTile();
                if (rasterTile != null) {
                    Tile startTile = new Tile(rasterTile.getX() + ((d == MazeDirection.EAST) ? 1 : -1), rasterTile.getY());
                    tileList.add(startTile);
                    for (int i = 0; i < 10; i++) {
                        tileList.add(new Tile(startTile.getX(), startTile.getY() - i));
                    }
                }
        }
        return tileList;
    }


    private MazeTile getStatueNode() {
        MazeTile tile = getMazeTile(m -> m.getWorldTile().getTile().equals(ctx.getDB().getNpcs().closest(6777).getTile()));
        if (tile == null) {
            tile = statueNode;
        } else {
            statueNode = tile;
        }
        return tile;
    }

    private int forNodeIndex(MazeTile tile) {
        if (path == null || path.size() == 0) {
            return -1;
        }
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).getTile().equals(tile)) {
                return i;
            }
        }
        return -1;
    }

    public List<MazeNode> calculatePath() {
        List<MazeNode> openList = new ArrayList<>();
        List<MazeNode> closedList = new ArrayList<>();
        MazeNode first = new MazeNode(start,null,0);
        openList.add(first);
        while (openList.size() > 0) {
            openList.sort(nodeSorter);
            MazeNode current = openList.get(0);
            if (current.getTile().equals(destination)) {
                List<MazeNode> path = new ArrayList<>();
                while (current.getParent() != null) {
                    path.add(current);
                    current = current.getParent();
                }
                Collections.reverse(path);
                openList.clear();
                closedList.clear();
                this.path = path;
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            List<MazeTile> neighbours = getNeighbours(current.getTile());
            for (MazeTile neighbour : neighbours) {
                MazeNode edge = new MazeNode(neighbour,current,current.getWeight() + 1);
                if (!closedList.contains(edge)) {
                    openList.add(edge);
                }
            }
        }
        return null;
    }

    private Comparator<MazeNode> nodeSorter = (o1, o2) -> {
        if (o1.getWeight() < o2.getWeight()) return +1;
        if (o2.getWeight() > o2.getWeight()) return -1;
        return 0;
    };

    private List<MazeTile> getNeighbours(MazeTile tile) {
        List<MazeTile> neighbours = new ArrayList<>();
        for (MazeDirection d : MazeDirection.values()) {
            MazeTile neighbour = getAdjacentEdge(tile,d);
            if (neighbour != null && !neighbour.equals(tile)) {
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

    public MazeTile getAdjacentEdge(MazeTile node, MazeDirection direction) {
        MazeTile end = node;
        int maxamount = 0;
        while (maxamount < 11) {
            if (end == null || end.isClipped(direction)) {
                break;
            }
            switch (direction) {
                case WEST:
                    MazeTile fEnd = end;
                    end = getMazeTile(n ->
                            new Tile(fEnd.getWorldTile().getX() - 1, fEnd.getWorldTile().getY()).equals(n.getWorldTile()));
                    break;
                case EAST:
                    fEnd = end;
                    end = getMazeTile(n ->
                            new Tile(fEnd.getWorldTile().getX() + 1, fEnd.getWorldTile().getY()).equals(n.getWorldTile()));
                    break;
                case NORTH:
                    fEnd = end;
                    end = getMazeTile(n ->
                            new Tile(fEnd.getWorldTile().getX(), fEnd.getWorldTile().getY() + 1).equals(n.getWorldTile()));
                    break;
                case SOUTH:
                    fEnd = end;
                    end = getMazeTile(n ->
                            new Tile(fEnd.getWorldTile().getX(), fEnd.getWorldTile().getY() - 1).equals(n.getWorldTile()));
                    break;
            }
            maxamount++;
        }
        return end;
    }

    private void createRaster() {
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
        start = getStatueNode();
        destination = getMazeTile(m -> m.getWorldTile().getTileReference().getFloorDecoration() != null);
    }

    private MazeTile getMazeTile(Predicate<MazeTile> predicate) {
        for (MazeTile node : tileList) {
            if (node != null) {
                if (predicate.test(node)) {
                    return node;
                }
            }
        }
        return null;
    }

    private Tile getRasterRoot() {
        List<GameObject> walls = ctx.getDB().getGameObjects().all(10755);
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

    public boolean isRead() {
        return isRead;
    }
}