package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.SinglyLinkedList;

public class SinglyLinkedListTest1 {

	public static void main(String[] sa) {
		new SinglyLinkedListTest1().doIt();
	}
	
	private void doIt() {
		SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();
		String s;
		System.out.println(singlyLinkedList.toString());
		singlyLinkedList.addFirst("Bob");
		System.out.println(singlyLinkedList.toString());
		s = singlyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(singlyLinkedList.toString());
		singlyLinkedList.addFirst("Frank");
		System.out.println(singlyLinkedList.toString());
		singlyLinkedList.addFirst("Hotdog");
		System.out.println(singlyLinkedList.toString());
		singlyLinkedList.addLast("Pepsi");
		System.out.println(singlyLinkedList.toString());
		s = singlyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(singlyLinkedList.toString());
		s = singlyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(singlyLinkedList.toString());
		s = singlyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(singlyLinkedList.toString());
		try {
			s = singlyLinkedList.removeFirst();
			System.out.println("\"" + s + "\" Removed");
			System.out.println(singlyLinkedList.toString());
		} catch (java.util.NoSuchElementException ex) {
			System.out.println("Expected Exception");
		}
		singlyLinkedList.addLast("Coke");
		System.out.println(singlyLinkedList.toString());
	}
}
