package edu.mccc.cos210.car;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import edu.mccc.cos210.racecar.*;
public class DrawCar extends JFrame {
	private static final long serialVersionUID = 1L;
	private RaceCar raceCar;
	private DrawingPanel drawingPanel;
	public DrawCar() {
		super("Draw Car");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.raceCar = new DillPickle();
		this.drawingPanel = new DrawingPanel(this.raceCar);
		this.raceCar.getModel().addObserver(drawingPanel);
		add(drawingPanel, BorderLayout.CENTER);
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		this.raceCar.drawCar();
		drawingPanel.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent me) {
					if (me.getButton() == MouseEvent.BUTTON1) {
						double x = me.getPoint().getX();
						double y = me.getPoint().getY();
						Point2D p2d = new Point2D.Double(
							x - drawingPanel.getWidth() / 2.0,
							-y + drawingPanel.getHeight() / 2.0
						);
						raceCar.getModel().setPosition(p2d);
					}
				}
			}
		);
		drawingPanel.addMouseMotionListener(
			new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent me) {
					raceCar.getModel().setHeading(
						raceCar.getModel().getHeading() + Math.toDegrees(0.0005)
					);
				}
			}
		);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(
			new Runnable() {
				@Override
				public void run() {
					new DrawCar();
				}
			}
		);
	}
	private class DrawingPanel extends JPanel implements Observer {
		private static final long serialVersionUID = 1L;
		private RaceCar raceCar;
		public DrawingPanel(RaceCar rc) {
			super();
			setLayout(null);
			setBackground(Color.WHITE);
			this.raceCar = rc;
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(
				getWidth() / 2.0,
				getHeight() / 2.0
			);
			gat.scale(1.0, -1.0);
			g2d.transform(gat);
			AffineTransform at = new AffineTransform();
			Point2D p2d = this.raceCar.getModel().getPosition();
			at.translate(
				p2d.getX(),
				p2d.getY()
			);
			at.rotate(this.raceCar.getModel().getHeading());
			at.translate(
				-this.raceCar.getBuffer().getWidth() / 2.0,
				-this.raceCar.getBuffer().getHeight() / 2.0
			);
			at.scale(1.0, 1.0);
			g2d.drawRenderedImage(this.raceCar.getBuffer(), at);
			g2d.dispose();
		}
		@Override
		public void update(Observable o, Object arg) {
			repaint();
		}
	}
}
