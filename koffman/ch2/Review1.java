import java.util.*;
/**
This class prints out some stats for linear searches
@param testIterations The number of tests run to get an average
@param dataLength The length of the data array being searched
*/
public class Review1{
	public static void main(String[] args) {
		// SETUP:
		int DEFAULT_ITERATION_COUNT = 50;
		int DEFAULT_DATA_LENGTH = 100;
		int testIterations = 0;
		int dataLength = 0;

		try {
            if(args.length == 0){
            	testIterations = DEFAULT_ITERATION_COUNT;
            	dataLength = DEFAULT_DATA_LENGTH;
            }else if(args.length == 1){
            	testIterations = Integer.parseInt(args[0]);
            	dataLength = DEFAULT_DATA_LENGTH;
            }else{
            	testIterations = Integer.parseInt(args[0]);
            	dataLength = Integer.parseInt(args[1]);
            }            
        }
        catch (NumberFormatException nfe) {
            System.out.println("Please enter only integers");
            System.exit(1);
        }
		
		int searchIterations = 0;
		int totalSearchIterations = 0;
		int failedSearches = 0;
		int successfulSearches = 0;

		int[] dataArr = new int[dataLength];
		populateDataArr(dataArr);
		shuffleArray(dataArr);

		for(int i = 0; i < testIterations; i++){
			// specify a number to search for - random number between 0 and twice the dataLength, so that the chances of either finding it or not finding it are 50/50
			Random rnd = new Random();
			int target = rnd.nextInt((dataLength*2)-1);
			if(linearSearch(dataLength, target, dataArr) != 0){
				// sum up the number of iterations through the array for successful searches, 
				searchIterations += linearSearch(dataLength, target, dataArr);
				// and store the number of successful searches for use in averaging the results.
				successfulSearches++;
			}else{
				// sum up number of failed searches
				failedSearches++;
			}
		}
		int averageSearchIterations = (int)(searchIterations/successfulSearches);

		System.out.println("Average number of iterations for a successful search: " + averageSearchIterations);
		System.out.println("Number of failed searches: "+ failedSearches);

	}
	/**
	linear Search for a target int in an array of ints
	@param dataLength The length of the array being searched - we could calculate this value, but since it's already stored above, we'll just use the cached value
	@param target The int being searhced for
	@param dataArr The array being searched
	@return number of iterations for successful search, or 0 for failed search.
	*/
	public static int linearSearch(int dataLength, int target, int[] dataArr){
		int count = 0;
		for(int i = dataLength-1; i > 0; i--){
			if(dataArr[i] == target){
				// we have a match, store 'dataLength-i' (because we're looping backwards): that's how many iterations it took to find the match.
				count = dataLength-i;
			}
		}
		return count; // if the search was unsuccessful, count will equal 0.
	}
	/**
	Populates an int array
	@param a Array to be populated
	@return The populated array
	*/
	public static int[] populateDataArr(int[] a){
		for(int i = a.length - 1; i > 0; i--){
			a[i] = i;
		}
		return a;
	}
	/**
	Shuffles an int array
	@param ar Array to be shuffled
	*/
	public static void shuffleArray(int[] ar){
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
  	}
}