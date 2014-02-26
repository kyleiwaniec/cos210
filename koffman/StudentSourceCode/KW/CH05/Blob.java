/*<listing chapter="5" number="3">*/
package KW.CH05;

/**
 * Class that solves problem of counting abnormal cells.
 *  @author Koffman and Wolfgang
 **/
public class Blob implements GridColors {

    /** The grid */
    private TwoDimGrid grid;

    /** Constructors */
    public Blob(TwoDimGrid grid) {
        this.grid = grid;
    }

    /**
     * Finds the number of cells in the blob at (x,y).
     * @pre Abnormal cells are in ABNORMAL color;
     *      Other cells are in BACKGROUND color.
     * @post All cells in the blob are in the TEMPORARY color.
     * @param x The x-coordinate of a blob cell
     * @param y The y-coordinate of a blob cell
     * @return The number of cells in the blob that contains (x, y)
     */
    public int countCells(int x, int y) {
        int result;

        if (x < 0 || x >= grid.getNCols()
                || y < 0 || y >= grid.getNRows()) {
            return 0;
        } else if (!grid.getColor(x, y).equals(ABNORMAL)) {
            return 0;
        } else {
            grid.recolor(x, y, TEMPORARY);
            return 1
                    + countCells(x - 1, y + 1) + countCells(x, y + 1)
                    + countCells(x + 1, y + 1) + countCells(x - 1, y)
                    + countCells(x + 1, y) + countCells(x - 1, y - 1)
                    + countCells(x, y - 1) + countCells(x + 1, y - 1);
        }
    }

// Insert solution to programming exercise 1, section 5, chapter 5 here

// Insert solution to programming exercise 2, section 5, chapter 5 here
}
