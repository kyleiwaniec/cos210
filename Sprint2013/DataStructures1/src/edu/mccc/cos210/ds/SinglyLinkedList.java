package edu.mccc.cos210.ds;
public interface SinglyLinkedList<E> {
	E getFirst();
	E getLast();
	E getNext();
	void addFirst(E element);
	void addLast(E element);
	void removeFirst();
	int getSize();
	boolean isEmpty();
}
