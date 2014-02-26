import java.awt.*;
import java.awt.Shape.*;
import java.awt.geom.*;
import javafx.*;
import javafx.scene.*;
import javafx.scene.shape.*;

public class DrawableCircle extends DrawableShape{
	public DrawableCircle(double rad, Point poi, Color border, Color inter){
		super(poi, border, inter, new Circle(rad));
	}
	public void drawMe(Graphics g){
		g.setColor(interiorColor);
		Circle circle = (Circle) theShape;
		g.fillOval(	pos.x - circle.getRadius(), 
					pos.y - circle.getRadius(), 
					2*circle.getRadius(), 
					2*circle.getRadius());
		g.setColor(borderColor);
		g.drawOval(	pos.x - circle.getRadius(), 
					pos.y - circle.getRadius(), 
					2*circle.getRadius(), 
					2*circle.getRadius());		
	}
	public String toString(){
		return "Drawable "+theShape+super.toString();
	}
}