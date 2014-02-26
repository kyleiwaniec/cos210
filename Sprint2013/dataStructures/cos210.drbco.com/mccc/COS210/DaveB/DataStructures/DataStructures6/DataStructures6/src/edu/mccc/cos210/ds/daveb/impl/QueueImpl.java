package edu.mccc.cos210.ds.daveb.impl;
import edu.mccc.cos210.ds.Queue;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.Utility;
public class QueueImpl<E> implements Queue<E> {
	private SinglyLinkedList<E> sllist;
	public QueueImpl() {
		try {
			this.sllist = Utility.getSinglyLinkedList();
		} catch (Exception e) {
			System.err.println("Unable to create SinglyLinkedList");
		}
	}
	@Override
	public void enqueue(E element) {
		this.sllist.addLast(element);
	}
	@Override
	public E dequeue() {
		if (this.sllist.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.sllist.getFirst();
		this.sllist.removeFirst();
		return element;
	}
	@Override
	public E getFirst() {
		if (this.sllist.isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.sllist.getFirst();
		return element;
	}
	@Override
	public int getSize() {
		return this.sllist.getSize();
	}
	@Override
	public boolean isEmpty() {
		return this.sllist.isEmpty();
	}
	@Override
	public String toString() {
		return this.sllist.toString();
	}
}
