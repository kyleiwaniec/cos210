//package edu.mccc.cos210.lecture;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class Lecture1 extends JFrame {
	public static final long serialVersionUID = -1L; //anything in Swng is serializable
	public Lecture1() {
		super("Lecture1"); //default constructor gets lost when any other constructors are created in the class
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(800, 600);
		add(
			new DrawingPanel(),
			BorderLayout.CENTER
		);
		pack();
		setLocationRelativeTo(null);// put in primerealestate: center
		setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(
			new Runnable(){ //annonymous class
				@Override
				public void run() {
					new Lecture1();
				}
			}
		);
	}
	private class DrawingPanel extends JPanel {
		public static final long serialVersionUID = -1L; //anything in Swng is serializable
		public static final double CIRCLE_DIAMETER = 128;
		public static final double CIRCLE_RADIUS = CIRCLE_DIAMETER/2;
		public DrawingPanel() {
			setLayout(null);
			setBackground(new Color(20, 50, 200));
		}
		public Dimension getPreferredSize() {
			return new Dimension(600, 400);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g.create();
			
			double winWidth = getWidth();
			double winHeight = getHeight();
			double ballDiameter = winHeight/3;
			double vertOffset = (winHeight - (ballDiameter + 30)); // 30 adds some space at the bottom of the snowman
			double lastBallDiam = 0;
			double squish = ballDiameter/16;
			double GOLDEN_RATIO = 1.61803398875;
			
			// Snowman body:
			// TODO: make into loop
			Ellipse2D e2d_b = new Ellipse2D.Double(
					winWidth/2 - ballDiameter/2, // x pos
					vertOffset - lastBallDiam, // y pos
					ballDiameter, // horizontal asymtote
					ballDiameter // vertical asymtote
					);
			vertOffset -= (lastBallDiam - (squish * 2));
			ballDiameter = ballDiameter / GOLDEN_RATIO;
			lastBallDiam = ballDiameter;
			
			Ellipse2D e2d_m = new Ellipse2D.Double(
					winWidth/2 - ballDiameter/2, // x pos
					vertOffset - lastBallDiam, // y pos
					ballDiameter, // horizontal asymtote
					ballDiameter // vertical asymtote
					);
			vertOffset -= (lastBallDiam - squish);
			ballDiameter = ballDiameter / GOLDEN_RATIO;
			lastBallDiam = ballDiameter;
			
			Ellipse2D e2d_t = new Ellipse2D.Double(
					winWidth/2 - ballDiameter/2, // x pos
					vertOffset - lastBallDiam, // y pos
					ballDiameter, // horizontal asymtote
					ballDiameter // vertical asymtote
					);
			//vertOffset -= (lastBallDiam - squish);
			//ballDiameter = ballDiameter / GOLDEN_RATIO;
			//lastBallDiam = ballDiameter;
			
			g2d.setPaint(Color.WHITE);
			//g2d.setPaint(new Color(255, 0, 200));
			g2d.fill(e2d_b);
			g2d.draw(e2d_b);
			g2d.fill(e2d_m);
			g2d.draw(e2d_m);
			g2d.fill(e2d_t);
			g2d.draw(e2d_t);
			
			// hat rim
			g2d.setColor(Color.BLACK);
			
			double hatXpos = winWidth/2 - ballDiameter/2;
			double hatYpos = vertOffset - lastBallDiam;
			double hatWidth = lastBallDiam;
			double hatHeight = lastBallDiam/6;
			
			Rectangle hatRim = new Rectangle(
							(int) (hatXpos),
							(int) (hatYpos),
							(int) (hatWidth),
							(int) (hatHeight)
							);
			g2d.draw(hatRim);
			g2d.fill(hatRim);
			
			hatXpos += (hatWidth * 0.1);
			hatWidth -= (hatWidth * 0.2);
			hatHeight *= 10;
			hatYpos -= hatHeight;
			
			//hat top
			Rectangle hatTop = new Rectangle(
							(int) (hatXpos),
							(int) (hatYpos),
							(int) (hatWidth),
							(int) (hatHeight)
							);
			g2d.draw(hatTop);
			g2d.fill(hatTop);
			
			
			g2d.dispose();
		}
	}
}
