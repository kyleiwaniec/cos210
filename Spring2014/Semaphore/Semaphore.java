import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
public class Semaphore extends JFrame {
	private static final long serialVersionUID = 1L;
	public Semaphore(String str) {
		super("Semaphore");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new MyJPanel(str));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] sa) {
		new Semaphore(sa[0]);
	}
	private class MyJPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private int index = 0;
		private int count = 0;
		private BufferedImage bi;
		private String input;
		public MyJPanel(String input) {
			this.input = input;
			setLayout(null);
			setBackground(new Color(0, 128, 255));
			this.bi = loadImage();
			new javax.swing.Timer(500, this).start();
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(921, 771);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int[] colRow = new int[2];
			if(count < this.input.length()){
				this.index = this.input.charAt(count) < 97 ? this.input.charAt(count) - 65 : this.input.charAt(count) - 97;
				count++;
			}else{
				this.index = 26;
				count = 0;
			}
			colRow = createColRow(this.index);
			//this.index = this.index + 1 == 27 ? 0 : this.index + 1;

			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(
				bi,
				0,
				0,
				920,
				770,
				colRow[0] * 921,
				colRow[1] * 771 + 16,
				colRow[0] * 921 + 920,
				colRow[1] * 771 + 786,
				null
			);
			g2d.dispose();
		}
		private int[] createColRow(int n) {
			int[] ia = new int[2];
			ia[0] = n % 6;
			ia[1] = n / 6;
			return ia;
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			repaint();
		}
	}
	private BufferedImage loadImage() {
		BufferedImage bi = null;
		try {
			 bi = ImageIO.read(
				new FileInputStream("semaphore.gif")
			);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bi;
	}
}
