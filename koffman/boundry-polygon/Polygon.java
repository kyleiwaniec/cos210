import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Polygon extends JFrame {
	public Polygon() {
		super("J3D Test1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(
			new DrawingPanel(), BorderLayout.CENTER
		);
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] sa) throws Exception {
		EventQueue.invokeAndWait(
			new Runnable() {
				public void run() {
					new Polygon();
				}
			}
		);
	}
	private class DrawingPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		//private SinglyLinkedList<Drawable> slld;
		public DrawingPanel() {
			setBackground(Color.WHITE);
			setLayout(null);
			// try {
			// 	slld = Utility.getSinglyLinkedList();
			// } catch (Exception ex) {
			// 	System.err.println(ex.getMessage());
			// 	System.exit(-1);
			// }
			// createDrawables();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() / 2.0, getHeight() / 2.0);
			gat.scale(1.0, -1.0);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.transform(gat);

			Drawable d =  initAnF();
			AffineTransform at = new AffineTransform();
			at.scale(8.0,  8.0);
			at.concatenate(d.getTransform());
			Shape shape = at.createTransformedShape(d.getShape());
			g2d.setPaint(d.getFillColor());
			g2d.fill(shape);
			g2d.setPaint(d.getDrawColor());
			//g2d.setStroke(d.getStroke());
			g2d.draw(shape);

			g2d.dispose();
		}

		private Drawable initDefaultF() {
			return new Drawable() {
				@Override
				public void initShape() {
					setShape(initF());
				}
			};
		}
		private Drawable initAnF() {
			return new Drawable() {
				@Override
				public void initShape() {
					AffineTransform at = new AffineTransform();
					at.scale(1.0, -1.0);
					setTransform(at);
					setFillColor(Color.BLACK);
					//setDrawColor(Color.RED);
					//setStroke(new BasicStroke(3.0f));
					setShape(initF());
				}
			};
		}
		
		private Shape initF() {
			Path2D p2d = new Path2D.Double(Path2D.WIND_EVEN_ODD);
			p2d.moveTo(-3.333,43);
p2d.lineTo(-5.701,37.436);
p2d.lineTo(-10.333,33);
p2d.lineTo(-13.389,31.036);
p2d.lineTo(-16.333,29);
p2d.lineTo(-17.349,27.518);
p2d.lineTo(-18.333,26);
p2d.lineTo(-20.833,23.865);
p2d.lineTo(-23.333,21);
p2d.lineTo(-23.818,20.018);
p2d.lineTo(-24.333,19);
p2d.lineTo(-24.934,17.668);
p2d.lineTo(-25.333,16);
p2d.lineTo(-26.02,5.302);
p2d.lineTo(-26.333,-8);
p2d.lineTo(-27.925,-16.977);
p2d.lineTo(-28.333,-26);
p2d.lineTo(-19.595,-23.298);
p2d.lineTo(-11.333,-18);
p2d.lineTo(-2.195,-18.383);
p2d.lineTo(8.667,-19);
p2d.lineTo(11.363,-18.387);
p2d.lineTo(13.667,-18);
p2d.lineTo(14.975,-18.477);
p2d.lineTo(16.667,-19);
p2d.lineTo(18.944,-19.267);
p2d.lineTo(20.667,-20);
p2d.lineTo(22.195,-20.459);
p2d.lineTo(23.667,-21);
p2d.lineTo(28.362,-23.813);
p2d.lineTo(34.667,-25);
p2d.lineTo(33.633,-14.263);
p2d.lineTo(31.667,-2);
p2d.lineTo(33.014,-1.348);
p2d.lineTo(34.667,-1);
			//p2d.closePath();
			return p2d;
		}
		private abstract class Drawable {
			private AffineTransform transform = new AffineTransform();
			private Paint fillColor = Color.WHITE;
			private Paint drawColor = Color.BLACK;
			private Stroke stroke = new BasicStroke(0.0f);
			private Shape shape = new Path2D.Double();
			public Drawable() {
				initShape();
			}
			public AffineTransform getTransform() {
				return transform;
			}
			public Paint getFillColor() {
				return fillColor;
			}
			public Paint getDrawColor() {
				return drawColor;
			}
			public Stroke getStroke() {
				return stroke;
			}
			public Shape getShape() {
				return shape;
			}
			public void setTransform(AffineTransform at) {
				this.transform = at;
			}
			public void setFillColor(Paint p) {
				this.fillColor = p;
			}
			public void setDrawColor(Paint p) {
				this.drawColor = p;
			}
			public void setStroke(Stroke s) {
				this.stroke = s;
			}
			public void setShape(Shape shape) {
				this.shape = shape;
			}
			public abstract void initShape();
		}
	}
}