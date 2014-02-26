// Insert solution to programming project 8, chapter -1 here

    /*<listing chapter="8" number="12">*/
    /**
     * Sort the threads
     * @param g Graphics environment */
    public void sort() {
        int red = 0;
        int white = height - 1;
        int blue = height - 1;
        // Invariant:
        // 0 <= i < red      ==> threads[i].getColor() == Color.RED
        // red <= i <= white ==> threads[i].getColor() is unknown
        // white < i < blue  ==> threads[i].getColor() == Color.WHITE
        // blue < i < height ==> threads[i].getColor() == Color.BLUE
        while (red <= white) {
            if (threads[white].getColor() == Color.WHITE) {
                --white;
            } else if (threads[white].getColor() == Color.RED) {
                swap(red, white);
                ++red;
            } else { // threads[white].getColor() == Color.BLUE
                swap(white, blue);
                --white;
                --blue;
            }
            repaint();
        }
        // red == white so unknown region is now empty
    }
    /*</listing>*/

// Insert solution to programming project 8, chapter -1 here
