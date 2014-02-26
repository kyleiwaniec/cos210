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
			Graphics2D g2d = (Graphics2D)g.create();
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
			Path2D.Double p2dd = new Path2D.Double();
			p2dd.moveTo(-0.375,  1.000);
			p2dd.lineTo(-0.375,  0.000);
			p2dd.lineTo(-1.000,  0.000);
			p2dd.lineTo(-1.000, -1.000);
			p2dd.lineTo( 1.000, -1.000);
			p2dd.lineTo( 1.000,  0.000);
			p2dd.lineTo( 0.000,  0.000);
			p2dd.lineTo( 0.000,  1.000);
			p2dd.closePath();
			AffineTransform at = new AffineTransform();
			at.translate(0.0, 0.0);
			at.scale(32.0, 32.0);
			Shape s = at.createTransformedShape(p2dd);
			g2d.setPaint(new Color(255, 0, 123));
			g2d.fill(s);
			g2d.setStroke(new BasicStroke(4.5f));
			g2d.setPaint(Color.BLACK);
			g2d.draw(s);
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
