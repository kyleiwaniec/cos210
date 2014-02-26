import java.util.*;

/** Class to simulate a queue of passengers */
public class PassengerQueue{
	// Data fields
	/** The Queue of passengers*/
	private Queue<Passenger> theQueue;
	/** The number of passengers served */
	private int numServed;
	/** The total time passengers were waiting */
	private int totalWait;
	/** The name of this Queue */
	private String queueName;
	/** The average arrival rate */
	private double arrivalRate;

	//Constructor
	/** Construct a passenger Queue with the given name.
		@param queueName The name of this Queue
	*/
	public PassengerQueue(String queueName){
		numServed = 0;
		totalWait = 0;
		this.queueName = queueName;
		theQueue = new LinkedList<Passenger>();
	}
	/** Check if a new rrival has occured.
		@param clock The current simulated time
		@param showAll Flag to indicate that detailed data should be putput.
	*/
	public void checkNewArrival(int clock, boolean showAll){
		if(Math.random() < arrivalRate){
			theQueue.add(new Passenger(clock));
			if(showAll){
				System.out.println("Time is: "+clock+": "+queueName+" arrival, new queue size is "+theQueue.size());
			}
		}
	}
	public int getNumServed(){
		return this.numServed;
	}
	public int getTotalWait(){
		return this.totalWait;
	}
	public int size(){
		return theQueue.size();
	}
	public double setArrivalRate(double ar){
		return this.arrivalRate = ar;
	}

	public boolean isEmpty(){
		return theQueue.peek() == null;
	}
	/** Update statistics.
		pre: The queue is not empty
		@param clock The current simulated time
		@param showAll Flag to indicate whether to show detail
		@return Time passenger is done being served
	*/
	public int update(int clock, boolean showAll){
		Passenger nextPassenger = theQueue.remove();
		int timeStamp = nextPassenger.getArrivalTime();
		int wait = clock - timeStamp;
		totalWait += wait;
		numServed++;
		if(showAll){
			System.out.println("Time is: "+clock+": Serving "+queueName+" with timestamp "+timeStamp);
		}
		return clock + nextPassenger.getProcessingTime();
	}	
}