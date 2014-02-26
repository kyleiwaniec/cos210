public class PerfectSquare{
	public static void main(String[] args){
		System.out.println(isPerfectSquare(Integer.parseInt(args[0])));

		// MAX int value: 2147483647 = 2^31-1
		// But, the maximum value I am able to work with is 2^30, larger numbers below MAX do not throw error but stall.
	}
// 	public static boolean isPerfectSquare(int input){

// 		for(int i = 1; i < input && i > 0; i*=2){
// 		System.out.println("i: "+i);	
// 			int product = i*i;
// 			if (product == input){
// 				System.out.println("power of 2: "+i);
// 				return true;
// 			}else if(product > input){
// 				int lowerBound = i/2;
// 				int upperBound = i;
// 				return binarySearch(lowerBound, upperBound, input);
// 			}
// 		}
// 		return false;
// 	}
// 	public static boolean binarySearch(int low, int high, int input){
// 		int mid, product, ans = 0;
		
// 		while (low <= high) {
// 			mid = (low + high) / 2;
// 			System.out.println("low: "+low+", high:" +high+", middle: "+mid);
// 			product = mid*mid;
// 			if(product == input){
// 				System.out.println("other square: "+mid);
// 				ans = 1;
// 				break;
// 			} else if (product > input) {
// 				high = mid - 1;
// 			} else  {
// 				low = mid + 1;
// 			}
// 		}
// 		return ans == 1 ? true : false;
// 	}
// }

public class PerfectSquare{
	public static void main(String[] args){
		System.out.println(isPerfectSquare(Integer.parseInt(args[0])));
		// MAX int value: 2147483647 = 2^31-1
		// But, the maximum value I am able to work with is 2^30, larger numbers below MAX do not throw error but stall.
	}
	public static boolean isPerfectSquare(long input){
		for(long i = 1; i < input; i*=2){
			long product = i*i;
			if (product == input){
				System.out.println("power of 2: "+i);
				return true;
			}else if(product > input){
				long lowerBound = i/2;
				long upperBound = i;
				return binarySearch(lowerBound, upperBound, input);
			}
		}
		return false;
	}
	public static boolean binarySearch(long low, long high, long input){
		long mid, product, ans = 0;
		
		while (low <= high) {
			mid = (low + high) / 2;
			System.out.println("low: "+low+", high:" +high+", middle: "+mid);
			product = mid*mid;
			if(product == input){
				System.out.println("other square: "+mid);
				ans = 1;
				break;
			} else if (product > input) {
				high = mid - 1;
			} else  {
				low = mid + 1;
			}
		}
		return ans == 1 ? true : false;
	}
}