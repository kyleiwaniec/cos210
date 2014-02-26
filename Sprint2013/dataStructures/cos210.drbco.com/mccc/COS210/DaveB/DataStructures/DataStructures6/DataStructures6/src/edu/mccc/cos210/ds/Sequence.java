package edu.mccc.cos210.ds;
import edu.mccc.cos210.ds.List;
public interface Sequence<E> extends List<E>, java.lang.Iterable<E> {
	boolean add(E e);
	void add(int index, E element) throws IndexOutOfBoundsException;
	boolean contains(Object o);
	E get(int index) throws IndexOutOfBoundsException;
	int indexOf(Object o);
	java.util.Iterator<E> iterator();
	int lastIndexOf(Object o);
	E remove(int index) throws IndexOutOfBoundsException;
	boolean remove(Object o);
	E set(int index, E element) throws IndexOutOfBoundsException;
	Sequence<E> subSequence(int fromIndex, int toIndex) throws IndexOutOfBoundsException;
	int getSize();
	boolean isEmpty();
}
