/*<listing chapter="1" number="4">*/
/**
 * Listing 1.4
 * @author Koffman and Wolfgang
 */
package KW.CH01;

/**
 * Class that represents a notebook computer.
 */
public class Notebook2 extends Computer {
    // Data Fields

    private static final String DEFAULT_NB_MAN = "MyBrand";
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
    public Notebook2(String man, String proc, double ram, int disk,
            double procSpeed, double screen, double wei) {
        super(man, proc, ram, disk, procSpeed);
        screenSize = screen;
        weight = wei;
    }

    /** Initializes a Notebook object with 6 properties specified. */
    public Notebook2(String proc, int ram, int disk,
            double procSpeed, double screen, double wei) {
        this(DEFAULT_NB_MAN, proc, ram, disk, procSpeed, screen, wei);
    }

    @Override
    public String toString() {
        String result = super.toString() + "\nScreen size: "
                + screenSize + " inches" + "\nWeight: " + weight
                + " pounds";

        return result;
    }

// Insert solution to programming exercise 2, section 2, chapter 1 here
}
/*</listing>*/
