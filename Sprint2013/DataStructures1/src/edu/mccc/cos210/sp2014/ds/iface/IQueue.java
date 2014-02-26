package edu.mccc.cos210.sp2014.ds.iface;

public interface IQueue<E> extends ISize {
	void enqueue(E value);
	E dequeue();
	E front();
}
