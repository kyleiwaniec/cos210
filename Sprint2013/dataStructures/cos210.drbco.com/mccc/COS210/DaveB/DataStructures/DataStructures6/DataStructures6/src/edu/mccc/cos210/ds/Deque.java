package edu.mccc.cos210.ds;
public interface Deque<E> {
	void addFirst(E element);
	void addLast(E element);
	E removeFirst();
	E removeLast();
	E getFirst();
	E getLast();
	int getSize();
	boolean isEmpty();
}
