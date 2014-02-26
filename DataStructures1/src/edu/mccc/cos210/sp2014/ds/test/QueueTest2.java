package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Queue;
import edu.mccc.cos210.sp2014.ds.DoublyLinkedList;

public class QueueTest2 {

	public static void main(String[] sa) {
		new QueueTest2().doIt();
	}
	
	private void doIt() {
		Queue<String, DoublyLinkedList<String>> queue = new Queue<String, DoublyLinkedList<String>>(new DoublyLinkedList<String>());
		System.out.println(queue.toString());
		queue.enqueue("A");
		System.out.println(queue.toString());
		queue.enqueue("B");
		System.out.println(queue.toString());
		queue.enqueue("C");
		System.out.println(queue.toString());
		queue.dequeue();
		System.out.println(queue.toString());
		String value = queue.front();
		System.out.println(value + " at front");
		System.out.println(queue.toString());
	}

}
