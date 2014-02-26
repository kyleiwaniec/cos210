public class Words{
	public static void main(String[] args){
		//int wordToInt = new Integer(Integer.parseInt(args[0], 10));
		String s = args[0];
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			System.out.println("numeric: "+Character.getNumericValue(c)); // numerical representation
			System.out.println("ascii: "+(int)c); // ascii value
		}
		char c = 'c';
		char f = 'f';
		System.out.println(c<f);
	}
}


/**
number of possible words (range): 0 - 1 million (permutations)
input data: 200,000 words

number letters: ascii set: 128

word length longest: about 30 [ "Antidisestablishmentarianism" ]
word length common: ~5

n = 128 * 30 = 3840
q should be 2^11 = 2048 [closest power of 2 smaller than n]
*/