import java.awt.*;
import java.awt.Shape.*;
import javax.swing.*;

public class TestDraw extends JPanel{
	private DrawableShape[] drawableShapes;

	private TestDraw(){


		DrawableShape[] drawableShapes = {
			new DrawableRectangle(100, 200, new Point(100,100), Color.RED, Color.YELLOW),
			new DrawableTriangle(new Point(250,300), Color.BLUE, Color.GREEN)
		};
	
		this.drawableShapes = drawableShapes;
		
	}
	public static void main(String[] args){
		JFrame frame = new JFrame("Test Draw");
		frame.setSize(400,450);
		frame.setContentPane(new TestDraw());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i = 0; i < drawableShapes.length; i++){
			drawableShapes[i].drawMe(g);
		}

		for (int i = 0; i < drawableShapes.length; i++){
			System.out.println("\n" + drawableShapes[i]);
			System.out.println("Area: " + drawableShapes[i].computeArea()
										+ ", perimeter: "
										+ drawableShapes[i].computePerimeter());
		}
	}
}