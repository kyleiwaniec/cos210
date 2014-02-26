package edu.mccc.cos210.sp2014.ds.test;

import edu.mccc.cos210.sp2014.ds.Queue;
import edu.mccc.cos210.sp2014.ds.SinglyLinkedList;

public class QueueTest1 {

	public static void main(String[] sa) {
		new QueueTest1().doIt();
	}
	
	private void doIt() {
		Queue<String, SinglyLinkedList<String>> queue = new Queue<String, SinglyLinkedList<String>>(new SinglyLinkedList<String>());
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
