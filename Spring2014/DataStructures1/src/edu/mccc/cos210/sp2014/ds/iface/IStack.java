package edu.mccc.cos210.sp2014.ds.iface;

public interface IStack<E> extends ISize {
	void push(E value);
	E pop();
	E top();
}
