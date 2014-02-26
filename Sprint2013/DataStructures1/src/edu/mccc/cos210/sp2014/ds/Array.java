package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.iface.IArray;

public class Array<T> implements IArray<T> {
	
	private Object[] theArray = new Object[1];
	
	public Array(int size) {
		if (size > 0) {
			setTheArray(new Object[size]);
		}
	}

	protected Object[] getTheArray() {
		return this.theArray;
	}

	protected void setTheArray(Object[] array) {
		this.theArray = array;
	}

	protected void boundsCheck(int index) {
		throw new java.lang.IndexOutOfBoundsException();
	}
	
	@Override
	public java.util.Iterator<T> iterator() {
		return new ArrayIterator<T>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		boundsCheck(index);
		return null;
	}

	@Override
	public void set(int index, T value) {
		boundsCheck(index);
	}

	@Override
	public int getSize() {
		return this.theArray.length;
	}

	@Override
	public boolean isEmpty() {
		return this.theArray.length == 0;
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSize() + " [ ");
		for (T value : this) {
			if (value == null) {
				sb.append("\"null\" ");
			} else {
				sb.append(value.toString() + " ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private class ArrayIterator<E> implements java.util.Iterator<E> {
		
		int index;
		
		public ArrayIterator() {
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			return this.index < theArray.length;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() throws java.util.NoSuchElementException {
			if (index >= theArray.length) {
				throw new java.util.NoSuchElementException();
			}
			return null;
		}

		@Override
		public void remove() throws UnsupportedOperationException, IllegalStateException {
			throw new UnsupportedOperationException();
		}
		
	}

}
