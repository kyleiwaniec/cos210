package edu.mccc.cos210.tests;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.Utility;
public class SinglyLinkedListTest1 {
	public static void main(String[] sa) throws Exception {
		new SinglyLinkedListTest1().doIt();
	}
	private void doIt() throws Exception {
		SinglyLinkedList<String> slls = Utility.getSinglyLinkedList();
		slls.addFirst("Fred");
		System.out.println(slls.toString() + " " + slls.getSize());
		slls.removeFirst();
		System.out.println(slls.toString() + " " + slls.getSize());
		slls.addFirst("Bob");
		
		System.out.println(slls.toString() + " " + slls.getSize());
		for (int i = 1; i < slls.getSize(); i++) {
			System.out.println(i + " " + slls.getNext());
		}
		slls.addFirst("Tom");
		System.out.println(slls.toString() + " " + slls.getSize());
		for (int i = 1; i < slls.getSize(); i++) {
			System.out.println(i + " " + slls.getNext());
		}
		slls.addLast("Joe");
		slls.getFirst();
		System.out.println("getFirst():"+ slls.getFirst());
		
		for (int i = 1; i < slls.getSize(); i++) {
			System.out.println(i + " " + slls.getNext());
		}
		System.out.println(slls.toString() + " " + slls.getSize());
		String s = slls.getFirst();
		System.out.println(s + " " + slls.toString() + " " + slls.getSize());
		for (int i = 1; i < slls.getSize(); i++) {
			System.out.println(i + " " + slls.getNext());
		}
		slls.removeFirst();
		System.out.println(slls.toString() + " " + slls.getSize());
		slls.removeFirst();
		System.out.println(slls.toString() + " " + slls.getSize());
		
		slls.addLast("Mary");
		System.out.println(slls.toString() + " " + slls.getSize());
		slls.removeFirst();
		System.out.println(slls.toString() + " " + slls.getSize());
		slls.removeFirst();
		System.out.println(slls.toString() + " " + slls.getSize());
		//slls.removeFirst();
	}
}
