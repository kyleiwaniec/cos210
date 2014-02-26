import java.awt.*;
import java.awt.Shape.*;

public abstract class DrawableShape extends Drawable implements ShapeInt{
	protected ShapeInt theShape;

	public DrawableShape(Point poi, Color border, Color inter, ShapeInt aShape){
		super(poi, border, inter);
		theShape = aShape;
	}
	public DrawableShape(Point poi, Color border, Color inter){
		super(poi, border, inter);
	}
	// Delegated methods
	/** Compute the area.
		@return The Area
	*/
	public double computeArea(){
		return theShape.computeArea();
	}
	public double computePerimeter(){
		return theShape.computePerimeter();
	}
	public void readShapeData(){
		theShape.readShapeData();
	}
}