package edu.mccc.cos210.sp2014.ds.iface;

public interface IDoublyLinkedList<E> extends ISequence<E> {
	E removeLast();
	java.util.Iterator<E> iterator();
	java.util.ListIterator<E> listIterator();
}
