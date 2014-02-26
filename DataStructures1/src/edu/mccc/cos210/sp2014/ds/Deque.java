package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.iface.IDeque;

public class Deque<E> implements IDeque<E> {
	
	private DoublyLinkedList<E> dsimpl = new DoublyLinkedList<>();

	@Override
	public void enqueueFront(E value) {
	}

	@Override
	public void enqueueBack(E value) {
	}

	@Override
	public E dequeueFront() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public E dequeueBack() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public E front() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public E back() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public int getSize() {
		return this.dsimpl.getSize();
	}

	@Override
	public boolean isEmpty() {
		return this.dsimpl.isEmpty();
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSize() + " [ ");
		for (E value : dsimpl) {
			sb.append(value.toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
