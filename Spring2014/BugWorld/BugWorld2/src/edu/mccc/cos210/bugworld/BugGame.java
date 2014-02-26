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
				} else {
					if (bug.getBugModel().getMoniker().equals(bug2Name)) {
						setBug2(bug);
						System.out.println("Loaded Bug2 - " + bug.toString());
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
		{"Tom","TommyT","1000","1.0"},
		{"Tartarus","MattB","1000","-1.0"},
		{"Zubenelgenubi","JavierB","500","0.1"},
		{"Zubenelschemali","Collin","S500","0.1"},
		{"MacAndCheese","JakeP","475","0.25"},
		{"Beelzebub","BrandonB","450","0.25"},
		{"Hades","AlexA","450","0.25"},
		{"Lucifer","JonO","400","0.25"},
		{"Mantus","KyleM","400","0.25"},
		{"Orcus","StephenD","375","0.25"},
		{"LethalInjection","NickD","375","0.25"},
		{"Anubis","DominicC","375","0.5"},
		{"Osiris","DeepP","350","0.5"},
		{"SilentDeath","LeylaA","350","0.5"},
		{"Thunderstorm","HarleyH","350","0.5"},
		{"Apocalypse","JoshuaW","350","0.5"},
		{"ViralInfection","RobertM","325","0.5"},
		{"Nostradamus","HassanS","325","0.75"},
		{"Misery","KyleH","325","0.75"},
		{"Armageddon","CarlosR","300","0.75"},
		{"DoctorEvil","KellyM","300","0.75"},
		{"Undertaker","CesarS","275","0.75"},
		{"Annihilator","SerhiyL","275","0.75"},
		{"Napoleon","GabrielC","275","0.75"},
		{"Excalibur","SemionR","250","0.75"},
		{"WidowMaker","KevinV","250","0.9"},
		{"FlamingBurrito","IkkeiI","225","0.9"},
		{"WildThang","MariamP","225","0.9"},
		{"MommasBadBoy","EricR","200","0.9"},
		{"Spanky","DanielLF","200","0.9"},
		{"LittleWillie","SamuelJ","99","0.99"},
		{"Bob","JuniorW","99","1.0"}
	};
}
