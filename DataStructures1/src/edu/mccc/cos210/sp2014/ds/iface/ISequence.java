package edu.mccc.cos210.sp2014.ds.iface;

public interface ISequence<E> extends java.lang.Iterable<E>, ISize {
	void addFirst(E value);
	void addLast(E value);
	E removeFirst();
}
