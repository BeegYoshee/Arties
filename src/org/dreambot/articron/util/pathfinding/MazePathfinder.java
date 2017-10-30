package org.dreambot.articron.util.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MazePathfinder {

    private MazeSolver solver;

    public MazePathfinder(MazeSolver solver) {
        this.solver = solver;
    }

    private Comparator<MazeNode> nodeSorter = (o1, o2) -> {
        if (o1.getWeight() < o2.getWeight()) return +1;
        if (o2.getWeight() > o2.getWeight()) return -1;
        return 0;
    };

    public List<MazeNode> calculatePath(MazeTile start, MazeTile end, List<MazeTile> raster) {
        List<MazeNode> openList = new ArrayList<>();
        List<MazeNode> closedList = new ArrayList<>();
        MazeNode first = new MazeNode(start,null,0);
        openList.add(first);
        while (openList.size() > 0) {
            openList.sort(nodeSorter);
            MazeNode current = openList.get(0);
            if (current.getTile().equals(end)) {
                List<MazeNode> path = new ArrayList<>();
                while (current.getParent() != null) {
                    path.add(current);
                    current = current.getParent();
                }
                path.add(first);
                Collections.reverse(path);
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            List<MazeTile> neighbours = getNeighbours(current.getTile(),raster);
            for (MazeTile neighbour : neighbours) {
                MazeNode edge = new MazeNode(neighbour,current,current.getWeight() + 1);
                if (!closedList.contains(edge)) {
                    openList.add(edge);
                }
            }
        }
        return null;
    }


    private List<MazeTile> getNeighbours(MazeTile tile, List<MazeTile> raster) {
        List<MazeTile> neighbours = new ArrayList<>();
        for (MazeDirection d : MazeDirection.values()) {
            MazeTile neighbour = solver.getMazeRasterizer().getNeighbour(tile,d,raster);//getAdjacentEdge(tile,d);
            if (neighbour != null && !neighbour.equals(tile)) {
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }

}
