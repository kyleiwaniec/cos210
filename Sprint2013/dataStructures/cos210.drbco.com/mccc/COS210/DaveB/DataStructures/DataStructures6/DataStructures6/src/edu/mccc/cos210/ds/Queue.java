package edu.mccc.cos210.ds;
public interface Queue<E> {
	void enqueue(E element);
	E dequeue();
	E getFirst();
	int getSize();
	boolean isEmpty();
}
