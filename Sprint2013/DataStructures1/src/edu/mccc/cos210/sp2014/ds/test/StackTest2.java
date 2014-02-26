package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Stack;
import edu.mccc.cos210.sp2014.ds.DoublyLinkedList;

public class StackTest2 {

	public static void main(String[] sa) {
		new StackTest2().doIt();
	}
	
	private void doIt() {
		Stack<String, DoublyLinkedList<String>> stack = new Stack<String, DoublyLinkedList<String>>(new DoublyLinkedList<String>());
		System.out.println(stack.toString());
		stack.push("A");
		System.out.println(stack.toString());
		stack.push("B");
		System.out.println(stack.toString());
		stack.push("C");
		System.out.println(stack.toString());
		stack.pop();
		System.out.println(stack.toString());
		String value = stack.top();
		System.out.println(value + " on top");
		System.out.println(stack.toString());
	}

}
