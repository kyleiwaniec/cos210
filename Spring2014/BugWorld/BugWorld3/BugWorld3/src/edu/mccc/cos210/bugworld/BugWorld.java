package edu.mccc.cos210.bugworld;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToolBar;


public class BugWorld {
	private BugGame bugGame;
	public BugWorld(String bug1Name, String bug2Name, String hostName, String portName) {
		setBugGame(createBugGame(bug1Name, bug2Name, hostName, Integer.parseInt(portName)));
		JFrame jf = new JFrame("BugWorld");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(
			createToolBar(),
			BorderLayout.NORTH
		);
		jf.add(
			getBugGame().getBugGameView(),
			BorderLayout.CENTER
		);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().
			addKeyEventDispatcher(
				new BugWorldKeyEventDispatcher(getBugGame().getBugGameView())
			)
		;
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		new javax.swing.Timer(33, getBugGame().getBugGameModel()).start();
		jf.setVisible(true);
	}
	public static void main(final String[] sa) {
		EventQueue.invokeLater(
			new Runnable() {
				@Override
				public void run() {
					String bug1Name = "Bob";
					String bug2Name = "Tom";
					String hostName = "localhost";
					String portName = "5975";
					switch (sa.length) {
						case 1:
							bug1Name = sa[0];
							break;
						case 2:
							bug1Name = sa[0];
							bug2Name = sa[1];
							break;
						case 3:
							bug1Name = sa[0];
							bug2Name = sa[1];
							hostName = sa[2];
							break;
						case 4:
							bug1Name = sa[0];
							bug2Name = sa[1];
							hostName = sa[2];
							portName = sa[3];
							break;
						default:
							break;
					}
					new BugWorld(bug1Name, bug2Name, hostName, portName);
				}
			}
		);
	}
	private BugGame createBugGame(String bug1Name, String bug2Name, String hostName, int port) {
		BugGame bugGame = new BugGame(bug1Name, bug2Name, hostName, port);
		return bugGame;
	}
	private JToolBar createToolBar() {
		final BugGameModel bugGameModel = getBugGame().getBugGameModel();
		final BugGameView bugGameView = getBugGame().getBugGameView();
		JToolBar jtb = new JToolBar();
		jtb.setFloatable(false);
		JCheckBox jcb = new JCheckBox("Turbo");
		jtb.add(jcb);
		jcb.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					bugGameModel.toggleTurbo();
					bugGameView.repaint();
				}
			}
		);
		return jtb;
	}
	public BugGame getBugGame() {
		return this.bugGame;
	}
	public void setBugGame(BugGame bugGame) {
		this.bugGame = bugGame;
	}
}
