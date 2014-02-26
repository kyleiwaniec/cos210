package edu.mccc.cos210.g2d;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import edu.mccc.cos210.ds.*;
public class Graphics2DTest1 extends JFrame {
	private static final long serialVersionUID = 1L;
	public Graphics2DTest1() {
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
					new Graphics2DTest1();
				}
			}
		);
	}
	private class DrawingPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private SinglyLinkedList<Drawable> slld;
		public DrawingPanel() {
			setBackground(Color.WHITE);
			setLayout(null);
			try {
				slld = Utility.getSinglyLinkedList();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
			createDrawables();
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			AffineTransform gat = new AffineTransform();
			gat.translate(getWidth() / 2.0, getHeight() / 2.0);
			gat.scale(1.0, -1.0);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.transform(gat);
			for (int i = 0; i < slld.getSize(); i++) {
				Drawable d = i == 0 ? slld.getFirst() : slld.getNext();
				AffineTransform at = new AffineTransform();
				at.scale(100.0,  100.0);
				at.concatenate(d.getTransform());
				Shape shape = at.createTransformedShape(d.getShape());
				g2d.setPaint(d.getFillColor());
				g2d.fill(shape);
				g2d.setPaint(d.getDrawColor());
				g2d.setStroke(d.getStroke());
				g2d.draw(shape);
			}
			g2d.dispose();
		}
		private void createDrawables() {
			slld.addLast(initAnF());
			slld.addLast(initAnotherF());
			slld.addLast(initOneMoreF());
			for (int i = 0; i < 8; i++) {
				slld.addLast(randomF());
			}
			slld.addLast(initDefaultF());
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
					setFillColor(Color.YELLOW);
					setDrawColor(Color.RED);
					setStroke(new BasicStroke(3.0f));
					setShape(initF());
				}
			};
		}
		private Drawable initAnotherF() {
			return new Drawable() {
				@Override
				public void initShape() {
					AffineTransform at = new AffineTransform();
					at.rotate(Math.toRadians(40.0));
					at.scale(1.0, -1.0);
					setTransform(at);
					setFillColor(Color.GREEN);
					setDrawColor(Color.BLACK);
					setStroke(new BasicStroke(3.0f));
					setShape(initF());
				}
			};
		}
		private Drawable initOneMoreF() {
			return new Drawable() {
				@Override
				public void initShape() {
					AffineTransform at = new AffineTransform();
					at.rotate(Math.toRadians(-40.0));
					at.scale(1.0, -1.0);
					setTransform(at);
					setFillColor(Color.WHITE);
					setDrawColor(Color.BLUE);
					setStroke(new BasicStroke(3.0f));
					setShape(initF());
				}
			};
		}
		private Drawable randomF() {
			return new Drawable() {
				@Override
				public void initShape() {
					AffineTransform at = new AffineTransform();
					at.rotate(Math.toRadians(Math.random() * 360.0));
					at.scale(1.0, -1.0);
					setTransform(at);
					setFillColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
					setDrawColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
					setStroke(new BasicStroke(3.0f));
					setShape(initF());
				}
			};
		}
		private Shape initF() {
			Path2D p2d = new Path2D.Double(Path2D.WIND_EVEN_ODD);
			p2d.moveTo(-0.3602, 0.4);
			p2d.lineTo(-0.0884, -0.4384);
			p2d.lineTo(0.0866, -0.4384);
			p2d.lineTo(0.3597, 0.4);
			p2d.lineTo(0.1974, 0.4);
			p2d.lineTo(0.1337, 0.2036);
			p2d.lineTo(-0.1344, 0.2036);
			p2d.lineTo(-0.1979, 0.4);
			p2d.lineTo(-0.3602, 0.4);
			p2d.moveTo(-0.0967, 0.0813);
			p2d.lineTo(0.0, -0.2114);
			p2d.lineTo(0.0942, 0.0813);
			p2d.lineTo(-0.0967, 0.0813);
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
