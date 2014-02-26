/*<listing chapter="1" number="3">*/
/**
 * Listing 1.3
 * @author Koffman and Wolfgang
 */
package KW.CH01;

/**
 * Class that represents a notebook computer.
 */
public class Notebook extends Computer {
    // Data Fields

    private double screenSize;
    private double weight;

    // Methods
    /**
     * Initializes a Notebook object with all properties specified.
     * @param man The computer manufacturer
     * @param proc The processor type
     * @param ram The RAM size
     * @param disk The disk size
     * @param procSpeed The processor speed
     * @param screen The screen size
     * @param wei The weight
     */
    public Notebook(String man, String proc, double ram, int disk,
            double procSpeed, double screen, double wei) {
        super(man, proc, ram, disk, procSpeed);
        screenSize = screen;
        weight = wei;
    }

// Insert solution to programming exercise 1, section 3, chapter 1 here

// Insert solution to programming exercise 2, section 2, chapter 1 here

// Insert solution to programming exercise 3, section 3, chapter 1 here

// Insert solution to programming exercise 2, section 5, chapter 1 here
}
/*</listing>*/
