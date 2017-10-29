package org.dreambot.articron.util.pathfinding;

/**
 * Author: Articron
 * Date:   17/10/2017.
 */
public enum MazeDirection {

        NORTH(1), SOUTH(5), WEST(7), EAST(3);

        MazeDirection(int shift) {
            this.shift = shift;
        }

        private int shift;

        public int getShift() {
            return shift;
        }
}

