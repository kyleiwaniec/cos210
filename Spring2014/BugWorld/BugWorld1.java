import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
public class BugWorld1 extends JFrame {
	private static final long serialVersionUID = 1L;
	private BugModel bugModel = new BugModel();
	private MyJPanel mjp = new MyJPanel(bugModel);
	public BugWorld1() {
		super("BugWorld");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(
			createToolBar(),
			BorderLayout.NORTH
		);
		add(
			this.mjp,
			BorderLayout.CENTER
		);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().
			addKeyEventDispatcher(
				new MyKeyEventDispatcher()
			)
		;
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		new javax.swing.Timer(33, this.bugModel).start();
		setVisible(true);
	}
	public static void main(String[] sa) {
		new BugWorld1();
	}
	private JToolBar createToolBar() {
		JToolBar jtb = new JToolBar();
		jtb.setFloatable(false);
		JButton jb = new JButton("Home");
		jtb.add(jb);
		jb.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					bugModel.reset();
					mjp.repaint();
				}
			}
		);
		JCheckBox jcb = new JCheckBox("Bounds");
		jtb.add(jcb);
		jcb.addActionListener(
			new MyActionListener(this.mjp)
		);
		jcb = new JCheckBox("Turbo");
		jtb.add(jcb);
		jcb.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					bugModel.toggleTurbo();
					mjp.repaint();
				}
			}
		);
		return jtb;
	}
	private class MyJPanel extends JPanel implements Observer {
		private static final long serialVersionUID = 1L;
		private BugModel model;
		private BufferedImage bi;
		private BufferedImage bix;
		private boolean bounds = false;
		public MyJPanel(BugModel bugModel) {
			super();
			setLayout(null);
			setBackground(new Color(255, 123, 50));
			this.model = bugModel;
			this.bi = drawBug();
			this.bix = flipBug(bi);
			addKeyListener(new MyKeyListener());
			getModel().addObserver(this);
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1024, 768);
		}
		BugModel getModel() {
			return this.model;
		}
		boolean isBounds() {
			return this.bounds;
		}
		void setBounds(boolean b) {
			this.bounds = b;
		}
		void toggleBounds() {
			this.bounds = !bounds;
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			AffineTransform gat = new AffineTransform();
			gat.translate(
				getWidth() / 2.0,
				-getHeight() / 2.0
			);
			g2d.scale(1.0, -1.0);
			g2d.transform(gat);
			AffineTransform at = new AffineTransform();
			at.translate(-bi.getWidth() / 2.0, bi.getHeight() / 2.0);
			at.translate(
				getModel().getX(),
				getModel().getY()
			);
			at.rotate(
				getModel().getHeading(),
				bi.getWidth() / 2.0,
				-bi.getHeight() / 2.0
			);
			at.scale(1.0, -1.0);
			if (getModel().getFrame() == 0) {
				g2d.drawRenderedImage(bi, at);
			} else {
				g2d.drawRenderedImage(bix, at);
			}
			if (isBounds()) {
				Rectangle2D r2d = new Rectangle2D.Double(
					getModel().getX() - bi.getWidth() / 2.0,
					getModel().getY() - bi.getHeight() / 2.0,
					bi.getWidth(),
					bi.getHeight()
				);
				g2d.setStroke(new BasicStroke(0.0f));
				g2d.setPaint(Color.YELLOW);
				g2d.draw(r2d);
			}
			g2d.dispose();
		}
		private BufferedImage drawBug() {
			final int BI_WIDTH = 96;
			final int BI_HEIGHT = BI_WIDTH;
			BufferedImage bi = new BufferedImage(
				BI_WIDTH,
				BI_HEIGHT,
				BufferedImage.TYPE_4BYTE_ABGR
			);
			Graphics2D g2d = bi.createGraphics();
			AffineTransform gat = new AffineTransform();
			gat.translate(
				bi.getWidth() / 2.0,
				bi.getHeight() / 2.0
			);
			gat.scale(1.0, -1.0);
			g2d.transform(gat);

			AffineTransform at = new AffineTransform();
			at.translate(0, 0);
			at.scale(32.0, 32.0);

			Path2D.Double path = new Path2D.Double();
			path.moveTo(0, -.5675);
			path.curveTo(0, -.5675, .1608, -.4933,.2175, -.4025);
			path.curveTo(.3508, -.4366, .4963, -.6813, .5030, -.7125);
			path.curveTo(.4793, -.7839, .5843, -.8032, .6330, -.7714);
			path.curveTo(.6924, -.7328, .6918, -.6202, .6229, -.6177);
			path.curveTo(.4985, -.6132, .4745, -.3735, .4745, -.3735);
			path.curveTo(.4745, -.3735, .6025, .3075, .6025, .3075);
			path.curveTo(.6025, .3075, .7469, .4468, .7469, .4468);
			path.curveTo(.7553, .4514, .7861, .4343, .7991, .4625);
			path.curveTo(.8089, .4916, .7633, .5675, .7125, .5591);
			path.curveTo(.6691, .5566, .6720, .5204, .6720, .5204);
			path.curveTo(.6720, .5204, .4533, .2433, .4091, .3541);
			path.curveTo(.3658, .4689, .6158, .5700, .6158, .5700);
			path.curveTo(.6255, .5545, .6631, .5564, .6797, .5754);
			path.curveTo(.7199, .6214, .6861, .7092, .5983, .6925);
			path.curveTo(.5611, .6829, .5613, .6567, .5613, .6567);
			path.curveTo(.5613, .6567, .3528, .5323, .3362, .5523);
			path.curveTo(.3349, .6598, .2733, .7208, .2054, .7578);
			path.curveTo(.2021, .7716, .1819, .8165, .1273, .8464);
			path.curveTo(.1492, .8652, .2736, .7409, .3154, .8060);
			path.curveTo(.3224, .8168, .3170, .8315, .3043, .8363);
			path.curveTo(.2830, .8443, .2676, .8299, .2676, .8299);
			path.curveTo(.2676, .8299, .2046, .8754, .1495, .8825);
			path.curveTo(.1061, .8882, .0842, .8733, .0876, .8629);
			path.curveTo(.0604, .8712, .0276, .8759, -.0123, .8743);
			path.curveTo(-.0443, .8729, -.0709, .8685, -.0935, .8622);
			path.curveTo(-.0830, .8720, -.0983, .9055, -.1503, .8987);
			path.curveTo(-.2053, .8915, -.2771, .8413, -.2771, .8413);
			path.curveTo(-.2771, .8413, -.2891, .8604, -.3106, .8532);
			path.curveTo(-.3349, .8466, -.3292, .8284, -.3226, .8173);
			path.curveTo(-.2949, .7707, -.1678, .8627, -.1298, .8487);
			path.curveTo(-.1824, .8234, -.2034, .7845, -.2165, .7528);
			path.curveTo(-.3124, .6910, -.3229, .5894, -.3333, .5414);
			path.curveTo(-.3661, .5408, -.5266, .6425, -.5266, .6425);
			path.curveTo(-.5266, .6425, -.5347, .6987, -.5477, .7019);
			path.curveTo(-.6358, .7233, -.6680, .6133, -.6468, .5846);
			path.curveTo(-.6318, .5643, -.5953, .5764, -.5916, .5750);
			path.curveTo(-.5143, .5752, -.4524, .4859, -.4410, .4619);
			path.curveTo(-.4410, .4619, -.5349, .3927, -.5349, .3927);
			path.curveTo(-.5583, .3892, -.7205, .4868, -.7205, .4868);
			path.curveTo(-.7205, .4868, -.7116, .5058, -.7358, .5205);
			path.curveTo(-.8502, .5789, -.8640, .4468, -.8380, .4231);
			path.curveTo(-.8193, .4062, -.2794, .4167, -.2794, .4167);
			path.curveTo(-.2794, .4167, -.6126, .3402, -.6168, .3070);
			path.curveTo(-.6168, .3070, -.4844, -.3138, -.4950, -.3714);
			path.curveTo(-.4950, -.3714, -.5934, -.6375, -.6332, -.6549);
			path.curveTo(-.6492, -.6528, -.6791, -.6766, -.6742, -.6973);
			path.curveTo(-.6742, -.7676, -.6074, -.8024, -.5524, -.7849);
			path.curveTo(-.5327, -.7768, -.5203, -.7490, -.5206, -.7363);
			path.curveTo(-.5237, -.5701, -.3058, -.3641, -.2497, -.3668);
			path.curveTo(-.1838, -.4670, 0, -.5666, 0, -.5666);
			path.closePath();

			
			Shape s = at.createTransformedShape(path);
			g2d.setPaint(new Color(0, 0, 0));
			g2d.fill(s);
			g2d.draw(s);

			path = new Path2D.Double();
			path.moveTo(-.2617, -.5833);
			path.curveTo(-.5667, -.4733, -.7209, -.2294, -.7154, -.0112);
			path.curveTo(-.7100, .2069, -.6130, .3803, -.3661, .5457);
			path.curveTo(-.2974, .5917, -.0221, .4922, 0, .3783);
			path.curveTo(.0155, .2995, -.1028, -.4070, -.2617, -.5833);
			path.closePath();

			Shape s2 = at.createTransformedShape(path);
			g2d.setPaint(new Color(89, 74, 66));
			g2d.fill(s2);
			g2d.setStroke(new BasicStroke(2f));
			g2d.setPaint(new Color(237, 28, 36));
			g2d.draw(s2);

			path = new Path2D.Double();
			path.moveTo(.2336, -.5789);
			path.curveTo(.3162, -.5789, .6926, -.3365, .6999, -.0399);
			path.curveTo(.7148, .3073, .4410, .5365, .3649, .5416);
			path.curveTo(.2588, .5585, .0175, .5015, 0, .4044);
			path.curveTo(-.0330, .2076, .1574, -.4549, .2336, -.5789);
			path.closePath();

			Shape s3 = at.createTransformedShape(path);
			g2d.setPaint(new Color(89, 74, 66));
			g2d.fill(s3);
			g2d.setStroke(new BasicStroke(2f));
			g2d.setPaint(new Color(237, 28, 36));
			g2d.draw(s3);

			// eyes
			path = new Path2D.Double();
			path.moveTo(-.0875, .6693);
			path.curveTo(-.1427, .6716, -.1843, .5727, -.2169, .5849);
			path.curveTo(-.2394, .5933, -.2366, .6496, -.2085, .6725);
			path.curveTo(-.1804, .6947, -.1184, .7059, -.0875, .6693);
			path.closePath();

			path.moveTo(.0875, .6693);
			path.curveTo(.1427, .6716, .1843, .5727, .2169, .5849);
			path.curveTo(.2394, .5933, .2366, .6496, .2085, .6725);
			path.curveTo(.1804, .6947, .1184, .7059, .0875, .6693);
			path.closePath();

			Shape s7 = at.createTransformedShape(path);
			g2d.setPaint(new Color(89, 74, 66));
			g2d.fill(s7);
			g2d.draw(s7);

			// start dots
			path = new Path2D.Double();
			path.moveTo(-.7183, .0250);
			path.curveTo(-.6898, 0, -.6583, -.0249, -.6122, -.0221);
			path.curveTo(-.5382, -.0221, -.4782, .0379, -.4782, .1119);
			path.curveTo(-.4782, .1859, -.5382, .2459, -.6122, .2459);
			path.curveTo(-.6278, .2459, -.6428, .2431, -.6567, .2381);
			path.curveTo(-.6896, .1713, -.7076, .1004, -.7183, .0250);

			path.moveTo(.7183, .0250);
			path.curveTo(.6898, 0, .6583, -.0249, .6122, -.0221);
			path.curveTo(.5382, -.0221, .4782, .0379, .4782, .1119);
			path.curveTo(.4782, .1859, .5382, .2459, .6122, .2459);
			path.curveTo(.6278, .2459, .6428, .2431, .6567, .2381);
			path.curveTo(.6896, .1713, .7076, .1004, .7183, .0250);

			Shape s4 = at.createTransformedShape(path);
			g2d.setPaint(new Color(237, 28, 36));
			g2d.fill(s4);
			g2d.draw(s4);

			Ellipse2D.Double dot = new Ellipse2D.Double(-.5117, -.3, .25, .25);
			Shape s5 = at.createTransformedShape(dot);
			g2d.setPaint(new Color(237, 28, 36));
			g2d.fill(s5);
			g2d.draw(s5);
			dot = new Ellipse2D.Double(.25, -.3, .25, .25);
			Shape s5a = at.createTransformedShape(dot);
			g2d.setPaint(new Color(237, 28, 36));
			g2d.fill(s5a);
			g2d.draw(s5a);	

			dot = new Ellipse2D.Double(-.3792, .2267, .1467, .1467);
			Shape s6 = at.createTransformedShape(dot);
			g2d.setPaint(new Color(237, 28, 36));
			g2d.fill(s6);
			g2d.draw(s6);
			dot = new Ellipse2D.Double(.2325, .2267, .1467, .1467);
			Shape s6a = at.createTransformedShape(dot);
			g2d.setPaint(new Color(237, 28, 36));
			g2d.fill(s6a);
			g2d.draw(s6a);

			g2d.dispose();
			return bi;
		}
		private BufferedImage flipBug(BufferedImage bi) {
			BufferedImage bix;
			bix = new BufferedImage(
				bi.getWidth(),
				bi.getHeight(),
				BufferedImage.TYPE_4BYTE_ABGR
			);
			Graphics2D g2d = bix.createGraphics();
			AffineTransform at = new AffineTransform();
			at.translate(bi.getWidth(), 0.0);
			at.scale(-1.0, 1.0);
			g2d.drawRenderedImage(bi, at);
			g2d.dispose();
			return bix;
		}
		private class MyKeyListener extends KeyAdapter {
			final double SPEED_BUMP = getModel().getSpeedBump();
			final double HEADING_BUMP = Math.toRadians(getModel().getHeadingBump());
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
					case KeyEvent.VK_W:
					case KeyEvent.VK_UP:
						getModel().setSpeed(
							SPEED_BUMP +
							getModel().getSpeed()
						);
						break;
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						getModel().setHeading(
							HEADING_BUMP +
							getModel().getHeading()
						);
						break;
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						getModel().setSpeed(
							-SPEED_BUMP +
							getModel().getSpeed()
						);
						break;
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						getModel().setHeading(
							-HEADING_BUMP +
							getModel().getHeading()
						);
						break;
					default:
						break;
				}
			}
		}
		@Override
		public void update(Observable o, Object arg) {
			repaint();
		}
	}
	private class MyActionListener implements ActionListener {
		private MyJPanel mjp;
		public MyActionListener(MyJPanel mjp) {
			this.mjp = mjp;
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			mjp.toggleBounds();
			mjp.repaint();
		}
	}
	private class MyKeyEventDispatcher implements KeyEventDispatcher {
		@Override
		public boolean dispatchKeyEvent(KeyEvent ke) {
			if (ke.getID() == KeyEvent.KEY_PRESSED) {
				int key = ke.getKeyCode();
				if (
					key == KeyEvent.VK_W ||
					key == KeyEvent.VK_A ||
					key == KeyEvent.VK_S ||
					key == KeyEvent.VK_D ||
					key == KeyEvent.VK_UP ||
					key == KeyEvent.VK_LEFT ||
					key == KeyEvent.VK_DOWN ||
					key == KeyEvent.VK_RIGHT
				) {
					KeyboardFocusManager.
						getCurrentKeyboardFocusManager().
						redispatchEvent(mjp, ke)
					;
					return true;
				}
			}
			return false;
		}
	}
	private final class BugModel extends Observable implements ActionListener {
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
		public BugModel() {
			this("Bug", false, 0.0, 0.0, 0.0, 0.0, 1.0, 10.0, 4.0);
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
		int getFrame() {
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

		String getMoniker() {
			return this.moniker;
		}
		boolean isTurbo() {
			return this.turbo;
		}
		private void setTurbo(boolean b) {
			this.turbo = b;
		}
		private void toggleTurbo() {
			this.turbo = !isTurbo();
			setChanged();
			notifyObservers();
		}
		double getX() {
			return this.x;
		}
		private void reset() {
			this.x = 0.0;
			this.y = 0.0;
			this.speed = 0.0;
			this.heading = 0.0;
			this.frame = 0;
			this.frameScale = 0;
		}
		private void setX(double x) {
			this.x = x;
			setChanged();
			notifyObservers();
		}
		double getY() {
			return this.y;
		}
		private void setY(double y) {
			this.y = y;
			setChanged();
			notifyObservers();
		}
		double getSpeed() {
			return this.speed;
		}
		private void setSpeed(double s) {
			this.speed = s < 0.0 ? 0.0 : s > getMaxSpeed() ? getMaxSpeed() : s;
			setChanged();
			notifyObservers();
		}
		double getHeading() {
			return this.heading;
		}
		private void setHeading(double h) {
			this.heading = h;
			setChanged();
			notifyObservers();
		}
		double getSpeedBump() {
			return this.speedBump;
		}
		double getHeadingBump() {
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
		}
		private boolean isCollision(double newX, double newY) {
			boolean b = false;
			double w = mjp.getWidth() / 2.0;
			double h = mjp.getHeight() / 2.0;
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
		@Override
		public void actionPerformed(ActionEvent ae) {
			computeXY();
			if (getSpeed() != 0.0) {
				setFrameScale(getFrameScale() + 1);
				if (getFrameScale() > 3) {
					toggleFrame();
					setFrameScale(0);
				}
			}
		}
	}
}
