package edu.mccc.cos210.ds.yourname.impl;
import java.util.Iterator;
import edu.mccc.cos210.ds.Sequence;
import edu.mccc.cos210.ds.Utility;
public class SequenceImpl<E> implements Sequence<E>, java.lang.Iterable<E> {
	public SequenceImpl() {
	}
	@Override
	public boolean add(E e) {
		return false;
	}
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
	}
	@Override
	public boolean contains(Object o) {
		return false;
	}
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return null;
	}
	@Override
	public int indexOf(Object o) {
		return -1;
	}
	@Override
	public java.util.Iterator<E> iterator() {
		return null;
	}
	@Override
	public int lastIndexOf(Object o) {
		return -1;
	}
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		return null;
	}
	@Override
	public boolean remove(Object o) {
		return false;
	}
	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		return null;
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
	public Sequence<E> subSequence(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
		return null;
	}
	@Override
	public String toString() {
		return "";
	}
}
