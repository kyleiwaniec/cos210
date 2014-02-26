class INTtest{

	static int i=0;
	static long l =0;

	public static void main(String[] args){

		// if (Integer.parseInt(args[0])<2147483647) {args[0]="2147483647";}

		// System.out.println(Integer.parseInt(args[0]));

		// i=Integer.parseInt(args[0])+1;

		// System.out.println(i);
		// l= (long)i;
		// try{
		// 	while (l<Long.MAX_VALUE&&l>Long.MIN_VALUE){
		// 	l *= 2;

		// 	System.out.println(l);
		// 	}
		// }catch(Exception e){}


		int a = 340;
		int b = 60;

		//int c = (a % b + b) % b;
		int c = a % b;
			if (c < 0){
			    c += b;
			}

//we subtract 60 * -6 = -360 from -340

		System.out.println(c);

	}
}