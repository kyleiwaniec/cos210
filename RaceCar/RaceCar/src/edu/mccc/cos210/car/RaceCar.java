package edu.mccc.cos210.car;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
public abstract class RaceCar {
	private BufferedImage bi = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
	private RaceCarModel model = new RaceCarModel();
	public RaceCar() {
		Class<?> clazz = this.getClass();
		this.model.init(clazz);
	}
	public RaceCarModel getModel() {
		return this.model;
	}
	public RenderedImage getBuffer() {
		return this.bi;
	}
	final void drawCar() {
		Graphics2D g2dCar = this.bi.createGraphics();
		AffineTransform at = new AffineTransform();
		at.translate(this.bi.getWidth() / 2.0, this.bi.getHeight() / 2.0);
		g2dCar.transform(at);
		draw(g2dCar);
		g2dCar.dispose();
	}
	protected abstract void draw(Graphics2D g2d);
}
