package edu.mccc.cos210.ds;
public interface Map<K,V> {
	V get(K key);
	V put(K key, V value); // no nulls, returns previous value or null if none
	boolean containsKey(K key);
	boolean containsValue(V value);
	Set<K> getKeys();
	int getSize();
	boolean isEmpty();
	public static interface Entry<K,V> {
		K getKey();
		V getValue();
		V setValue(V value); // no nulls, returns previous value or null if none
		boolean equals(Object o); // equal if key is equal, ignore value
		int hashCode(); // if equal then hashCode must be the same
	}
}
