package edu.mccc.cos210.ds.kyleh.impl;
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
	@Override //todo
	public E getPrevious() {
		return null;
	}
	@Override //todo
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element);
		node.next = this.head;
		this.head = node;
		this.size++;
		this.current = this.head;
		if (this.size == 1) {
			this.tail = node;
		}
	}
	@Override //todo
	public void addLast(E element) {
		DNode<E> last = this.tail;
		if (last == null) {
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);
			last.next = node;
			tail = node;
			node.previous = this.current;
			this.current = node;
			this.size++;
		}
	}
	@Override //todo
	public void addNext(E element) {
		
		if (this.current == this.tail) {
			addLast(element);
		} else if (size == 0) {
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);
			node.next = this.current.next;
			this.current.next = node;
			node.previous = this.current;
			this.current = node;
			this.size++;
		}
	}
	@Override //todo
	public void addPrevious(E element) {
		DNode<E> first = this.head;
		if (first == null) {
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);
			node.next = this.current;
			node.previous = this.current.previous;
			this.current.previous.next = node;
			this.current = node;
			this.size++;
		}
	}
	@Override //todo
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.head;
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
	@Override //todo
	public void removeLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.tail;
		System.out.println("tail is: "+this.tail.element);
		this.tail = this.tail.previous;
		System.out.println("tail is: "+this.tail.element);
	
		node.element = null;
		
		this.size--;
		
		if (this.size == 0) {
			this.tail = null;
		} else {
			this.current = this.tail;
			
		}
	}
	@Override //todo
	public void removeNext() {
	}
	@Override //todo
	public void removePrevious() {
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
