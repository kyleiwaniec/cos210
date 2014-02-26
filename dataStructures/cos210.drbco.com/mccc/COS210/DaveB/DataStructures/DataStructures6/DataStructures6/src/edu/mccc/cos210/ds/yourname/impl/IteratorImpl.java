package edu.mccc.cos210.ds.yourname.impl;
import edu.mccc.cos210.ds.Sequence;
import edu.mccc.cos210.ds.Utility;
public class IteratorImpl<T> implements java.util.Iterator<T> {
	private Sequence<T> sequence;
	public IteratorImpl(Sequence<T> sequence) {
		this.sequence = sequence;
	}
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public T next() {
		return null;
	}
	@Override
	public void remove() {
	}
	@Override
	public String toString() {
		return "";
	}
}
