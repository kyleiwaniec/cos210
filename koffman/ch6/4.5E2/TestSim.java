public class TestSim{
	public static void main(String[] args){
		AirlineCheckinSim sim = new AirlineCheckinSim();
		sim.enterData();
		sim.runSimulation();
		sim.showStats();
		System.exit(0);
	}
}