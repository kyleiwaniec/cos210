package edu.mccc.cos210.racecar;
import java.awt.*;
import java.awt.geom.*;
import edu.mccc.cos210.car.RaceCar;
public class DillPickle extends RaceCar {
	@Override
	public void draw(Graphics2D g2d) {
		Path2D p2d = new Path2D.Double();
		p2d.moveTo(0.0, 120.0);
		p2d.lineTo(
			-3.0 / (2.0 * Math.sqrt(3.0)) * 120.0,
			-0.5 * 120.0
		);
		p2d.lineTo(
			3.0 / (2.0 * Math.sqrt(3.0)) * 120.0,
			-0.5 * 120.0
		);
		p2d.closePath();
		g2d.setPaint(Color.GREEN);
		g2d.fill(p2d);
		g2d.setPaint(Color.GREEN.darker());
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.draw(p2d);
		Ellipse2D e2d = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
		g2d.setPaint(Color.WHITE);
		g2d.fill(e2d);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.draw(e2d);
	}
}
