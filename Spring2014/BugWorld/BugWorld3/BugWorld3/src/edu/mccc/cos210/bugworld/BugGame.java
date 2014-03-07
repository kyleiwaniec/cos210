package edu.mccc.cos210.bugworld;

public class BugGame {
	private BugGameNetwork bugGameNetwork;
	private BugGameModel bugGameModel;
	private BugGameView bugGameView;
	private Bug bug1;
	private Bug bug2;
	public BugGame(String bug1Name, String bug2Name, String host, int port) {
		loadBugs(bug1Name, bug2Name);
		setBugGameNetwork(new BugGameNetwork(host, port));
		setBugGameModel(new BugGameModel(getBug1(), getBug2(), getBugGameNetwork()));
		setBugGameView(new BugGameView(getBugGameModel()));
	}
	private void loadBugs(String bug1Name, String bug2Name) {
		for (String[] sa : bugInfo) {
			try {
				Bug bug = new Bug();
				bug.setBugModel(new BugModel(sa[0]));
				BugView bv = (BugView) Class.forName(
					"edu.mccc.cos210.bugworld.bug." +
					sa[0]
				).newInstance();
				bug.setBugView(bv);
				if (bug.getBugModel().getMoniker().equals(bug1Name)) {
					setBug1(bug);
					System.out.println("Loaded Bug1 - " + bug.toString());
					getBug1().getBugModel().setY(Double.parseDouble(sa[4]) - 384.0);
					getBug1().getBugModel().setX(Double.parseDouble(sa[5]) - 513.0);
				} else {
					if (bug.getBugModel().getMoniker().equals(bug2Name)) {
						setBug2(bug);
						System.out.println("Loaded Bug2 - " + bug.toString());
						getBug2().getBugModel().setY(Double.parseDouble(sa[4]) - 384.0);
						getBug2().getBugModel().setX(Double.parseDouble(sa[5]) - 513.0);
					}
				}
			} catch (Exception ex) {
			}
		}
	}
	public BugGameModel getBugGameModel() {
		return this.bugGameModel;
	}
	private void setBugGameModel(BugGameModel bugGameModel) {
		this.bugGameModel = bugGameModel;
	}
	public BugGameView getBugGameView() {
		return this.bugGameView;
	}
	private void setBugGameView(BugGameView bugGameView) {
		this.bugGameView = bugGameView;
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
	public BugGameNetwork getBugGameNetwork() {
		return bugGameNetwork;
	}
	public void setBugGameNetwork(BugGameNetwork bugGameNetwork) {
		this.bugGameNetwork = bugGameNetwork;
	}
	static final String[][] bugInfo = {
		{"Tom","TommyT","1000","1.0","64","85"},
		{"Tartarus","MattB","1000","-1.0","64","256"},
		{"Zubenelgenubi","JavierB","500","0.1","64","427"},
		{"Zubenelschemali","Collin","S500","0.1","64","598"},
		{"MacAndCheese","JakeP","475","0.25","64","769"},
		{"Beelzebub","BrandonB","450","0.25","64","940"},
		{"Hades","AlexA","450","0.25","192","85"},
		{"Lucifer","JonO","400","0.25","192","256"},
		{"Mantus","KyleM","400","0.25","192","427"},
		{"Orcus","StephenD","375","0.25","192","598"},
		{"LethalInjection","NickD","375","0.25","192","769"},
		{"Anubis","DominicC","375","0.5","192","940"},
		{"Osiris","DeepP","350","0.5","320","85"},
		{"SilentDeath","LeylaA","350","0.5","320","256"},
		{"Thunderstorm","HarleyH","350","0.5","320","427"},
		{"Apocalypse","JoshuaW","350","0.5","320","598"},
		{"ViralInfection","RobertM","325","0.5","320","769"},
		{"Nostradamus","HassanS","325","0.75","320","940"},
		{"Misery","KyleH","325","0.75","448","85"},
		{"Armageddon","CarlosR","300","0.75","448","256"},
		{"DoctorEvil","KellyM","300","0.75","448","427"},
		{"Undertaker","CesarS","275","0.75","448","598"},
		{"Annihilator","SerhiyL","275","0.75","448","769"},
		{"Napoleon","GabrielC","275","0.75","448","940"},
		{"Excalibur","SemionR","250","0.75","576","85"},
		{"WidowMaker","KevinV","250","0.9","576","256"},
		{"FlamingBurrito","IkkeiI","225","0.9","576","427"},
		{"WildThang","MariamP","225","0.9","576","598"},
		{"MommasBadBoy","EricR","200","0.9","576","769"},
		{"Spanky","DanielLF","200","0.9","576","940"},
		{"LittleWillie","SamuelJ","99","0.99","704","85"},
		{"Bob","JuniorW","99","1.0","704","256"}
	};
}
