package edu.mccc.cos210.ds;
import edu.mccc.cos210.ds.List;
public interface SortedList<E extends Comparable<E>> extends List<E>, java.lang.Iterable<E> {
	boolean add(E e); // does not allow null to be added.
	boolean contains(Object o);
	java.util.Iterator<E> iterator();
	boolean remove(Object o);
	int getSize();
	boolean isEmpty();
}
