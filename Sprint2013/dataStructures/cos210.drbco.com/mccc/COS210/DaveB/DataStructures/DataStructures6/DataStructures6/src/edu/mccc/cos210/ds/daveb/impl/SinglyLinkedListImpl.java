package edu.mccc.cos210.ds.daveb.impl;
import edu.mccc.cos210.ds.SinglyLinkedList;
public class SinglyLinkedListImpl<E> implements SinglyLinkedList<E> {
	private SNode<E> head;
	private SNode<E> tail;
	private SNode<E> current;
	private int size = 0;
	public SinglyLinkedListImpl() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	@Override
	public E getFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head.element;
	}
	@Override
	public E getLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	@Override
	public E getNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.next;
		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	@Override
	public void addFirst(E element) {
		SNode<E> node = new SNode<E>(element);
		node.next = this.head;
		this.head = node;
		this.size++;
		this.current = this.head;
		if (this.size == 1) {
			this.tail = node;
		}
	}
	@Override
	public void addLast(E element) {
		SNode<E> last = this.tail;
		if (last == null) {
			addFirst(element);
		} else {
			SNode<E> node = new SNode<E>(element);
			last.next = node;
			tail = node;
			this.current = node;
			this.size++;
		}
	}
	@Override
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		SNode<E> node = this.head;
		this.head = this.head.next;
		node.element = null;
		node.next = null;
		this.size--;
		if (this.size == 0) {
			this.tail = null;
		} else {
			this.current = this.head;
		}
	}
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("< ");
		SNode<E> node = head;
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
	private class SNode<T> {
		private T element;
		private SNode<T> next = null;
		public SNode(T element) {
			this.element = element;
		}
	}
}
