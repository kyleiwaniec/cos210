package edu.mccc.cos210.sp2014.ds.iface;

public interface IDeque<E> extends ISize {
	void enqueueFront(E value);
	void enqueueBack(E value);
	E dequeueFront();
	E dequeueBack();
	E front();
	E back();
}
