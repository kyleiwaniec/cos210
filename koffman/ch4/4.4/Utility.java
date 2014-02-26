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
	// public static <E> SinglyLinkedList<E> getSinglyLinkedList() throws Exception {
	// 	Class<?> clazz = Class.forName(properties.getProperty("slist"));
	// 	@SuppressWarnings("unchecked")
	// 	SinglyLinkedList<E> sllist = (SinglyLinkedList<E>) clazz.newInstance();
	// 	return sllist;
	// }
	public static <E> DoublyLinkedList<E> getDoublyLinkedList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("dlist"));
		@SuppressWarnings("unchecked")
		DoublyLinkedList<E> dllist = (DoublyLinkedList<E>) clazz.newInstance();
		return dllist;
	}
}
