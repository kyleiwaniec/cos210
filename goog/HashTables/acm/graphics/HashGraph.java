package hashgraph;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GPoint;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;

/*
 * File   : HashGraph.java
 * Author : DM
 * License: BSD 3-clause license http://gotfu.wordpress.com/bsd-3-clause-license/
 * Copyright (c) 2012 Got-fu? http://gotfu.wordpress.com/
 *
 * A graph representation of how often a hash function hits each bucket.
 * Uses GCanvas from the ACM Library to make life easier.
 * The wordlist used comes from CS50 pset6
 */
public class HashGraph
				implements ActionListener, ComponentListener {

	/* Class variables */
	private static enum hashFunction { // the hash functions.

		DEFAULT, DJB2, SDBM, ROTATIVE, GRAY, SHUFFLE, SUM
	};

	private static enum compFunction { // the compression functions.

		COMP1, COMP2
	};
	private static final int MARKSIZE = 10;
	private static final int MARKMARGIN = 50;
	private static final int MAXGRAPHHEIGHT = 950;
	private static final int MAXGRAPHWIDTH = 1250;
	private static final int BUTTONAREAWIDTH = 160;
	private static final String pathToWordsFile = "resources/words.txt";
	private static final int APPLICATION_WIDTH = 1100;
	private static final int APPLICATION_HEIGHT = 720;

	/* Instance variables */
	private int CONTENT_WIDTH = 900;
	private int CONTENT_HEIGHT = 700;
	private int _DEFAULT_BUCKETS = 1009;
	private int _DEFAULT_aConst = 127;
	private int _DEFAULT_bConst = 65993;
	private int _DEFAULT_pConst = 1999993;
	private int BUCKETS = _DEFAULT_BUCKETS;
	private int aConst = _DEFAULT_aConst;
	private int bConst = _DEFAULT_bConst;
	private int pConst = _DEFAULT_pConst;
	private JFrame topFrame;
	private JPanel buttonArea;
	private GCanvas outerCanvas;
	private GCanvas canvas;
	private GCanvas graph;
	private JLabel bucketsTFLabel;
	private JLabel aConstTFLabel;
	private JLabel bConstTFLabel;
	private JLabel pConstTFLabel;
	private JTextField bucketsField;
	private JTextField aConstField;
	private JTextField bConstField;
	private JTextField pConstField;
	private JButton setBucketsButton;
	private JButton setaConstButton;
	private JButton setbConstButton;
	private JButton setpConstButton;
	private JRadioButton javaHashRadio;
	private JRadioButton sdbmHashRadio;
	private JRadioButton djb2HashRadio;
	private JRadioButton grayHashRadio;
	private JRadioButton rotativeHashRadio;
	private JRadioButton shuffleHashRadio;
	private JRadioButton sumHashRadio;
	private ButtonGroup hashRadioButtons;
	private ButtonGroup compRadioButtons;
	private JRadioButton comp1Radio;
	private JRadioButton comp2Radio;
	private JButton drawButton;
	private JButton clearButton;
	private JButton resetButton;
	private JButton aboutButton;
	private boolean standAlone = false;

	/* used to launch urls with... when under an applet env */
	private AppletContext appletContext = null;
	/* stuff in canvas */
	private ArrayList<GLine> axisLines = null;
	private ArrayList<GLabel> axisLabels = null;

	/* track which functions are 'currently' chosen. */
	private hashFunction currentHashFunction;
	private compFunction currentCompFunction;
	private int currentColor = 0;

	/* loop through these colors each time a new graph is drawn */
	private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.cyan, Color.MAGENTA, Color.BLACK, Color.DARK_GRAY};

	public static void main(String[] args) {
		new HashGraph().init(true);
	}

	/**
	 * initializes the exact coordinates the panels should be at.
	 * also used during window resizing.
	 */
	private void reInit() {
		CONTENT_HEIGHT = topFrame.getContentPane().getBounds().height;
		CONTENT_WIDTH = topFrame.getContentPane().getBounds().width;

		outerCanvas.setSize(CONTENT_WIDTH, CONTENT_HEIGHT);
		outerCanvas.removeAll();

		buttonArea.setSize(BUTTONAREAWIDTH, CONTENT_HEIGHT);
		buttonArea.setPreferredSize(new Dimension(BUTTONAREAWIDTH, CONTENT_HEIGHT));

		buttonArea.setLocation(CONTENT_WIDTH - BUTTONAREAWIDTH, 0);
		buttonArea.setVisible(true);
		buttonArea.setFocusable(true);

		int cWidth = CONTENT_WIDTH - BUTTONAREAWIDTH - 1; // off by one pixel
		int cHeight = CONTENT_HEIGHT;
		canvas.setSize(cWidth, cHeight);

		/*
		 * frame/panel/canvas diagram:
		 *
		 *    topFrame
		 *        |
		 *    contentPane ... ( topFrame.getContentPane() )
		 *        |
		 *    outerCanvas
		 *     /     \
		 *  canvas   buttonArea
		 *    |
		 *  graph
		 *
		 *
		 * order of 'add'ing the panels/canvases matters.
		 */

		outerCanvas.add(buttonArea);
		outerCanvas.add(canvas);

		/*
		 * axis marks, lines and labels are also added in an arraylist so they can be explicitly removed when needed.
		 */
		if (axisLines != null)
			for (int i = 0; i < axisLines.size(); i++)
				canvas.remove(axisLines.get(i));

		if (axisLabels != null)
			for (int i = 0; i < axisLabels.size(); i++)
				canvas.remove(axisLabels.get(i));

		/* make new (empty) ArrayLists for the axis lines and axis labels. */
		axisLines = new ArrayList<GLine>();
		axisLabels = new ArrayList<GLabel>();

		/* the y axis marks and labels. */
		for (int y = cHeight - MARKMARGIN; y > 0; y -= MARKMARGIN) {
			GLine line = new GLine(MARKMARGIN, y, MARKMARGIN - MARKSIZE, y);
			line.setColor(Color.black);
			axisLines.add(line);
			canvas.add(line);

			GLabel label = new GLabel("" + (cHeight - y - MARKMARGIN) + "");
			label.setColor(Color.black);
			label.setLocation(MARKMARGIN - MARKSIZE - label.getWidth() - MARKSIZE, y + label.getAscent() / 2);
			axisLabels.add(label);
			canvas.add(label);
		}

		/* the x axis marks and labels. */
		for (int x = MARKMARGIN; x < MAXGRAPHWIDTH + MARKMARGIN + MARKMARGIN; x += MARKMARGIN) {
			GLine line = new GLine(x, cHeight - MARKMARGIN, x, cHeight - (MARKMARGIN - MARKSIZE));
			line.setColor(Color.black);
			axisLines.add(line);
			canvas.add(line);

			GLabel label = new GLabel("" + (x - MARKMARGIN) + "");
			label.setColor(Color.black);
			label.setLocation(x - label.getWidth() / 2, cHeight - (MARKMARGIN - MARKSIZE) + label.getHeight());
			axisLabels.add(label);
			canvas.add(label);
		}

		/* figure out where to put the graph area's top-right corner */
		canvas.remove(graph);
		int graphYLocation;
		int graphMaxVisibleHeight = cHeight - MARKMARGIN;
		if (graphMaxVisibleHeight >= MAXGRAPHHEIGHT) {
			graphYLocation = graphMaxVisibleHeight - MAXGRAPHHEIGHT;
		} else {// if (graphMaxVisibleHeight < GRAPHHEIGHT) {
			graphYLocation = 0 - (MAXGRAPHHEIGHT - graphMaxVisibleHeight);
		}
		graph.setLocation(MARKMARGIN + 1, // off by one, to allow the axis line to be drawn
						graphYLocation);
		canvas.add(graph);

		/* the y axis line */
		GLine yAxis = new GLine(MARKMARGIN, graphYLocation, MARKMARGIN, cHeight - MARKMARGIN);
		yAxis.setColor(Color.black);
		axisLines.add(yAxis);
		canvas.add(yAxis);

		/* the x axis line */
		GLine xAxis = new GLine(MARKMARGIN, cHeight - MARKMARGIN, MARKMARGIN + MAXGRAPHWIDTH, cHeight - MARKMARGIN);
		xAxis.setColor(Color.black);
		axisLines.add(xAxis);
		canvas.add(xAxis);

		/* the y axis label */
		GLabel hitsLabel = new GLabel("Hits");
		hitsLabel.setLocation(MARKMARGIN / 2 - hitsLabel.getWidth() / 2, hitsLabel.getHeight() + hitsLabel.getHeight() / 2);
		axisLabels.add(hitsLabel);
		canvas.add(hitsLabel);

		/* the x axis label */
		GLabel bucketsLabel = new GLabel("Buckets");
		bucketsLabel.setLocation(cWidth / 2 - bucketsLabel.getWidth() / 2,
						cHeight - bucketsLabel.getHeight() / 2);
		axisLabels.add(bucketsLabel);
		canvas.add(bucketsLabel);

		topFrame.requestFocus();
	}

	/**
	 * launches the app with low priviledges.
	 */
	public void init() {
		init(false);
	}

	/**
	 * initialize componets... without specifically setting coordinates.
	 * @param boolean sa   false when launched via an applet, true when launched as a standalone app.
	 *
	 */
	public void init(boolean sa) {
		standAlone = sa; // this made developing/debugging easy
		/* http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			//don't care... Falls back to default LAF
		}

		currentHashFunction = hashFunction.DEFAULT;
		currentCompFunction = compFunction.COMP1;

		topFrame = new JFrame("HashGraph");

		if (standAlone)
			topFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		topFrame.setBounds(100, 100, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		topFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximizes the jframe window.
		topFrame.addComponentListener(this);
		topFrame.getContentPane().setFocusable(true);
		topFrame.getContentPane().setVisible(true);
		buttonArea = new JPanel();

		buttonArea.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		/* Choose Hash functions */
		hashRadioButtons = new ButtonGroup();

		JLabel hashLabel = new JLabel("Hash Function:");
		hashLabel.setPreferredSize(new Dimension(145, 23));
		buttonArea.add(hashLabel);

		javaHashRadio = new JRadioButton();
		hashRadioButtons.add(javaHashRadio);
		javaHashRadio.setText("Java");
		javaHashRadio.setPreferredSize(new Dimension(145, 23));
		javaHashRadio.setSelected(true);
		javaHashRadio.addActionListener(this);
		buttonArea.add(javaHashRadio);

		sdbmHashRadio = new JRadioButton();
		hashRadioButtons.add(sdbmHashRadio);
		sdbmHashRadio.setText("sdbm");
		sdbmHashRadio.setPreferredSize(new Dimension(145, 23));
		sdbmHashRadio.addActionListener(this);
		buttonArea.add(sdbmHashRadio);

		djb2HashRadio = new JRadioButton();
		hashRadioButtons.add(djb2HashRadio);
		djb2HashRadio.setText("djb2");
		djb2HashRadio.setPreferredSize(new Dimension(145, 23));
		djb2HashRadio.addActionListener(this);
		buttonArea.add(djb2HashRadio);

		rotativeHashRadio = new JRadioButton();
		hashRadioButtons.add(rotativeHashRadio);
		rotativeHashRadio.setText("rotative");
		rotativeHashRadio.setPreferredSize(new Dimension(145, 23));
		rotativeHashRadio.addActionListener(this);
		buttonArea.add(rotativeHashRadio);

		grayHashRadio = new JRadioButton();
		hashRadioButtons.add(grayHashRadio);
		grayHashRadio.setText("gray");
		grayHashRadio.setPreferredSize(new Dimension(145, 23));
		grayHashRadio.addActionListener(this);
		buttonArea.add(grayHashRadio);

		shuffleHashRadio = new JRadioButton();
		hashRadioButtons.add(shuffleHashRadio);
		shuffleHashRadio.setText("shuffle");
		shuffleHashRadio.setPreferredSize(new Dimension(145, 23));
		shuffleHashRadio.addActionListener(this);
		buttonArea.add(shuffleHashRadio);

		sumHashRadio = new JRadioButton();
		hashRadioButtons.add(sumHashRadio);
		sumHashRadio.setText("sum");
		sumHashRadio.setPreferredSize(new Dimension(145, 23));
		sumHashRadio.addActionListener(this);
		buttonArea.add(sumHashRadio);

		/* Choose Compression functions */
		compRadioButtons = new ButtonGroup();

		JLabel compLabel = new JLabel("Compression Function:");
		compLabel.setPreferredSize(new Dimension(145, 23));
		buttonArea.add(compLabel);

		comp1Radio = new JRadioButton();
		compRadioButtons.add(comp1Radio);
		comp1Radio.setText("h%N");
		comp1Radio.setPreferredSize(new Dimension(145, 23));
		comp1Radio.setSelected(true);
		comp1Radio.addActionListener(this);
		buttonArea.add(comp1Radio);

		comp2Radio = new JRadioButton();
		compRadioButtons.add(comp2Radio);
		comp2Radio.setText("((a*h + b) % p) % N");
		comp2Radio.setPreferredSize(new Dimension(145, 23));
		comp2Radio.addActionListener(this);
		buttonArea.add(comp2Radio);

		/* Choose number of Buckets */
		bucketsTFLabel = new JLabel("N (Buckets): ");
		bucketsTFLabel.setPreferredSize(new Dimension(145, 24));
		buttonArea.add(bucketsTFLabel);

		bucketsField = new JTextField(7);
		bucketsField.setText("" + BUCKETS);
		bucketsField.setPreferredSize(new Dimension(90, 26));
		bucketsField.addActionListener(this);
		buttonArea.add(bucketsField);

		setBucketsButton = new JButton("Set");
		setBucketsButton.addActionListener(this);
		setBucketsButton.setPreferredSize(new Dimension(50, 26));
		buttonArea.add(setBucketsButton);

		/* set the a constant */
		aConstTFLabel = new JLabel("a (Prime): ");
		aConstTFLabel.setPreferredSize(new Dimension(145, 24));
		buttonArea.add(aConstTFLabel);

		aConstField = new JTextField(7);
		aConstField.setText("" + aConst);
		aConstField.setPreferredSize(new Dimension(90, 26));
		aConstField.addActionListener(this);
		buttonArea.add(aConstField);

		setaConstButton = new JButton("Set");
		setaConstButton.addActionListener(this);
		setaConstButton.setPreferredSize(new Dimension(50, 26));
		buttonArea.add(setaConstButton);

		/* set the b constant */
		bConstTFLabel = new JLabel("b: ");
		bConstTFLabel.setPreferredSize(new Dimension(145, 24));
		buttonArea.add(bConstTFLabel);

		bConstField = new JTextField(7);
		bConstField.setText("" + bConst);
		bConstField.setPreferredSize(new Dimension(90, 26));
		bConstField.addActionListener(this);
		buttonArea.add(bConstField);

		setbConstButton = new JButton("Set");
		setbConstButton.addActionListener(this);
		setbConstButton.setPreferredSize(new Dimension(50, 26));
		buttonArea.add(setbConstButton);

		/* set the p constant */
		pConstTFLabel = new JLabel("p (Prime, p >> a): ");
		pConstTFLabel.setPreferredSize(new Dimension(145, 24));
		buttonArea.add(pConstTFLabel);

		pConstField = new JTextField(7);
		pConstField.setText("" + pConst);
		pConstField.setPreferredSize(new Dimension(90, 26));
		bConstField.addActionListener(this);
		buttonArea.add(pConstField);

		setpConstButton = new JButton("Set");
		setpConstButton.addActionListener(this);
		setpConstButton.setPreferredSize(new Dimension(50, 26));
		buttonArea.add(setpConstButton);

		/* disable the extra constants by default. */
		aConstField.setEnabled(false);
		bConstField.setEnabled(false);
		pConstField.setEnabled(false);
		aConstTFLabel.setEnabled(false);
		bConstTFLabel.setEnabled(false);
		pConstTFLabel.setEnabled(false);
		setaConstButton.setEnabled(false);
		setbConstButton.setEnabled(false);
		setpConstButton.setEnabled(false);

		/* Draw button */
		drawButton = new JButton("Draw");
		drawButton.setPreferredSize(new Dimension(100, 24));
		drawButton.addActionListener(this);
		buttonArea.add(drawButton);

		/* Clear button */
		clearButton = new JButton("Clear All");
		clearButton.setPreferredSize(new Dimension(100, 24));
		clearButton.addActionListener(this);
		buttonArea.add(clearButton);

		/* Reset button */
		resetButton = new JButton("Reset");
		resetButton.setPreferredSize(new Dimension(100, 24));
		resetButton.addActionListener(this);
		buttonArea.add(resetButton);

		/* About button */
		aboutButton = new JButton("About");
		aboutButton.setPreferredSize(new Dimension(101, 25));
		aboutButton.addActionListener(this);
		aboutButton.setFont(Font.decode("SansSerif-Bold-12"));
		//Font:javax.swing.plaf.FontUIResource[family=SansSerif,name=sansserif,style=plain,size=12]
		//System.out.println("Font:" + aboutButton.getFont()) ;
		buttonArea.add(aboutButton);

		outerCanvas = new GCanvas();
		outerCanvas.setBackground(Color.white);

		topFrame.getContentPane().add(outerCanvas);

		canvas = new GCanvas();
		canvas.setBackground(Color.white);

		graph = new GCanvas();
		graph.setBackground(Color.lightGray);
		graph.setSize(MAXGRAPHWIDTH, MAXGRAPHHEIGHT);

		reInit();

		run();
	}

	/**
	 * give focus to the frame.
	 */
	public void run() {
		topFrame.setVisible(true);
		topFrame.setFocusable(true);
		topFrame.requestFocus();

		//draw the default function...
		updateConstants();
		int[] hpb = testWordsFromFile(pathToWordsFile);
		drawPointsOnGraph(hpb, nextColor());
	}

	private void drawPointsOnGraph(int[] hpb, Color color) {
		GPoint prevPoint = null;
		for (int x = 0; x < hpb.length; x++) {
			double y = graph.getHeight() - hpb[x];
			if (prevPoint == null) {
				prevPoint = new GPoint(x, y);
			} else {
				GLine line = new GLine(prevPoint.getX(), prevPoint.getY(), x, y);
				line.setColor(color);
				graph.add(line);
				prevPoint = new GPoint(x, y);
			}
		}
	}

	private int compFunction1(long code) {
		if (code > 0)
			return (int) (code % BUCKETS);
		else
			return (int) ((code % BUCKETS + BUCKETS) % BUCKETS);

		////same as:
		//int ret = ((int) code) % BUCKETS;
		//if (ret < 0)
		//	ret += BUCKETS;
		//return ret;
	}

	private int compFunction2(long code) {
		/*
		 * ((a * hashCode + b) mod p) mod N
		 * a, b, and p are positive integers, p is a large prime, and p >> N.
		 * Now, the number N of buckets doesn't need to be prime.
		 * 
		 * 127 is prime, 1999993 is prime.
		 * 65993 (the 'b' constant) doesn't have to be prime, tho here it is.
		 */
		long ret = ((aConst * code + bConst) % pConst) % BUCKETS;
		if (ret < 0)
			ret += BUCKETS;
		return (int) ret; // assuming the user uses less than Integer.MAX_VALUE buckets, this is fine.
	}

	/**
	 * http://www.cse.yorku.ca/~oz/hash.html
	 * adapted from C.
	 * Since Java doesn't have 'unsigned int/long', results may differ.
	 * BigInteger can be used, and then big.longValue();, on all these functions...
	 * But the goal, randomization, is achieved even when the sign bit matters
	 * and the results differ. They're just not portable in the case they differ.
	 * (randomization results not consistent across platforms).
	 * @param str
	 * @return
	 */
	private long djb2(String str) {
		long hash = 5381L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash << 5) + str.charAt(i);
		return hash;

		////if worrying about the sign bit. (results appear to be identical with my sample wordlist)
		//BigInteger big = BigInteger.valueOf(5381L);
		//for (int i = 0; i < str.length(); i++)
		//	big = big.shiftLeft(5).add(BigInteger.valueOf(str.charAt(i)));
		//return big.longValue();

		////slow version (results differ)
		//long hash = 5381L;
		//for (int i = 0; i < str.length(); i++)
		//	hash = hash * 33 + str.charAt(i);
		//return hash;

		////bernstein version (results differ)
		//long hash = 5381L;
		//for (int i = 0; i < str.length(); i++)
		//	hash = (hash * 33) ^ str.charAt(i);
		//return hash;
	}

	/**
	 * http://www.cse.yorku.ca/~oz/hash.html
	 * @param str
	 * @return
	 */
	private long sdbm(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash = str.charAt(i) + (hash << 6) + (hash << 16);
		return hash;

		////if worrying about the sign bit. (results appear to be identical with my sample wordlist)
		//BigInteger big = BigInteger.ZERO;
		//for (int i = 0; i < str.length(); i++)
		//	big = big.add(BigInteger.valueOf(str.charAt(i))).add(big.shiftLeft(6)).add(big.shiftLeft(16)).subtract(big);
		//return big.longValue();

		////alternate version (results differ)
		//long hash = 0;
		//for (int i = 0; i < str.length(); i++)
		//	hash = hash * 65599 + str.charAt(i);
		//return hash;
	}

	/**
	 * http://www.cse.yorku.ca/~oz/hash.html
	 * @param str
	 * @return
	 */
	private long sum(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash += str.charAt(i);
		return hash;
	}

	/**
	 * http://hbfs.wordpress.com/2011/11/22/hash-functions-checksums-part-ii/
	 * @param str
	 * @return
	 */
	private long rotative(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash = (hash << 5) ^ (hash >> 27) ^ str.charAt(i);
		return hash;

		////if worrying about the sign bit. (results DO differ, but visually they are equally good.)
		//BigInteger big = BigInteger.ZERO;
		//for (int i = 0; i < str.length(); i++)
		//	big = big.shiftLeft(5).xor(big.shiftRight(27)).xor(BigInteger.valueOf(str.charAt(i)));
		//return big.longValue();
	}

	/**
	 * http://hbfs.wordpress.com/2011/11/22/hash-functions-checksums-part-ii/
	 * @param str
	 * @return
	 */
	private long gray(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ (hash >> 1)) + str.charAt(i);
		return hash;

		////if worrying about the sign bit. (results appear to be identical with my sample wordlist)
		//BigInteger big = BigInteger.ZERO;
		//for (int i = 0; i < str.length(); i++)
		//	big = big.xor(big.shiftRight(1)).add(BigInteger.valueOf(str.charAt(i)));
		//return big.longValue();
	}

	/**
	 * http://hbfs.wordpress.com/2011/11/22/hash-functions-checksums-part-ii/
	 * @param str
	 * @return
	 */
	private long shuffle(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++)
			hash = perfect_shuffle(hash) + str.charAt(i);
		return hash;
	}

	/**
	 * http://hbfs.wordpress.com/2011/11/22/hash-functions-checksums-part-ii/
	 * due to the lack of unsigned primitives in java
	 * this may be off the desired behaviour... dunno...
	 * too lazy to rewrite this with BigInteger...
	 * This was originally written C and used with uint32_t, I'm hoping using long will work ok.
	 * According to http://mindprod.com/jgloss/unsigned.html  & and | are fine for signed longs,
	 * is that true?... In what context?...
	 * @param x
	 * @return long with lowest 32 bits 'perfectly' shuffled.
	 */
	private long perfect_shuffle(long x) {
		// see, for example, 'Hacker's Delight', p. 107
		x = (x & 0xff0000ff) | ((x & 0x0000ff00) << 8) | ((x & 0x00ff0000) >> 8);
		x = (x & 0xf00ff00f) | ((x & 0x00f000f0) << 4) | ((x & 0x0f000f00) >> 4);
		x = (x & 0xc3c3c3c3) | ((x & 0x0c0c0c0c) << 2) | ((x & 0x30303030) >> 2);
		x = (x & 0x99999999) | ((x & 0x22222222) << 1) | ((x & 0x44444444) >> 1);
		return x;
	}

	/**
	 * simplifies accessing the currently selected hash function
	 * @param line
	 * @return
	 */
	private long getHashCode(String line) {
		switch (currentHashFunction) {
			case SUM:
				return sum(line);
			case SDBM:
				return sdbm(line);
			case DJB2:
				return djb2(line);
			case ROTATIVE:
				return rotative(line);
			case GRAY:
				return gray(line);
			case SHUFFLE:
				return shuffle(line);
			case DEFAULT: // java's default String hashCode()
			default:
				return line.hashCode();
		}
	}

	/**
	 * simplifies accessing the currently selected compression function
	 * @param hashCode
	 * @return
	 */
	private int getComp(long hashCode) {
		switch (currentCompFunction) {
			case COMP2:
				return compFunction2(hashCode);
			case COMP1:
			default:
				return compFunction1(hashCode);
		}
	}

	private int[] testWordsFromFile(String pathToFile) {
		int[] hpb = new int[BUCKETS];
		BufferedReader rd;
		try {
			if (standAlone) {
				rd = new BufferedReader(new FileReader(pathToFile));
			} else {
				rd = new BufferedReader(
								new InputStreamReader(this.getClass().getResourceAsStream("/" + pathToFile)));
			}
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				}
				long hashCode = getHashCode(line);
				int comp = getComp(hashCode);
				try {
					hpb[comp]++;
				} catch (ArrayIndexOutOfBoundsException ex) {
					System.out.println("currentHashFunction:" + currentHashFunction
									+ " currentCompFunction:" + currentCompFunction
									+ " line:" + line
									+ " hashCode:" + hashCode
									+ " comp:" + comp);
					System.out.println(ex);
					System.exit(1);
				}
			}
			rd.close();

		} catch (IOException ex) {
			throw new Error(ex);
		}
		return hpb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == javaHashRadio) {
			currentHashFunction = hashFunction.DEFAULT;
		} else if (e.getSource() == sdbmHashRadio) {
			currentHashFunction = hashFunction.SDBM;
		} else if (e.getSource() == djb2HashRadio) {
			currentHashFunction = hashFunction.DJB2;
		} else if (e.getSource() == sumHashRadio) {
			currentHashFunction = hashFunction.SUM;
		} else if (e.getSource() == rotativeHashRadio) {
			currentHashFunction = hashFunction.ROTATIVE;
		} else if (e.getSource() == grayHashRadio) {
			currentHashFunction = hashFunction.GRAY;
		} else if (e.getSource() == shuffleHashRadio) {
			currentHashFunction = hashFunction.SHUFFLE;
		} else if (e.getSource() == comp1Radio) {
			currentCompFunction = compFunction.COMP1;
			aConstField.setEnabled(false);
			bConstField.setEnabled(false);
			pConstField.setEnabled(false);
			aConstTFLabel.setEnabled(false);
			bConstTFLabel.setEnabled(false);
			pConstTFLabel.setEnabled(false);
			setaConstButton.setEnabled(false);
			setbConstButton.setEnabled(false);
			setpConstButton.setEnabled(false);
		} else if (e.getSource() == comp2Radio) {
			currentCompFunction = compFunction.COMP2;
			aConstField.setEnabled(true);
			bConstField.setEnabled(true);
			pConstField.setEnabled(true);
			aConstTFLabel.setEnabled(true);
			bConstTFLabel.setEnabled(true);
			pConstTFLabel.setEnabled(true);
			setaConstButton.setEnabled(true);
			setbConstButton.setEnabled(true);
			setpConstButton.setEnabled(true);
		} else if (e.getSource() == clearButton) {
			graph.removeAll();
			currentColor = 0; // resets the colors
		} else if (e.getSource() == resetButton) {
			//restore default values for buckets, a, b ,p.
			BUCKETS = _DEFAULT_BUCKETS;
			aConst = _DEFAULT_aConst;
			bConst = _DEFAULT_bConst;
			pConst = _DEFAULT_pConst;
			bucketsField.setText("" + BUCKETS);
			aConstField.setText("" + aConst);
			bConstField.setText("" + bConst);
			pConstField.setText("" + pConst);
		} else if (e.getActionCommand().equals("Set")
						|| e.getSource() == bucketsField
						|| e.getSource() == aConstField
						|| e.getSource() == bConstField
						|| e.getSource() == pConstField //	|| e.getSource() == setBucketsButton
						) {
			System.out.println("updateConstants");
			updateConstants();
		} else if (e.getSource() == drawButton) {
			//first check to see if the user changed the BUCKETS
			updateConstants();
			int[] hpb = testWordsFromFile(pathToWordsFile);
			drawPointsOnGraph(hpb, nextColor());
		} else if (e.getSource() == aboutButton) {
			//launch the about dialogue, centered in the screen.
			Toolkit kit = topFrame.getToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;

			AboutDialogue dialogue = new AboutDialogue(topFrame, false);
			dialogue.setLocation(screenWidth / 2 - dialogue.getWidth() / 2,
							screenHeight / 2 - dialogue.getHeight() / 2);
			dialogue.setVisible(true);
			if(!standAlone)
				dialogue.setAppletContext(appletContext);
		}
	}

	/**
	 * updates BUCKETS, aConst, bConst, pConst from their respective textFields
	 */
	public void updateConstants() {
		try {
			int field = Integer.parseInt(bucketsField.getText());
			BUCKETS = field;
		} catch (NumberFormatException ex) {
			bucketsField.setText("" + BUCKETS);
		}
		try {
			int field = Integer.parseInt(aConstField.getText());
			aConst = field;
		} catch (NumberFormatException ex) {
			aConstField.setText("" + aConst);
		}
		try {
			int field = Integer.parseInt(bConstField.getText());
			bConst = field;
		} catch (NumberFormatException ex) {
			bConstField.setText("" + bConst);
		}
		try {
			int field = Integer.parseInt(pConstField.getText());
			pConst = field;
		} catch (NumberFormatException ex) {
			pConstField.setText("" + pConst);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		reInit();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	/**
	 * gets the next color from a pool of colors.
	 * @return
	 */
	private Color nextColor() {
		//currentColor++;
		currentColor %= colors.length;
		return colors[currentColor++];
	}

	public void setAppletContext(AppletContext ac) {
		appletContext = ac;
	}
}
