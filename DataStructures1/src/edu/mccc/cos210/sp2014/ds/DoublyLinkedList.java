package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.iface.IDoublyLinkedList;

public class DoublyLinkedList<E> implements IDoublyLinkedList<E> {

	private DoublyLinkedListNode<E> head = null;
	private DoublyLinkedListNode<E> tail = null;
	private int size = 0;
	
	public DoublyLinkedList() {
	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(this);
	}
	
	@Override
	public java.util.ListIterator<E> listIterator() {
		return new DoublyLinkedListListIterator<E>(this);
	}

	@Override
	public void addFirst(E value) {
	}

	@Override
	public void addLast(E value) {
	}

	@Override
	public E removeFirst() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public E removeLast() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int i) {
		this.size = i;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	private void addFirstNode(DoublyLinkedListNode<E> dnode) {
	}
	
	private void addLastNode(DoublyLinkedListNode<E> dnode) {
	}
	
	DoublyLinkedListNode<E> getFirstNode() {
		return null;
	}

	DoublyLinkedListNode<E> getLastNode() {
		return null;
	}
	
	DoublyLinkedListNode<E> getNextNode(DoublyLinkedListNode<E> dnode) {
		return null;
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSize() + " [ ");
		for (E value : this) {
			sb.append(value.toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	static class DoublyLinkedListNode<V> {
		private V value;
		private DoublyLinkedListNode<V> next;
		private DoublyLinkedListNode<V> previous;
		
		public DoublyLinkedListNode(V value) {
			this.value = value;
			this.next = null;
			this.previous = null;
		}
		public V getValue() {
			return this.value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public DoublyLinkedListNode<V> getNext() {
			return this.next;
		}
		public DoublyLinkedListNode<V> getPrevious() {
			return this.previous;
		}
		public void setNext(DoublyLinkedListNode<V> next) {
			this.next = next;
		}
		public void setPrevious(DoublyLinkedListNode<V> previous) {
			this.previous = previous;
		}
	}

	private class DoublyLinkedListIterator<T> implements java.util.Iterator<E> {

		protected DoublyLinkedListNode<E> current;
		
		public DoublyLinkedListIterator(DoublyLinkedList<E> dlist) {	
			this.current = dlist.getFirstNode();
		}
		
		@Override
		public boolean hasNext() {
			if (this.current == null) {
				return false;
			}
			return this.current != null;
		}

		@Override
		public E next() throws java.util.NoSuchElementException {
			return null;
		}

		@Override
		public void remove() throws UnsupportedOperationException, IllegalStateException {
			throw new UnsupportedOperationException();
		}
		
	}

	private class DoublyLinkedListListIterator<T> extends DoublyLinkedListIterator<E> implements java.util.ListIterator<E> {

		public DoublyLinkedListListIterator(DoublyLinkedList<E> dlist) {
			super(dlist);
		}
		
		@Override
		public void add(E e) throws IllegalStateException {
		}
		
		@Override
		public void set(E e) throws IllegalStateException {
		}

		@Override
		public boolean hasPrevious() {
			if (this.current == null) {
				return false;
			}
			return current.getPrevious() != null;
		}

		@Override
		public E previous() throws java.util.NoSuchElementException {
			return null;
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}

}
