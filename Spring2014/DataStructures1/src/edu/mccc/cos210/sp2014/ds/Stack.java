package edu.mccc.cos210.sp2014.ds;

import edu.mccc.cos210.sp2014.ds.iface.ISequence;
import edu.mccc.cos210.sp2014.ds.iface.IStack;

public class Stack<E, V extends ISequence<E>> implements IStack<E> {
	private ISequence<E> dsimpl;
	
	public Stack(ISequence<E> dsimpl) {
		this.dsimpl = dsimpl;
	}

	@Override
	public void push(E value) {
	}

	@Override
	public E pop() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public E top() throws java.util.NoSuchElementException {
		return null;
	}

	@Override
	public int getSize() {
		return this.dsimpl.getSize();
	}

	@Override
	public boolean isEmpty() {
		return this.dsimpl.isEmpty();
	}

	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSize() + " [ ");
		for (E value : dsimpl) {
			sb.append(value.toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
