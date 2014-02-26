package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Deque;

public class DequeTest1 {

	public static void main(String[] sa) {
		new DequeTest1().doIt();
	}
	
	private void doIt() {
		Deque<String> deque = new Deque<>();
		System.out.println(deque.toString());
		deque.enqueueBack("A");
		System.out.println(deque.toString());
		deque.enqueueBack("B");
		System.out.println(deque.toString());
		deque.enqueueBack("C");
		System.out.println(deque.toString());
		deque.enqueueFront("D");
		System.out.println(deque.toString());
		deque.enqueueFront("E");
		System.out.println(deque.toString());
		deque.enqueueFront("F");
		System.out.println(deque.toString());
		deque.dequeueFront();
		System.out.println(deque.toString());
		deque.dequeueFront();
		System.out.println(deque.toString());
		deque.dequeueBack();
		System.out.println(deque.toString());
		deque.dequeueBack();
		System.out.println(deque.toString());
		System.out.println(deque.front() + " at front");
		System.out.println(deque.toString());
		System.out.println(deque.back() + " at back");
		System.out.println(deque.toString());
	}

}
