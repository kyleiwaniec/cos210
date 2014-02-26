import java.awt.*;
public class DrawableTriangle extends DrawableShape{
	public DrawableTriangle(Point poi, Color border, Color inter){
		super(poi, border, inter, new RtTriangle());
	}

	public void drawMe(Graphics g){
		Polygon rtTri = new Polygon();
		RtTriangle triangle = (RtTriangle) theShape;

		double[] dimentions = new double[2];
		dimentions = triangle.getDimentions();

		rtTri.addPoint(pos.x, pos.y);
		rtTri.addPoint(pos.x, pos.y - (int) dimentions[1]);
		rtTri.addPoint(pos.x + (int) dimentions[0], pos.y);
		g.setColor(interiorColor);
		g.fillPolygon(rtTri);
		g.setColor(borderColor);
		g.drawPolygon(rtTri);		
	}
	public String toString(){
		return "Drawable "+theShape+super.toString();
	}
}