package edu.mccc.cos210.ds.yourname.impl;
import java.util.Iterator;
import edu.mccc.cos210.ds.List;
import edu.mccc.cos210.ds.Sequence;
import edu.mccc.cos210.ds.Utility;
public class ListImpl<E> implements List<E>, java.lang.Iterable<E> {
	Sequence<E> sequence = null;
	public ListImpl() {
		try {
			this.sequence = Utility.getSequence();
		} catch (Exception ex) {
		}
	}
	@Override
	public boolean add(E e) {
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
