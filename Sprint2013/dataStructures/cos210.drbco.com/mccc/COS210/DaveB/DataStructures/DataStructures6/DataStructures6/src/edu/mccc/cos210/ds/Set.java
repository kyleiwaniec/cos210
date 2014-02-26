package edu.mccc.cos210.ds;
public interface Set<E> extends java.lang.Iterable<E> {
	boolean add(E e);
	void add(Set<E> s);
	boolean contains(Object o);
	boolean contains(Set<E> s);
	Set<E> union(Set<E> s);
	Set<E> intersection(Set<E> s);
	Set<E> difference(Set<E> s);
	java.util.Iterator<E> iterator();
	int getSize();
	boolean isEmpty();
}
