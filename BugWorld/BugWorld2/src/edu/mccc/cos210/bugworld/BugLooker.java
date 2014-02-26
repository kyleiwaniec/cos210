package edu.mccc.cos210.bugworld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BugLooker extends JFrame {
	private static final long serialVersionUID = 1L;
	public BugLooker() {
		super("BugLooker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(
			new BugLookerView(),
			BorderLayout.CENTER
		);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] sa) {
		new BugLooker();
	}
	private class BugLookerView extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage bi[][] = new BufferedImage[BugGame.bugInfo.length][2];
		private int flipper = 0;
		public BugLookerView() {
			setLayout(null);
			setBackground(new Color(255, 123, 50));
			int index = 0;
			for (String[] sa : BugGame.bugInfo) {
				if ("Tom".equals(sa[0]) || "Bob".equals(sa[0])) {
					continue;
				}
				try {
					BugView bv = (BugView) Class.forName(
						"edu.mccc.cos210.bugworld.bug." +
						sa[0]
					).newInstance();
					this.bi[index][0] = bv.drawBug();
					this.bi[index][1] = BugGameView.flipBug(this.bi[index][0]);
					index++;
					System.out.println(sa[0] + " loaded");
				} catch (Exception ex) {
				}
			}
			new javax.swing.Timer(
				250,
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						flipper = (flipper + 1) % 2;
						repaint();
					}
				}
			).start();
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(6 * 96, 6 * 96);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			done:
			for (int row = 0; row < 6; row++) {
				for (int col = 0; col < 6; col++) {
					if (bi[row * 6 + col][0] == null) {
						break done;
					}
					AffineTransform at = AffineTransform.getTranslateInstance(col * 96.0, row * 96.0);
					g2d.drawRenderedImage(bi[row * 6 + col][flipper], at);
				}
			}
			g2d.dispose();
		}
	}
}
