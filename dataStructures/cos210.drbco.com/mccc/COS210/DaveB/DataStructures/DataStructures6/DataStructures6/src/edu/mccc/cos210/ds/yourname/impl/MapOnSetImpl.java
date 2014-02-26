package edu.mccc.cos210.ds.yourname.impl;
import java.util.Iterator;
import edu.mccc.cos210.ds.Map;
import edu.mccc.cos210.ds.Set;
import edu.mccc.cos210.ds.Utility;
public class MapOnSetImpl<K,V> implements Map<K,V> {
	private Set<Map.Entry<K,V>> entries;
	public MapOnSetImpl() {
		try {
			this.entries = Utility.getSet();
		} catch (Exception e) {
			System.err.println("Unable to create Map");
		}
	}
	@Override
	public V get(K key) {
		return null;
	}
	@Override
	public V put(K key, V value) {
		return null;
	}
	@Override
	public boolean containsKey(K key) {
		return false;
	}
	@Override
	public boolean containsValue(V value) {
		return false;
	}
	@Override
	public Set<K> getKeys() {
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
	public String toString() {
		return "";
	}
	public static class EntryImpl<K,V> implements Map.Entry<K,V> {
		public EntryImpl() {
		}
		@Override
		public K getKey() {
			return null;
		}
		@Override
		public V getValue() {
			return null;
		}
		@Override
		public V setValue(V value) {
			return null;
		}
		@Override
		public boolean equals(Object o) { // equal if key is equal, ignore value
			return false;
		}
		@Override
		public int hashCode() { // if equal then hashCode must be the same
			return -1;
		}
		@Override
		public String toString() {
			return "";
		}
	}
}
