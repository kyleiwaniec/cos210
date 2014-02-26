package edu.mccc.cos210.hwk1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;
import com.cbthinkx.util.Debug;
/**
 * Homework 1:. A "Hello World" Program on Steroids.
 *
 * @author Junior Watson
 *
 * @version 1.0
 */
public class HomeWork1 implements ActionListener {
	private static HomeWork1 runningInstance;
	/**
	 * The greeting. Blah, blah, blah.
	 */
	private String hello;
	/**
	 *
	 * Constructor. Create the original object
	 */
	public HomeWork1(String s) {
		Debug.println("HomeWork1():" + s);
		this.hello = s;
		JFrame jf = new JFrame(s);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton jb = new MyJButton(
			s,
			new MyIcon()
		);
		jb.setVerticalTextPosition(SwingConstants.TOP);
		jb.setHorizontalTextPosition(SwingConstants.CENTER);
		jb.setFocusPainted(false);
		jb.addActionListener(this);
		jf.add(
			jb,
			BorderLayout.CENTER
		);
		jf.setSize(800, 600);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.getRootPane().setDefaultButton(jb);
		jf.setVisible(true);
		jb.requestFocus();
	}
	/**
	 * In the begining there was main. the method main is where it all
	 * begins. Blah, blah, blah.
	 *
	 * @param sa The command line arguments. Optionally pass in the greeting.
	 */
	public static void main(final String[] sa) {
		Debug.println("main() started");
		try {
			EventQueue.invokeAndWait(
				new Runnable() {
					public void run() {
						Debug.println("run()");
						String message = "Me so pretty!";
						if (sa.length > 0) {
							StringBuilder sb = new StringBuilder();
							for (String s : sa) {
								sb.append(s);
								sb.append(" ");
							}
							message = sb.toString().trim();
						}
						runningInstance = new HomeWork1(message);
					}
				}
			);
			runningInstance.doIt(runningInstance.getHello());
		} catch (InterruptedException ex) {
			System.err.println(ex.getMessage());
		} catch (InvocationTargetException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Debug.println("main() ended");
		}
	}
	protected void doIt(String s) {
		Debug.println("doIt():" + s);
		System.out.println(s);
	}
	/**
	 * Inspector in charge of returning the greeting. Blah, blah, blah.
	 * @return The greeting.
	 * @throws Exception The greeting equals "boom".
	 */
	public String getHello() throws Exception {
		Debug.println("getHello():" + this.hello);
		if (this.hello.equalsIgnoreCase("Boom")) {
			throw new Exception("Boom!");
		}
		return this.hello;
	}
	void setHello(String s) {
		Debug.println("setHello():" + s);
		this.hello = s;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Debug.println("actionPerformed():" + e);
		try {
			System.out.println(getHello() + " (again)");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	private class MyJButton extends JButton {
		private static final long serialVersionUID = 1L;
		public MyJButton(String s, Icon i) {
			super(s, i);
			Debug.println("MyJButton():" + s + " " + i);
		}
		@Override
		public Icon getPressedIcon() {
			Debug.println("getPressedIcon()");
			((MyIcon) super.getIcon()).setPressed(true);
			return super.getIcon();
		}
		@Override
		public Icon getIcon() {
			Debug.println("getIcon()");
			((MyIcon) super.getIcon()).setPressed(false);
			return super.getIcon();
		}
	}
	private class MyIcon implements Icon {
		private final static double PI = Math.PI;
		private final static int ICON_HEIGHT = 200;
		private final static int ICON_WIDTH = ICON_HEIGHT;
		private final float EYE_WIDTH = ICON_WIDTH / 16.0f;
		private final float EYE_HEIGHT = EYE_WIDTH;
		private final float MOUTH_WIDTH = ICON_WIDTH / 8.0f;
		private final float MOUTH_HEIGHT = MOUTH_WIDTH;
		private BufferedImage upBuffer = new BufferedImage(
			ICON_WIDTH,
			ICON_HEIGHT,
			BufferedImage.TYPE_4BYTE_ABGR
		);
		private BufferedImage downBuffer = new BufferedImage(
			ICON_WIDTH,
			ICON_HEIGHT,
			BufferedImage.TYPE_4BYTE_ABGR
		);
		private boolean pressed = false;
		public MyIcon() {
			Debug.println("MyIcon():");
			initIcon();
		}
		public void setPressed(boolean b) {
			Debug.println("setPressed():" + b);
			this.pressed = b;
		}
		public boolean isPressed() {
			Debug.println("isPressed():" + this.pressed);
			return this.pressed;
		}
		@Override
		public int getIconHeight() {
			Debug.println("getIconHeight():" + ICON_HEIGHT);
			return ICON_HEIGHT;
		}
		@Override
		public int getIconWidth() {
			Debug.println("getIconWidth():" + ICON_WIDTH);
			return ICON_WIDTH;
		}
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Debug.println("paintIcon():" + c + " " + g + " " + x + " " + y);
			AffineTransform at = new AffineTransform();
			at.setToTranslation(x, y);
			Graphics2D g2d = (Graphics2D) g.create();
			if (!isPressed()) {
				g2d.drawRenderedImage(upBuffer, at);
				
			} else {
				g2d.drawRenderedImage(downBuffer, at);
			}
			g2d.dispose();
		}
		void initIcon() {
			Debug.println("initIcon()");
			Graphics2D g2dUp = upBuffer.createGraphics();
			Graphics2D g2dDown = downBuffer.createGraphics();
			drawIconBase(g2dUp);
			drawIconBase(g2dDown);
			
			Path2D p = new Path2D.Double();
			//right eye:
			p.moveTo(112, 85.5);
			p.curveTo(115, 91, 138.5, 92, 140, 84);
			p.moveTo(117, 88.5);
			p.lineTo(115.25, 92.25);
			p.moveTo(122.5, 90);
			p.lineTo(122.75, 94.25);
			p.moveTo(128, 90.25);
			p.lineTo(128.5, 95);
			p.moveTo(132.5, 89.75);
			p.lineTo(134.25, 94.5);
			p.moveTo(136.75, 88.25);
			p.lineTo(139.25, 92);
			p.moveTo(139.75, 86);
			p.lineTo(142.75, 87.75);
			// left eye:
			p.moveTo(63.204, 82.771);
			p.curveTo(68, 88, 82.5, 91, 88.627, 87);
			p.moveTo(65.25, 84.75);
			p.lineTo(62.5, 88.25);
			p.moveTo(69, 87);
			p.lineTo(67.5, 90.75);
			p.moveTo(76.25, 88.75);
			p.lineTo(75.25, 93.5);
			p.moveTo(81.166, 88.833);
			p.lineTo(82.25, 94);
			p.moveTo(86.499, 88.333);
			p.lineTo(88.5, 93);
			//mouth
			p.moveTo(66.955, 145.271);
			p.curveTo(81.5, 151.5, 129, 145.5, 145, 152);
			p.curveTo(139, 186.5, 64.5, 158.5, 66.955, 145.271);
			
			
			g2dUp.setPaint(Color.BLACK);
			g2dUp.draw(p);
				
			p = new Path2D.Double();
			// mouth:
			p.moveTo(71.667, 151);
			p.curveTo(83, 147.5, 131, 155, 136, 160);
			
			
			// right eye:
			p.moveTo(112.145, 85.808);
			p.curveTo(115, 80, 138.5, 79.5, 140, 87.5);
			p.moveTo(115.25, 79.083);
			p.lineTo(117, 82.833);
			p.moveTo(122.75, 77.083);
			p.lineTo(122.5, 81.333);
			p.moveTo(128.5, 76.333);
			p.lineTo(128, 81.083);
			p.moveTo(134.25, 76.833);
			p.lineTo(132.5, 81.583);
			p.moveTo(139.25, 79.333);
			p.lineTo(136.75, 83.083);
			p.moveTo(142.75, 83.583);
			p.lineTo(139.75, 85.333);
			
			// left eye:
			p.moveTo(88.627, 83.658);
			p.curveTo(82.5, 79.5, 68, 82.5, 63.204, 87.896);
			p.curveTo(63.204, 87.896, 62.5, 95.3, 75.333, 96.667);
			p.moveTo(62.5, 82.417);
			p.lineTo(65.25, 85.917);
			p.moveTo(67.5, 79.917);
			p.lineTo(69, 83.667);
			p.moveTo(75.25, 77.167);
			p.lineTo(76.25, 81.917);
			p.moveTo(82.25, 76.667);
			p.lineTo(81.166, 81.8);
			p.moveTo(88.5, 77.667);
			p.lineTo(86.499, 82.333);
			g2dDown.setPaint(Color.BLACK);
			g2dDown.draw(p);
			
			p = new Path2D.Double();
			g2dDown.setPaint(Color.BLACK);
			//left pupil
			p.moveTo(68, 84.667);
			p.curveTo(68, 84.667, 78.8, 79.8, 83.167, 82);
			p.curveTo(86.83, 86.83, 82.8, 92.3, 75.833, 92.833);
			p.curveTo(68.8, 93.3, 66.3, 86.8, 68, 84.667);
			// right pupil
			p.moveTo(118.507, 82.33);
			p.curveTo(119, 81.5, 135, 80.5, 135.505, 83.249);
			p.curveTo(136.5, 90.5, 131, 93.7, 125.226, 93.355);
			p.curveTo(116.8, 92.7, 117.6, 83.4, 118.507, 82.33);
			
			g2dDown.fill(p);
			g2dDown.draw(p);
			
			g2dDown.setPaint(Color.WHITE);
			Ellipse2D.Double s = new Ellipse2D.Double(69.833, 85.458, 3, 3);
			g2dDown.fill(s);
			g2dDown.draw(s);
			s.setFrame(129.375, 83.125, 3, 3);
			g2dDown.fill(s);
			g2dDown.draw(s);
			s.setFrame(127.888, 87.188, 1.3, 1.3);
			g2dDown.fill(s);
			g2dDown.draw(s);
			
			g2dUp.dispose();
			g2dDown.dispose();
		}
		void drawIconBase(Graphics2D g2d) {
			Debug.println("initIcon():" + g2d);
			
			Path2D p = new Path2D.Double();
			
			// face outline
			p.moveTo(96.678, 20.271);
			p.curveTo(152, 19, 160, 64, 154, 78.5);
			p.curveTo(148, 94, 172, 115, 162.5, 149.5);
			p.curveTo(159, 200, 93, 194, 64.5, 175.5);
			p.curveTo(11, 141, 11, 23, 96.678, 20.271);
			
			g2d.setPaint(new Color(255, 255, 255));
			g2d.fill(p);
			g2d.setPaint(Color.BLACK);
			g2d.draw(p);
			
			//nose
			p = new Path2D.Double();
			
			p.moveTo(141.25, 74.5);
			p.curveTo(124.5, 68, 110.5, 72, 105.5, 79.75);
			p.curveTo(101, 85, 103, 93, 103.882, 95.483);
			p.curveTo(105, 98, 116, 118, 119.136, 120.907);
			p.curveTo(122, 123, 124, 130.5, 118.076, 132.348);
			p.curveTo(112, 134, 109.3, 126.8, 102.398, 128.11);
			p.curveTo(95.4, 129.4, 100.7, 133, 96.89, 132.771);
			p.curveTo(93, 132.5, 89, 123.5, 94.771, 122.178);
			
			g2d.setPaint(Color.BLACK);
			g2d.draw(p);
			
			// hair
			p = new Path2D.Double();
			p.moveTo(12.628, 189.163);
			p.curveTo(27.5, 182, 44, 161.5, 26.5, 142.33);
			p.curveTo(22, 136, -22, 83.5, 22.788, 63.17);
			p.curveTo(50, 51, 29, 12, 85, 3.5);
			p.curveTo(145.5, -6, 186.5, 29.5, 176, 86);
			p.curveTo(169, 125.5, 162, 175, 189, 184);
			p.curveTo(131, 189, 153, 145, 158, 100);
			p.curveTo(162, 62, 127, 37, 89, 44);
			p.curveTo(92, 64, 84, 68, 71, 75);
			p.curveTo(52, 85, 42, 98, 55.5, 123);
			p.curveTo(69, 148, 41, 190, 12.628, 189.163);
			p.closePath();
			
			AffineTransform at = new AffineTransform();
			at.setToScale(1,1);
			at.rotate(Math.toRadians(0), 100, 100);
			System.out.println(5 * PI/180);
			p.transform(at);
			
			g2d.setPaint(new Color(238, 42, 123));
			g2d.fill(p);
			g2d.draw(p);
			
		}
	}
}
