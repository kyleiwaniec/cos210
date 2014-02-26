/** DrawableRectangle.java
 *  Represents a drawable rectangle.
 *  @author Koffman and Wolfgang
 */
package KW.AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;

public class DrawableRectangle extends DrawableShape {

    // Data Fields
    /** The name of this shape */
    private static final String NAME = "Rectangle";

    // Constructors
    /** Construct a DrawableRectangle with the specified values
     *  @param wid The width
     *  @param hei The height
     *  @param poi The coordinates of the upper left corner
     *  @param bor The border color
     *  @param in The interior color
     */
    public DrawableRectangle(int wid, int hei,
            Point pio, Color bor, Color in) {
        super(pio, bor, in, new Rectangle(wid, hei));
    }

    /** Construct a default DrawableRectangle
     */
    public DrawableRectangle() {
        super(new Rectangle());
    }

    /** Draw this shape
     *  @param g The graphics context
     */
    public void drawMe(Graphics g) {
        g.setColor(interiorColor);
        BoundingRectangle br = getBoundingRectangle();
        g.fillRect(br.x, br.y, br.w, br.h);
        g.setColor(borderColor);
        g.drawRect(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = false;
    }

// Insert solution to programming exercise 1, section 7, chapter C here

    /** Create a new instance of a DrawableRectangle
     *  @return a new instance of a DrawableRectangle
     */
    @Override
    public Drawable newInstance() {
        return new DrawableRectangle(0, 0, new Point(0, 0),
                borderColor, interiorColor);
    }

// Insert solution to programming exercise 1, section 6, chapter C here

    /** Create a string representation of this shape
     *  @return a string representation of this shape
     */
    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    /** Get the bounding rectangle of this shape.
     *  The bounding rectangle has its origin at the upper left
     *  corner.
     *  @return the bounding rectangle of this shape
     */
    private BoundingRectangle getBoundingRectangle() {
        BoundingRectangle result = new BoundingRectangle();
        if (getWidth() < 0) {
            result.x = pos.x + getWidth();
            result.w = -getWidth();
        } else {
            result.x = pos.x;
            result.w = getWidth();
        }
        if (getHeight() < 0) {
            result.y = pos.y + getHeight();
            result.h = -getHeight();
        } else {
            result.y = pos.y;
            result.h = getHeight();
        }
        return result;
    }

    /** Static inner class to represent the bouding rectangle
     */
    private static class BoundingRectangle {

        /** The x-coordinate of the upper left corner */
        private int x;
        /** The y-coordinate of the upper left corner */
        private int y;
        /** The width */
        private int w;
        /** The height */
        private int h;
    }
}
