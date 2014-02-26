package edu.mccc.cos210.sp2014.ds.iface;

public interface IArray<E> extends java.lang.Iterable<E>, ISize {
	E get(int index);
	void set(int index, E value);
}
