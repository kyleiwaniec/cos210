import javax.swing.*;
import java.lang.Math.*;

public class RtTriangle implements ShapeInt{
	private double base = 0;
	private double height = 0;

	public RtTriangle(){};
	public RtTriangle(double bas, double hei){
		base = bas;
		height = hei;
	}
	public double[] getDimentions(){
		this.readShapeData();
		double b = base;
		double h = height;
		double[] d = {b, h};
		this.setHeight(h);
		this.setBase(b);
		return d;
	}
	public double setHeight(double h){
		return this.height = h;
	}
	public double setBase(double b){
		return this.base = b;
	}
	public int getHeight(){
		return (int) height;
	}
	public int getBase(){
		return (int) base;
	}
	public double computeArea(){
		return (height * base)/2;
	}
	public double computePerimeter(){
		return (base + height + Math.hypot(base, height) );
	}
	public void readShapeData(){
		String baseStr = JOptionPane.showInputDialog("Enter the base of the triangle");
		base = Double.parseDouble(baseStr);
		String heightStr = JOptionPane.showInputDialog("Enter the height of the trinagle");
		height = Double.parseDouble(heightStr);
	}
	public String toString(){
		return "Triangle: base: "+base+", height: "+height;
	}
}