package edu.mccc.cos210.bugworld;


public class Bug {
	private BugModel bugModel;
	private BugView bugView;
	public Bug() {
	}
	public BugModel getBugModel() {
		return this.bugModel;
	}
	public void setBugModel(BugModel bugModel) {
		this.bugModel = bugModel;
	}
	public BugView getBugView() {
		return this.bugView;
	}
	public void setBugView(BugView bugView) {
		this.bugView = bugView;
	}
	public String getBugMsg() {
		BugModel bm = getBugModel();
		StringBuilder sb = new StringBuilder();
		sb.append(bm.getX() + "," + bm.getY() + "," + bm.getHeading());
		return sb.toString();
	}
	@Override
	public String toString() {
		String s = getBugModel().getMoniker();
		return s;
	}
}

