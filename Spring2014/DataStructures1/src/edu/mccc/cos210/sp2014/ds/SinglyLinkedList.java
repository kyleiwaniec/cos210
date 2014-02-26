package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.iface.ISinglyLinkedList;

public class SinglyLinkedList<E> implements ISinglyLinkedList<E> {
	
	private SinglyLinkedListNode<E> head = null;
	private SinglyLinkedListNode<E> tail = null;
	private int size = 0;
	
	public SinglyLinkedList() {
	}

	@Override
	public java.util.Iterator<E> iterator() {
		return new SinglyLinkedListIterator<E>(this);
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
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	private void addFirstNode(SinglyLinkedListNode<E> snode) {
	}
	
	private void addLastNode(SinglyLinkedListNode<E> snode) {
	}
	
	private SinglyLinkedListNode<E> getFirstNode() {
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
	
	private class SinglyLinkedListNode<V> {
		private V value;
		private SinglyLinkedListNode<V> next;
		public SinglyLinkedListNode(V value) {
			this.value = value;
			this.next = null;
		}
		public V getValue() {
			return this.value;
		}
		public SinglyLinkedListNode<V> getNext() {
			return this.next;
		}
		public void setNext(SinglyLinkedListNode<V> next) {
			this.next = next;
		}
	}

	private class SinglyLinkedListIterator<T> implements java.util.Iterator<E> {
		
		private SinglyLinkedListNode<E> current;
		
		public SinglyLinkedListIterator(SinglyLinkedList<E> slist) {
			this.current = slist.getFirstNode();
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

}
