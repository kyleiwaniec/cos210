import java.util.*;


/** A class to represent a passenger */
public class Passenger{
	// Data fields
	/** The ID number for this passenger */
	private int passengerId;
	/** The time needed to process this pasenger */
	private int processingTime;
	/** The time this passenger arrives */
	private int arrivalTime;
	/** The maximum time to process a pasenger */
	private static int maxProcessingTime = 1;
	/** The sequence number for passengers. */
	private static int idNum = 0;

	/** Create a new passenger 
		@param arrivalTime The time the passenger arrives
	*/
	public Passenger(int arrivalTime){
		this.arrivalTime = arrivalTime;
		Random rand = new Random();
		processingTime = 1 + rand.nextInt(maxProcessingTime);
		passengerId = idNum++;
	}	
	/** Get the arrival time.
		@return The arrival time
	*/
	public int getArrivalTime(){
		return arrivalTime;
	}	
	/** Get the processing time
		@return The provcessing time
	*/
	public int getProcessingTime(){
		return processingTime;
	}	
	/** Get the passenger id
		@return The passenger Id
	*/
	public int getId(){
		return passengerId;
	}	
	/** Set the maximum processing time
		@param maxProcessingTime The new value
	*/
	public static void setMaxProcessingTime(int maxProcessTime){
		maxProcessingTime = maxProcessTime;
	}	
}