package edu.mccc.cos210.car;
import java.awt.geom.Point2D;
import java.util.Observable;
public class RaceCarModel extends Observable {
	private Point2D position = new Point2D.Double(0.0, 0.0);
	private double heading = 0.0;
	private String name = "";
	private String driver = "";
	private double horsePower = 0.0;
	private double maxSpeed = 0.0;
	private int gearCount = 1;
	public String getName() {
		return name;
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
		setChanged();
		notifyObservers();
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
		setChanged();
		notifyObservers();
	}
	public String getDriver() {
		return this.driver;
	}
	public double getHorsePower() {
		return this.horsePower;
	}
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	public int getGearCount() {
		return this.gearCount;
	}
	void init(Class<?> clazz) {
		this.name = clazz.getSimpleName();
	}
}
