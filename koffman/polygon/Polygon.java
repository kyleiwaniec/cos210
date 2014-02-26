import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.lang.Math;

public class Polygon extends JFrame implements ActionListener, Cloneable{
	public DrawingPanel d = new DrawingPanel();
	public ShapeData sd = new ShapeData();
	public LinkedList<Point> pts = sd.getPoints();

	// public LinkedList<Point> pts = deepCopy(ptss);



	public Polygon() {
		super(" =^.^=  FELINE REDUCTION  =^.^= ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton reduceBtn = new JButton("Reduce");
        add(reduceBtn, BorderLayout.PAGE_START);
        reduceBtn.addActionListener(this);
        reduceBtn.setActionCommand("goagain");

        JButton undoBtn = new JButton("Undo");
        add(undoBtn, BorderLayout.PAGE_END);
        undoBtn.addActionListener(this);
        undoBtn.setActionCommand("undo");

		add(d, BorderLayout.CENTER);
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
	public void actionPerformed(ActionEvent e) {
        if ("goagain".equals(e.getActionCommand())) {
        	d.reduceShape();
        	repaint();
        } else if ("undo".equals(e.getActionCommand())){
        	d.undo();
        	repaint();
        	d.undo();
           // put the last point back and repaint
        }
    }
    //public Flags flags = new Flags();
	

	private class DrawingPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		private int leastSig = 100;
		private int lastRmd = 100;

		private LinkedList<Point> idxList = new LinkedList();

		public void reduceShape(){

			System.out.println(idxList.toString());

			ListIterator pit = pts.listIterator();
			Point a;
			Point b;
			Point c;
			double temp = 0;
			double min = 100; //arbitrary value. // should redo differently
			double max = 0;
			
			

			while(pit.hasNext()){
					a = (Point) pit.next();
				if(pit.hasNext()){
					b = (Point) pit.next();					
				}else{
					break;
				}
				if(pit.hasNext()){
					c = (Point) pit.next();
				}else{
					break;
				}
				// calculate significance:
				double ax = a.getXCoor();
				double ay = a.getYCoor();
				double bx = b.getXCoor();
				double by = b.getYCoor();
				double cx = c.getXCoor();
				double cy = c.getYCoor();

				double d1 = Math.pow((Math.pow((bx-ax), 2) + Math.pow((by-ay), 2)), 0.5);
				double d2 = Math.pow((Math.pow((bx-cx), 2) + Math.pow((by-cy), 2)), 0.5);
				double d3 = Math.pow((Math.pow((ax-cx), 2) + Math.pow((ay-cy), 2)), 0.5);

				double delta = Math.abs(d1 + d2 - d3);

				if(delta > max){
					max = delta;
				}
				if(delta < min){
					min = delta;
					leastSig++;
					b.setLeastSig(leastSig);
				}
				pit.previous();
				pit.previous();
			}
		}
		public void undo(){
			// add(int index, E element)
			if(idxList.size() > 0){
				Point lastRemoved = (Point) idxList.getLast();
				int atIdx = lastRemoved.getIdx();
				pts.add(atIdx, lastRemoved);
				idxList.remove(lastRemoved);
			}else{
				System.out.println("you're back to the begginging");
			}

		}
		


		public DrawingPanel() {
			setBackground(Color.WHITE);
			setLayout(null);

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
			at.scale(2.5,  2.5);
			at.concatenate(d.getTransform());
			Shape shape = at.createTransformedShape(d.getShape());
			g2d.setPaint(d.getFillColor());
			g2d.fill(shape);
			g2d.setPaint(d.getDrawColor());
			//g2d.setStroke(d.getStroke());
			
			g2d.draw(shape);
			g2d.dispose();

		}

		private Drawable initAnF() {
			return new Drawable() {
				@Override
				public void initShape() {
					AffineTransform at = new AffineTransform();
					at.scale(1.0, -1.0);
					setTransform(at);
					// setFillColor(Color.GRAY);
					// setDrawColor(Color.BLACK);
					// setStroke(new BasicStroke(3.0f));
					setShape(path()); // Path
				}
			};
		}
		
		private Shape path() {
			Path2D p2d = new Path2D.Double(Path2D.WIND_EVEN_ODD);

			//LinkedList<Point> pts = sd.getPoints();
			ListIterator pit = pts.listIterator();

				if(pit.hasNext()){
					Point p = (Point) pit.next();
					p2d.moveTo(p.getXCoor(),p.getYCoor());
				}

				while(pit.hasNext()){
					Point p = (Point) pit.next();

					if(p.getLeastSig() != leastSig){
						p2d.lineTo(p.getXCoor(),p.getYCoor());
					}else{
						int idx = pts.indexOf(p); // keeps track of removed items in an idxList
						p.setIdx(idx);
						idxList.add(p);
						pit.remove();
					}
				}

			p2d.closePath();
			return p2d;
		}
		public LinkedList<Point> deepCopy(LinkedList<Point> llist){
			LinkedList<Point> copy = new LinkedList<Point>();
			ListIterator<Point> lit = llist.listIterator();
			while(lit.hasNext()){
				Point p = (Point) lit.next();
				Point n = (Point) p.clone();

				copy.add(n);
			}
			//System.out.println("deepcopy: "+copy.toString());
			return copy;
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