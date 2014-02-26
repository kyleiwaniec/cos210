package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.DoublyLinkedList;

public class DoublyLinkedListTest1 {

	public static void main(String[] sa) {
		new DoublyLinkedListTest1().doIt();
	}
	
	private void doIt() {
		DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
		String s;
		System.out.println(doublyLinkedList.toString());
		doublyLinkedList.addFirst("Bob");
		System.out.println(doublyLinkedList.toString());
		s = doublyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(doublyLinkedList.toString());
		doublyLinkedList.addFirst("Frank");
		System.out.println(doublyLinkedList.toString());
		doublyLinkedList.addFirst("Hotdog");
		System.out.println(doublyLinkedList.toString());
		doublyLinkedList.addLast("Pepsi");
		System.out.println(doublyLinkedList.toString());
		s = doublyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(doublyLinkedList.toString());
		s = doublyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(doublyLinkedList.toString());
		s = doublyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(doublyLinkedList.toString());
		try {
			s = doublyLinkedList.removeFirst();
			System.out.println("\"" + s + "\" Removed");
			System.out.println(doublyLinkedList.toString());
		} catch (java.util.NoSuchElementException ex) {
			System.out.println("Expected Exception");
		}
		doublyLinkedList.addLast("Coke");
		System.out.println(doublyLinkedList.toString());
		java.util.ListIterator<String> listIterator = doublyLinkedList.listIterator();
		listIterator.add("A");
		listIterator.add("B");
		listIterator.add("C");
		listIterator.previous();
		listIterator.previous();
		listIterator.add("X");
		System.out.println(doublyLinkedList.toString());
		s = doublyLinkedList.removeFirst();
		System.out.println("\"" + s + "\" Removed");
		System.out.println(doublyLinkedList.toString());		
	}
}
