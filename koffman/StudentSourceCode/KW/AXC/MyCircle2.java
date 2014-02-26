/*<listing chapter="C" number="4">*/
package KW.AXC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/** Class MyCircle is a JPanel that consists of a circle enclosed
 *  in a square. The square is always displayed, but the circle can
 *  be turned on and off.
 *  @author Koffman & Wolfgang
 */
public class MyCircle2 extends JPanel {

    // Data Fields
    /** The size */
    private int size;
    /** Display state for the circle */
    private boolean showCircle = true;

    // Constructors
    /** Construct a MyCircle object of the specified size.
     *  @param size The size of the circle in pixels
     */
    public MyCircle2(int size) {
        this.size = size;
        // Encapsulate the object�s dimensions in a Dimension object.
        Dimension dims = new Dimension(size, size);
        // Set the object�s dimensions.
        setPreferredSize(dims);
        setMaximumSize(dims);
        setMinimumSize(dims);
    }

    /** Toggle the state of the circle. */
    public void toggleState() {
        showCircle = !showCircle;
        repaint(); // Calls paint to redraw the object.
    }

// Insert solution to programming exercise 1, section 2, chapter C here
}
/*</listing>*/
