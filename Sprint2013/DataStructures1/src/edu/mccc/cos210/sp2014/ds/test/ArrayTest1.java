package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Array;

public class ArrayTest1 {

	public static void main(String[] sa) {
		new ArrayTest1().doIt();
	}
	
	private void doIt() {
		Array<Integer> array = new Array<>(8);
		System.out.println(array.toString());
		for (int i = 0; i < array.getSize(); i++) {
			array.set(i, 40 + i);
			System.out.println(array.toString());
		}
		try {
			array.set(array.getSize(), 99);
		} catch (Exception ex) {
			System.out.println("Expected Exception");
		}
		System.out.println(array.toString());
	}

}
