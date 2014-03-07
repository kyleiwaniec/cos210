package edu.mccc.cos210.bugworld;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class BugGameView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private BugGameModel bugGameModel;
	private BufferedImage bi1;
	private BufferedImage bi1Flipped;
	private BufferedImage bi2;
	private BufferedImage bi2Flipped;
	private boolean bounds = false;
	public BugGameView(BugGameModel bugGameModel) {
		super();
		setLayout(null);
		setBackground(new Color(255, 123, 50));
		this.bugGameModel = bugGameModel;
		this.bi1 = getBugGameModel().getBug1().getBugView().drawBug();
		this.bi1Flipped = flipBug(this.bi1);
		this.bi2 = getBugGameModel().getBug2().getBugView().drawBug();
		this.bi2Flipped = flipBug(this.bi2);
		addKeyListener(new BugGameViewKeyListener());
		getBugGameModel().addObserver(this);
		getBugGameModel().getBug1().getBugModel().addObserver(this);
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1026, 768);
	}
	BugGameModel getBugGameModel() {
		return this.bugGameModel;
	}
	boolean isBounds() {
		return this.bounds;
	}
	void setBounds(boolean b) {
		this.bounds = b;
	}
	public void toggleBounds() {
		this.bounds = !bounds;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BugModel bug1Model = getBugGameModel().getBug1().getBugModel();
		BugModel bug2Model = getBugGameModel().getBug2().getBugModel();
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform gat = new AffineTransform();
		gat.translate(
			getWidth() / 2.0,
			-getHeight() / 2.0
		);
		g2d.scale(1.0, -1.0);
		g2d.transform(gat);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(5.0f));
		for (Line2D l2d : getBugGameModel().getWalls()) {
			g2d.draw(l2d);
		}
		AffineTransform at = new AffineTransform();
		at.translate(-bi1.getWidth() / 2.0, bi1.getHeight() / 2.0);
		at.translate(
			bug1Model.getX(),
			bug1Model.getY()
		);
		at.rotate(
			bug1Model.getHeading(),
			bi1.getWidth() / 2.0,
			-bi1.getHeight() / 2.0
		);
		at.scale(1.0, -1.0);
		if (bug1Model.getFrame() == 0) {
			g2d.drawRenderedImage(bi1, at);
		} else {
			g2d.drawRenderedImage(bi1Flipped, at);
		}
		at = new AffineTransform();
		at.translate(-bi2.getWidth() / 2.0, bi2.getHeight() / 2.0);
		at.translate(
			bug2Model.getX(),
			bug2Model.getY()
		);
		at.rotate(
			bug2Model.getHeading(),
			bi2.getWidth() / 2.0,
			-bi2.getHeight() / 2.0
		);
		at.scale(1.0, -1.0);
		if (bug2Model.getFrame() == 0) {
			g2d.drawRenderedImage(bi2, at);
		} else {
			g2d.drawRenderedImage(bi2Flipped, at);
		}
		g2d.dispose();
	}
	static BufferedImage flipBug(BufferedImage bi) {
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
	private class BugGameViewKeyListener extends KeyAdapter {
		BugModel bug1Model = getBugGameModel().getBug1().getBugModel();
		final double SPEED_BUMP = bug1Model.getSpeedBump();
		final double HEADING_BUMP = Math.toRadians(bug1Model.getHeadingBump());
		@Override
		public void keyPressed(KeyEvent ke) {
			switch (ke.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					bug1Model.setSpeed(
						SPEED_BUMP +
						bug1Model.getSpeed()
					);
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					bug1Model.setHeading(
						HEADING_BUMP +
						bug1Model.getHeading()
					);
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					bug1Model.setSpeed(
						-SPEED_BUMP +
						bug1Model.getSpeed()
					);
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					bug1Model.setHeading(
						-HEADING_BUMP +
						bug1Model.getHeading()
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
