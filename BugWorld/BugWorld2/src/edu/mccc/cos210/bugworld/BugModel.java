package edu.mccc.cos210.bugworld;

import java.awt.event.ActionEvent;
import java.util.Observable;

public class BugModel extends Observable {
	private static final double PI_OVER_2 = Math.PI / 2.0;
	private String moniker;
	private boolean turbo;
	private int frame = 0;
	private int frameScale = 0;
	private double x;
	private double y;
	private double speed;
	private double heading;
	private final double speedBump;
	private final double headingBump;
	private final double maxSpeed;
	public BugModel(String bugName) {
		this(bugName, false, 0.0, 0.0, 0.0, 0.0, 1.0, 10.0, 4.0);
	}
	private BugModel(
		String moniker,
		boolean turbo,
		double x,
		double y,
		double speed,
		double heading,
		double speedBump,
		double headingBump,
		double maxSpeed
	) {
		this.moniker = moniker;
		this.turbo = false;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.heading = heading;
		this.speedBump = speedBump;
		this.headingBump = headingBump;
		this.maxSpeed = maxSpeed;
	}
	public int getFrame() {
		return this.frame;
	}
	void setFrame(int n) {
		this.frame = n;
	}
	void toggleFrame() {
		setFrame(getFrame() == 0 ? 1 : 0);
	}
	int getFrameScale() {
		return this.frameScale;
	}
	void setFrameScale(int n) {
		this.frameScale = n;
	}
	public String getMoniker() {
		return this.moniker;
	}
	boolean isTurbo() {
		return this.turbo;
	}
	public void setTurbo(boolean b) {
		this.turbo = b;
	}
	public void toggleTurbo() {
		this.turbo = !isTurbo();
		setChanged();
		notifyObservers();
	}
	public double getX() {
		return this.x;
	}
	public void reset() {
		this.x = 0.0;
		this.y = 0.0;
		this.speed = 0.0;
		this.heading = 0.0;
		this.frame = 0;
		this.frameScale = 0;
	}
	void setX(double x) {
		this.x = x;
		setChanged();
		notifyObservers();
	}
	public double getY() {
		return this.y;
	}
	void setY(double y) {
		this.y = y;
		setChanged();
		notifyObservers();
	}
	public double getSpeed() {
		return this.speed;
	}
	public void setSpeed(double s) {
		this.speed = s < 0.0 ? 0.0 : s > getMaxSpeed() ? getMaxSpeed() : s;
		setChanged();
		notifyObservers();
	}
	public double getHeading() {
		return this.heading;
	}
	public void setHeading(double h) {
		this.heading = h;
		setChanged();
		notifyObservers();
	}
	public double getSpeedBump() {
		return this.speedBump;
	}
	public double getHeadingBump() {
		return this.headingBump;
	}
	double getMaxSpeed() {
		return this.maxSpeed;
	}
	private void computeXY() {
		double newX = getSpeed() * (isTurbo() ? 4.0 : 1.0) * Math.cos(getHeading() + PI_OVER_2) + getX();
		double newY = getSpeed() * (isTurbo() ? 4.0 : 1.0) * Math.sin(getHeading() + PI_OVER_2) + getY();
		if (!isCollision(newX, newY)) {
			setX(newX);
			setY(newY);
		}
		setChanged();
		notifyObservers();
	}
	private boolean isCollision(double newX, double newY) {
		boolean b = false;
		double w = 1024 / 2.0;
		double h = 768 / 2.0;
		if (
			newX < -w + 32.0 ||
			newX > w - 32.0 ||
			newY < -h + 32.0 ||
			newY > h - 32.0
		) {
			b = true;
		}
		return b;
	}
	public void doActionPerformed(ActionEvent ae) {
		computeXY();
		setFrameScale(getFrameScale() + 1);
		if (getFrameScale() > 3) {
			toggleFrame();
			setFrameScale(0);
		}
	}
}
