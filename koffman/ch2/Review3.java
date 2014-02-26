import java.util.*;
/**
This class prints out some stats for linear searches
@param testIterations The number of tests run to get an average
@param dataLength The length of the data array being searched
*/
public class Review3{
	// SETUP:
	protected static int DEFAULT_ITERATION_COUNT = 50;
	protected static int DEFAULT_DATA_LENGTH = 100;
	protected static int DEFAULT_DATA_MULTIPLIER = 3;
	protected static int testIterations = 0;
	protected static int dataLength = 0;
	protected static int searchIterations = 0;
	protected static int totalSearchIterations = 0;
	protected static int failedSearches = 0;
	protected static int successfulSearches = 0;
	protected static int averageSearchIterations = 0;

	public static void main(String[] args) {
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

		int[] dataArr = new int[dataLength];
		populateDataArr(dataArr);

		// do 3 diffrent sorts: 
		// args[2] : 1 => shuffle, 2 => sort ASC, 3 => Inverse
		try{
			if(args.length == 3){

				switch(Integer.parseInt(args[2])){
					case 1 : shuffleArray(dataArr);
						break;
					case 2 : Arrays.sort(dataArr);
						break;
					case 3 : reverseSort(dataArr);
						break;
					default : shuffleArray(dataArr);
						break;
				}
			 }
		}catch(NumberFormatException nfet){
			System.out.println("Please enter only integers");
            System.exit(1);
		}
		
		System.out.println(Arrays.toString(dataArr));

		for(int i = 0; i < testIterations; i++){
			// specify a number to search for - random number between 0 and twice the dataLength, so that the chances of either finding it or not finding it are 50/50
			Random rnd = new Random();
			int target = rnd.nextInt((dataLength*DEFAULT_DATA_MULTIPLIER)-1); // x times bigger so that it can be any int from the original data set
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
		
		if(successfulSearches>0){
			averageSearchIterations = (int)(searchIterations/successfulSearches);
			System.out.println("Average number of iterations for a successful search: " + averageSearchIterations);
		}else{
			averageSearchIterations = 0;
			System.out.println("No successful searhces");
		}
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
			if(target > dataLength-1){
				return count;
			}else if(dataArr[i] == target){
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
		// make the set to choose from 5 times bigger than the target set
		int[] b = new int[a.length * DEFAULT_DATA_MULTIPLIER]; 
		for(int i = b.length - 1; i >= 0; i--){b[i] = i;}
		shuffleArray(b);
		for(int i = a.length - 1; i >= 0; i--){a[i] = b[i];}
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
  	/**
	Sorts an int array in DESC order
	@param ar Array to be sorted
	*/
	public static void reverseSort(int[] ar){
		int temp;
		int len = ar.length-1;
		Arrays.sort(ar);
		for (int i = len /2; i >= 0; i--){
		     temp = ar[i];
		     ar[i] = ar[len - i];
		     ar[len - i] = temp;
		  }
  	}
}