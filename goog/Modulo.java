class Modulo{

	static int i=0;
	static long l =0;

	public static void main(String[] args){


		int a = 340;
		int b = 60;

		int c = a % b;
			if (c < 0){
			    c += b;
			}


		System.out.println(c);

	}
}