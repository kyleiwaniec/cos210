import java.util.*;
import java.lang.Math;

public class AirlineCheckinSim{
	//Data Fileds
	/** Queue of frequent flyers */
	private PassengerQueue frequentFlyerQueue = new PassengerQueue("Frequent Flyer");
	/** Queue of regular passengers */
	private PassengerQueue regularPassengerQueue = new PassengerQueue("Regular Passenger");
	/** Maximum number of frequent flyers to be served before a regular passenger is served */
	private int frequentFlyerMax;
	/** Maximum time to service a passenger */
	private int maxProcessingTime;
	/** Total simulated time */
	private int totalTime;
	/** if set true, print additional output */
	private boolean showAll;
	/** Simulated clock */
	private int clock = 0;
	/** Time that the agent will be done with the current passenger */
	private int timeDone;
	/** Number of frequent flyers served since the last regular pasenger was served. */
	private int frequentFlyerSinceRP;

	// public methods
	/** gets the input values for simulation */
	public void enterData(){
		/*********
		set the following values:
		*/
		frequentFlyerQueue.setArrivalRate(0.25);
		regularPassengerQueue.setArrivalRate(0.25);
		maxProcessingTime = 4;
		totalTime = 60;
		showAll = true;
	
	}
	public void runSimulation(){
		for(clock = 0; clock < totalTime; clock++){
			frequentFlyerQueue.checkNewArrival(clock, showAll);
			regularPassengerQueue.checkNewArrival(clock, showAll);
			if(clock >= timeDone){
				startServe();
			}
		}
	}
	public void startServe(){
		if(!frequentFlyerQueue.isEmpty() && ((frequentFlyerSinceRP <= frequentFlyerMax) || regularPassengerQueue.isEmpty())){
				// serve the next frequent flyer
			frequentFlyerSinceRP++;
			timeDone = frequentFlyerQueue.update(clock, showAll);

		}else if(!regularPassengerQueue.isEmpty()){
			// Serve the next regular passenger
			frequentFlyerSinceRP = 0;
			timeDone = regularPassengerQueue.update(clock, showAll);

		}else if(showAll){
			System.out.println("Time is: " +clock+ " server is idle");
		}
	}
	/** Method to show the statistics */
	public void showStats(){
		System.out.println("the number of regular passengers served was: "+regularPassengerQueue.getNumServed());
		double averageWaitingTime1 = (double) regularPassengerQueue.getTotalWait() / (double) regularPassengerQueue.getNumServed();
		System.out.println(" with an average waiting time of: "+averageWaitingTime1);

		System.out.println("the number of frequent fluers served was: "+frequentFlyerQueue.getNumServed());
		double averageWaitingTime2 = (double) frequentFlyerQueue.getTotalWait() / (double) frequentFlyerQueue.getNumServed();
		System.out.println(" with an average waiting time of: "+averageWaitingTime2);

		System.out.println("Passengers in frequent flyer Queue: "+frequentFlyerQueue.size());
		System.out.println("Passengers in regular passenger Queue: "+regularPassengerQueue.size());

	}

}