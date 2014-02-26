package edu.mccc.cos210.ds.yourname.impl;
import edu.mccc.cos210.ds.DoublyLinkedList;
public class DoublyLinkedListImpl<E> implements DoublyLinkedList<E> {
	private DNode<E> head;
	private DNode<E> tail;
	private DNode<E> current;
	private int size = 0;
	public DoublyLinkedListImpl() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	@Override
	public E getFirst() {
		return null;
	}
	@Override
	public E getLast() {
		return null;
	}
	@Override
	public E getNext() {
		return null;
	}
	@Override
	public E getPrevious() {
		return null;
	}
	@Override
	public void addFirst(E element) {
	}
	@Override
	public void addLast(E element) {
	}
	@Override
	public void addNext(E element) {
	}
	@Override
	public void addPrevious(E element) {
	}
	@Override
	public void removeFirst() {
	}
	@Override
	public void removeLast() {
	}
	@Override
	public void removeNext() {
	}
	@Override
	public void removePrevious() {
	}
	@Override
	public int getSize() {
		return -1;
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("< ");
		DNode<E> node = head;
		while (node != null) {
			if (node.element != null) {
				sb.append(node.element.toString() + " ");
			} else {
				sb.append("null ");
			}
			node = node.next;
		}
		sb.append(">");
		return sb.toString();
	}
	private class DNode<T> {
		private T element;
		private DNode<T> next = null;
		private DNode<T> previous = null;
		public DNode(T element) {
			this.element = element;
		}
		private void reset() {
			this.element = null;
			this.next = null;
			this.previous = null;
		}
	}
}
