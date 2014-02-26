package edu.mccc.cos210.ds.yourname.impl;
import java.util.Iterator;
import edu.mccc.cos210.ds.List;
import edu.mccc.cos210.ds.SortedList;
import edu.mccc.cos210.ds.Utility;
public class SortedListImpl<E extends Comparable<E>> implements SortedList<E>, java.lang.Iterable<E> {
	List<E> list = null;
	public SortedListImpl() {
		try {
			this.list = Utility.getList();
		} catch (Exception ex) {
		}
	}
	@Override
	public boolean add(E e) {
		// does not allow null to be added. implementation uses insertion sort on add.
		return false;
	}
	@Override
	public boolean contains(Object o) {
		return false;
	}
	@Override
	public java.util.Iterator<E> iterator() {
		return null;
	}
	@Override
	public boolean remove(Object o) {
		return false;
	}
	@Override
	public int getSize() {
		return -1;
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
	@Override
	public String toString() {
		return "";
	}
}
