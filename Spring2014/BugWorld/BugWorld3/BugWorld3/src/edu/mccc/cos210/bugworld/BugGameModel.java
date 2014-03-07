package edu.mccc.cos210.bugworld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;


public class BugGameModel extends Observable implements ActionListener {
	private List<Line2D> walls = new LinkedList<>();
	private BugGameNetwork bugGameNetwork;
	private Bug bug1;
	private Bug bug2;
	public BugGameModel(Bug bug1, Bug bug2, BugGameNetwork bugGameNetwork) {
		createWalls();
		setBug1(bug1);
		setBug2(bug2);
		setBugGameNetwork(bugGameNetwork);
	}
	private void createWalls() {
		getWalls().add(new Line2D.Double(171.0 - 513.0, 128.0 - 384.0, 171.0 - 513.0, 512.0 - 384.0));
		getWalls().add(new Line2D.Double(171.0 - 513.0, 512.0 - 384.0, 513.0 - 513.0, 512.0 - 384.0));
		getWalls().add(new Line2D.Double(855.0 - 513.0, 640.0 - 384.0, 855.0 - 513.0, 256.0 - 384.0));
		getWalls().add(new Line2D.Double(855.0 - 513.0, 256.0 - 384.0, 513.0 - 513.0, 256.0 - 384.0));
		getWalls().add(new Line2D.Double(427.0 - 513.0, 384.0 - 384.0, 598.0 - 513.0, 384.0 - 384.0));
	}
	public List<Line2D> getWalls() {
		return this.walls;
	}
	public Bug getBug1() {
		return this.bug1;
	}
	public void setBug1(Bug bug) {
		this.bug1 = bug;
	}
	public Bug getBug2() {
		return this.bug2;
	}
	public void setBug2(Bug bug) {
		this.bug2 = bug;
	}
	public void reset() {
		getBug1().getBugModel().reset();
	}
	public void toggleTurbo() {
		getBug1().getBugModel().toggleTurbo();
	}
	public BugGameNetwork getBugGameNetwork() {
		return bugGameNetwork;
	}
	public void setBugGameNetwork(BugGameNetwork bugGameNetwork) {
		this.bugGameNetwork = bugGameNetwork;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		BugGameNetwork bgn = getBugGameNetwork();
		getBug1().getBugModel().doActionPerformed(ae);
		String s = getBug1().getBugMsg();
		bgn.getSendQueue().offer(s);
		s = "";
		try {
			while (true) {
				s = bgn.getRecvQueue().remove(0);
			}
		} catch (IndexOutOfBoundsException ex) {
		}
		BugModel bm = bug2.getBugModel();
		if (s != null && !s.equals("")) {
			String[] sa = s.split(",");
			bm.setX(Double.parseDouble(sa[0]));
			bm.setY(Double.parseDouble(sa[1]));
			bm.setHeading(Double.parseDouble(sa[2]));
			bm.setFrameScale(bm.getFrameScale() + 1);
			if (bm.getFrameScale() > 3) {
				bm.toggleFrame();
				bm.setFrameScale(0);
			}
		}
	}
}
