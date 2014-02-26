public class TestChar{
	public static void main(String[] args){
		String s = "abcd";

		char[] ia = new char[128];
		int[] ia = new int[128];

		for(int i = 0; i < s.length(); i++){
			ia[s.charAt(i)] = s.charAt(i);
		}
		for(int j = 0; j < ia.length; j++){
			System.out.println(ia[j]);
		}
		
	}
}
