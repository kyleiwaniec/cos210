package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.SortedList;

public class SortedListTest1 {

	public static void main(String[] sa) {
		new SortedListTest1().doIt();
	}

	private void doIt() {
		SortedList<Integer> sortedList = new SortedList<>();
		System.out.println(sortedList.toString());
		sortedList.add(3);
		System.out.println(sortedList.toString());
		sortedList.add(6);
		System.out.println(sortedList.toString());
		sortedList.add(6);
		System.out.println(sortedList.toString());
		sortedList.add(5);
		System.out.println(sortedList.toString());
		sortedList.add(2);
		System.out.println(sortedList.toString());
		sortedList.add(1);
		System.out.println(sortedList.toString());
		sortedList.add(5);
		System.out.println(sortedList.toString());
		sortedList.add(4);
		System.out.println(sortedList.toString());
		sortedList.add(4);
		System.out.println(sortedList.toString());
		for (int n : sortedList) {
			System.out.print(n + " ");
		}
		System.out.println();
		sortedList.add(0);
		System.out.println(sortedList.toString());
		sortedList.add(7);
		System.out.println(sortedList.toString());
		SortedList<String> sortedListString = new SortedList<>();
		sortedListString.add("Hello");
		sortedListString.add("GoodBye");
		sortedListString.add("Adios");
		sortedListString.add("Aloha");
		for (String s : sortedListString) {
			System.out.print(s + " ");
		}
	}
}
