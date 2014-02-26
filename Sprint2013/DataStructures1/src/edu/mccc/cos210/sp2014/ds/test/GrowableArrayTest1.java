package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Array;
import edu.mccc.cos210.sp2014.ds.GrowableArray;

public class GrowableArrayTest1 {

	public static void main(String[] sa) {
		new GrowableArrayTest1().doIt();
	}
	
	private void doIt() {
		Array<Integer> array = new GrowableArray<>(2);
		System.out.println(array.toString());
		for (int i = 0; i < 32; i++) {
			array.set(i, 40 + i);
			System.out.println(array.toString());
		}
		try {
			array.set(array.getSize() + 1, 99);
		} catch (Exception ex) {
			System.out.println("Expected Exception");
		}
		System.out.println(array.toString());
	}

}
