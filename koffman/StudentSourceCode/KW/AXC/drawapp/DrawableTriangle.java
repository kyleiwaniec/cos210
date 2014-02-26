/** DrawableTriangle.java
 *  @author Koffman & Wolfgang
 *  Represents a drawable triangle.
 */
package KW.AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.Icon;

public class DrawableTriangle extends DrawableShape {

    /* Data fields */
    private static String NAME = "Triangle";

    /* Methods */
    /* Constructors */
    public DrawableTriangle(int bas, int h,
            Point p, Color b, Color i) {
        super(p, b, i, new RtTriangle(bas, h));
    }

    public DrawableTriangle() {
        super(new RtTriangle());
    }

    // Methods
    @Override
    public Drawable newInstance() {
        return new DrawableTriangle(0, 0, new Point(0, 0),
                borderColor,
                interiorColor);
    }

    private Polygon makePolygon() {
        RtTriangle triangle = (RtTriangle) theShape;
        Polygon rtTri = new Polygon();
        rtTri.addPoint(pos.x, pos.y);
        rtTri.addPoint(pos.x, pos.y - triangle.getHeight());
        rtTri.addPoint(pos.x + triangle.getBase(), pos.y);
        return rtTri;
    }

    @Override
    public void drawMe(Graphics g) {
        Polygon rtTri = makePolygon();
        g.setColor(interiorColor);
        g.fillPolygon(rtTri);
        g.setColor(borderColor);
        g.drawPolygon(rtTri);
    }

// Insert solution to programming exercise 1, section 7, chapter C here

    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

// Insert solution to programming exercise 1, section 6, chapter C here

}
