package KW.AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;

public class DrawableCircle extends DrawableShape {
    // Data fields

    private static final String NAME = "Circle";

    // Methods
    // Constructors
    public DrawableCircle(int rad,
            Point poi, Color bor, Color in) {
        super(poi, bor, in, new Circle(rad));
    }

    public DrawableCircle() {
        super(new Circle());
    }

    // Operations
    @Override
    public Drawable newInstance() {
        return new DrawableCircle(0, new Point(0, 0),
                borderColor,
                interiorColor);
    }

    @Override
    public void drawMe(Graphics g) {
        BoundingRectangle br = getBoundingRectangle();
        g.setColor(interiorColor);
        g.fillOval(br.x, br.y, br.w, br.h);
        g.setColor(borderColor);
        g.drawOval(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = false;
    }

// Insert solution to programming exercise 1, section 7, chapter C here

    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    // Accessors:
    private double getRadius() {
        return ((Circle) theShape).getRadius();
    }

// Insert solution to programming exercise 1, section 6, chapter C here

    private BoundingRectangle getBoundingRectangle() {
        BoundingRectangle result = new BoundingRectangle();
        int radius = (int) getRadius();
        result.x = pos.x - radius;
        result.y = pos.y - radius;
        result.w = 2 * radius;
        result.h = 2 * radius;
        return result;
    }

    private static class BoundingRectangle {
        private int x;
        private int y;
        private int w;
        private int h;
    }
}

