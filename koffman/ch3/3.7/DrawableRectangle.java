import java.awt.*;
import java.awt.Shape.*;
public class DrawableRectangle extends DrawableShape{
	public DrawableRectangle(int wid, int hei, Point poi, Color border, Color inter){
		super(poi, border, inter, new Rectangle(wid, hei));
	}
	public void drawMe(Graphics g){
		g.setColor(interiorColor);
		Rectangle rectangle = (Rectangle) theShape;
		g.fillRect(pos.x, pos.y, rectangle.getWidth(), rectangle.getHeight());
		g.setColor(borderColor);
		g.drawRect(pos.x, pos.y, rectangle.getWidth(), rectangle.getHeight());
	}
	public String toString(){
		return "Drawable "+theShape+super.toString();
	}
}