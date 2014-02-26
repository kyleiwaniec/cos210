package edu.mccc.cos210.ds;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
public class Utility {
	private static java.util.Properties properties = new java.util.Properties();
	static {
		try {
			properties.load(new FileInputStream("dsimpl.properties"));
		} catch (Exception ex) {
			System.err.println("Bad Property File");
			System.exit(-1);
		}
	}
	public static <E> Array<E> getArray(int size) throws Exception {
		if (size <= 0) {
			throw new IllegalArgumentException("Bad Array Size Requested");
		}
		Class<?> clazz = Class.forName(properties.getProperty("array"));
		Constructor<?> ctor = clazz.getConstructor(int.class);
		@SuppressWarnings("unchecked")
		Array<E> array = (Array<E>) ctor.newInstance(size);
		return array;
	}
	public static <E> SinglyLinkedList<E> getSinglyLinkedList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("slist"));
		@SuppressWarnings("unchecked")
		SinglyLinkedList<E> sllist = (SinglyLinkedList<E>) clazz.newInstance();
		return sllist;
	}
	public static <E> DoublyLinkedList<E> getDoublyLinkedList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("dlist"));
		@SuppressWarnings("unchecked")
		DoublyLinkedList<E> dllist = (DoublyLinkedList<E>) clazz.newInstance();
		return dllist;
	}
	public static <E> Queue<E> getQueue() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("queue"));
		@SuppressWarnings("unchecked")
		Queue<E> queue = (Queue<E>) clazz.newInstance();
		return queue;
	}
	public static <E> Stack<E> getStack() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("stack"));
		@SuppressWarnings("unchecked")
		Stack<E> stack = (Stack<E>) clazz.newInstance();
		return stack;
	}
	public static <E> Deque<E> getDeque() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("deque"));
		@SuppressWarnings("unchecked")
		Deque<E> deque = (Deque<E>) clazz.newInstance();
		return deque;
	}
	public static <E> Sequence<E> getSequence() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("sequence"));
		@SuppressWarnings("unchecked")
		Sequence<E> sequence = (Sequence<E>) clazz.newInstance();
		return sequence;
	}
	public static <E> java.util.Iterator<E> getIterator(Sequence<E> sequence) throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("iterator"));
		Constructor<?> ctor = clazz.getConstructor(Sequence.class);
		@SuppressWarnings("unchecked")
		java.util.Iterator<E> iterator = (java.util.Iterator<E>) ctor.newInstance(sequence);
		return iterator;
	}
	public static <E> List<E> getList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("list"));
		@SuppressWarnings("unchecked")
		List<E> list = (List<E>) clazz.newInstance();
		return list;
	}
	public static <E extends Comparable<E>> SortedList<E> getSortedList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("sortedlist"));
		@SuppressWarnings("unchecked")
		SortedList<E> sortedList = (SortedList<E>) clazz.newInstance();
		return sortedList;
	}
	public static <E> Set<E> getSet() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("set"));
		@SuppressWarnings("unchecked")
		Set<E> set = (Set<E>) clazz.newInstance();
		return set;
	}
	public static <K,E> Map<K,E> getMap() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("map"));
		@SuppressWarnings("unchecked")
		Map<K,E> map = (Map<K,E>) clazz.newInstance();
		return map;
	}
}
