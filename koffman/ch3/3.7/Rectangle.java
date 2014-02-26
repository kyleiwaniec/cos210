import javax.swing.*;

public class Rectangle implements ShapeInt{
	private int width = 0;
	private int height = 0;
	public Rectangle(int wid, int hei){
		width = wid;
		height = hei;
	}

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public double computeArea(){
		return width * height;
	}
	public double computePerimeter(){
		return 2 * (width + height);
	}
	public void readShapeData(){
		String widthStr = JOptionPane.showInputDialog("Enter the width of the rectangle");
		width = Integer.parseInt(widthStr);
		String heightStr = JOptionPane.showInputDialog("Enter the height of the rectangle");
		height = Integer.parseInt(heightStr);
	}
	public String toString(){
		return "Rectangle: width: "+width+", height: "+height;
	}
}