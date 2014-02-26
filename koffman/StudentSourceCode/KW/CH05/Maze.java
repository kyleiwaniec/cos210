/*<listing chapter="5" number="4">*/
package KW.CH05;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if (x < 0 || y < 0
                || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false; // Cell is out of bounds.
        } else if (!maze.getColor(x, y).equals(BACKGROUND)) {
            return false; // Cell is on barrier or dead end.
        } else if (x == maze.getNCols() - 1
                && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH); // Cell is on path
            return true; // and is maze exit.
        } else { // Recursive case.
            // Attempt to find a path from each neighbor.
            // Tentatively mark cell as on path.
            maze.recolor(x, y, PATH);
            if (findMazePath(x - 1, y)
                    || findMazePath(x + 1, y)
                    || findMazePath(x, y - 1)
                    || findMazePath(x, y + 1)) {
                return true;
            } else {
                maze.recolor(x, y, TEMPORARY); // Dead end.
                return false;
            }
        }
    }

// Insert solution to programming exercise 2, section 6, chapter 5 here

// Insert solution to programming exercise 3, section 6, chapter 5 here
}
/*</listing>*/
