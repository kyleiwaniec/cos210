import java.awt.*;
import java.awt.Shape.*;

public abstract class Drawable{
	protected Point pos = new Point(0,0);
	protected Color borderColor = Color.black;
	protected Color interiorColor = Color.white;

	public abstract void drawMe(Graphics g);

	public Drawable(Point poi, Color border, Color inter){
		pos = poi;
		borderColor = border;
		interiorColor = inter;
	}

	public String toString(){
		return "\nx coordinate: " + pos.x +
				", y cooordinate: " + pos.y +
				"\nBorder color: " + borderColor +
				"\nInterior color: " + interiorColor;
	}
}