package org.dreambot.articron.util.pathfinding;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.articron.fw.ScriptContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public class MazeSolver {


    private boolean isRead = false;

    private ScriptContext ctx;
    private MazeRasterizer mazeRasterizer;
    private MazePathfinder pathfinder;
    private List<MazeNode> path = new ArrayList<>();
    private List<MazeTile> raster = new ArrayList<>();
    private HashMap<MazeDirection, List<Tile>> directionMap = new HashMap<>();
    private MazeTile lastStatueTile;
    private MazeTile expectedLocationTile;

    public MazeSolver(ScriptContext ctx) {
        this.ctx = ctx;
        this.mazeRasterizer = new MazeRasterizer(ctx);
        this.pathfinder = new MazePathfinder(this);
    }

    public void reset() {
        mazeRasterizer.reset();
        directionMap.clear();
        path.clear();
        raster.clear();
        isRead = false;
    }

    public void readMaze() {
        raster = mazeRasterizer.calculateRaster();
        createDirectionMap();
        path = pathfinder.calculatePath(mazeRasterizer.getStartLocation(), mazeRasterizer.getDestination(), raster);
        isRead = (raster.size() > 0 && path.size() > 0);
    }

    private void createDirectionMap() {
        for (MazeDirection d : MazeDirection.values()) {
            directionMap.put(d,getTilesForDirection(d));
        }
    }

    public boolean isInCorrectRow() {
        MazeDirection d = getNextDirection();
        return d != null && directionMap.get(d).contains(ctx.getDB().getLocalPlayer().getTile());
    }

    public List<Tile> getCorrectRow() {
        return directionMap.get(getNextDirection());
    }

    public MazeTile getStatueTile() {
        //MethodProvider.log("raster = " + Boolean.toString(raster != null));
        NPC guard =  ctx.getDB().getNpcs().closest(6777);
        if (guard == null) return null;
        return mazeRasterizer.getTile(tile ->
                tile.getWorldTile().equals(
                        guard.getTile()
                ),raster);
    }

    public void saveStatueLocation() {
        if (getStatueTile() != null) {
            lastStatueTile = getStatueTile();
        }
    }

    public Tile getCastTile() {
        MazeDirection d = getNextDirection();
        if (d == null) return null;
        List<Tile> row = directionMap.get(d);
        row.sort(Comparator.comparingDouble(Tile::distance));
        return row.get(Calculations.random(0,row.size()));
    }

    private MazeDirection getNextDirection() {
        boolean prediction = false;
        if (getStatueTile() == null) {
            prediction = true;
        }
        MazeTile start = prediction ? path.get(forIndex(lastStatueTile) + 1).getTile() : getStatueTile();
        if (start.equals(mazeRasterizer.getDestination())) return null;
        MazeTile goal = start == null ? null : path.get(forIndex(start) + 1).getTile();
        expectedLocationTile = getStatueTile() != null ? getStatueTile() : prediction ? start : goal;
        if (goal != null) {
            if (goal.getY() > start.getY()) {
                return MazeDirection.SOUTH;
            } else if (goal.getY() < start.getY()) {
                return MazeDirection.NORTH;
            } else if (goal.getX() > start.getX()) {
                return MazeDirection.WEST;
            } else if (goal.getX() < start.getX()) {
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
                Tile rasterTile = mazeRasterizer.getTile(n -> n.getX() == ((d == MazeDirection.NORTH)? 0 : 9) && n.getY() == ((d == MazeDirection.NORTH)? 0 : 9),raster).getWorldTile();
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
                rasterTile = mazeRasterizer.getTile(n -> n.getX() == ((d == MazeDirection.EAST) ? 0 : 9) && n.getY() == 0,raster).getWorldTile();
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


    private int forIndex(MazeTile tile) {
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

    public boolean isRead() {
        return isRead;
    }

    public MazeRasterizer getMazeRasterizer() {
        return mazeRasterizer;
    }

    public MazePathfinder getPathfinder() {
        return pathfinder;
    }

    public MazeTile getExpectedLocationTile() {
        return expectedLocationTile;
    }
}