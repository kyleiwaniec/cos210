package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.DoublyLinkedList.DoublyLinkedListNode;
import edu.mccc.cos210.sp2014.ds.iface.ISortedList;

public class SortedList<E extends java.lang.Comparable<E>> implements ISortedList<E> {
	private DoublyLinkedList<E> theList = new DoublyLinkedList<>();
	@Override
	public void add(E value) {
	}
	@Override
	public java.util.Iterator<E> iterator() {
		return theList.iterator();
	}
	@Override
	public String toString() {
		return theList.toString();
	}
}
