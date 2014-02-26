package edu.mccc.cos210.ds.daveb.impl;
import edu.mccc.cos210.ds.Array;
public class ArrayImpl<E> implements Array<E> {
	private int size = 0;
	private Object[] array = null;
	public ArrayImpl(int size) {
		this.size = size;
		this.array = new Object[size];
	}
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws ArrayIndexOutOfBoundsException {
		checkIndex(index);
		return (E) this.array[index];
	}
	@Override
	public void set(int index, E o) throws ArrayIndexOutOfBoundsException {
		checkIndex(index);
		this.array[index] = o;
	}
	@Override
	public int getSize() {
		return this.size;
	}
	private void checkIndex(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= this.size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for (Object o : array) {
			if (o != null) {
				sb.append(o.toString() + " ");
			} else {
				sb.append("null ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
