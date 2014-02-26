package edu.mccc.cos210.ds.daveb.impl;
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
	public E getFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head.element;
	}
	@Override
	public E getLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	@Override
	public E getNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			this.current = null;
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
	public E getPrevious() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.previous;
		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	@Override
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element);
		if (this.head != null) {
			node.previous = null;
			node.next = this.head;
			this.head.previous = node;
			this.head = node;
		} else {
			node.next = null;
			node.previous = null;
			this.tail = node;
			this.head = node;
		}
		this.current = node;
		this.size++;
	}
	@Override
	public void addLast(E element) {
		if (this.tail == null) {
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);
			node.next = null;
			node.previous = this.tail;
			this.tail.next = node;
			this.tail = node;
			this.current = node;
			this.size++;
		}
	}
	@Override
	public void addNext(E element) throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = new DNode<E>(element);
		node.previous = this.current;
		node.next = this.current.next;
		this.current.next.previous = node;
		this.current.next = node;
		this.current = node;
		this.size++;
	}
	@Override
	public void addPrevious(E element) throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = new DNode<E>(element);
		node.next = this.current;
		node.previous = this.current.previous;
		this.current.previous.next = node;
		this.current.previous = node;
		this.current = node;
		this.size++;
	}
	@Override
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		if (size == 1) {
			this.current = null;
			this.tail = null;
		} else {
			this.current = this.head.next;
			this.head.next.previous = null;
		}
		DNode<E> node = this.head;
		this.head = node.next;
		node.reset();
		this.size--;
	}
	@Override
	public void removeLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		if (size == 1) {
			this.current = null;
			this.head = null;
		} else {
			this.current = this.tail.previous;
			this.tail.previous.next = null;
		}
		DNode<E> node = this.tail;
		this.tail = node.previous;
		node.reset();
		this.size--;
	}
	@Override
	public void removeNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.next == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.current.next;
		this.current.next = node.next;
		if (this.current.next != null) {
			this.current.next.previous = this.current;
		} else {
			this.tail = this.current;
			this.current = null;
		}
		node.reset();
		this.size--;
	}
	@Override
	public void removePrevious() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.previous == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.current.previous;
		this.current.previous = node.previous;
		if (this.current.previous != null) {
			this.current.previous.next = this.current;
		} else {
			this.head = this.current;
			this.current = null;
		}
		node.reset();
		this.size--;
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
