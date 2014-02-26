/*<listing chapter="7" section="5">*/
package KW.CH07;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A hash table for storing set elements using open addressing.
 * @author Koffman and Wolfgang
 **/
public class HashSetOpen<K> extends AbstractSet<K> {

    private KWHashMap<K, K> setMap = new HashtableOpen<K, K>();

    /**
     * Adapter method contains.
     * @return true if the key is found in setMap
     */
    @Override
    public boolean contains(Object key) {
        // HashtableOpen.get returns null if the key is not found.
        return (setMap.get(key) != null);
    }

    /**
     * Adapter method add.
     * @post Adds a new Entry object (key, key)
     * if key is not a duplicate.
     * @return true if the key is not a duplicate
     */
    @Override
    public boolean add(K key) {
        /* HashtableOpen.put returns null if the
        key is not a duplicate. */
        return (setMap.put(key, key) == null);
    }

    /**
     * Adapter method remove.
     * @post Removes the key-value pair (key, key).
     * @return true if the key is found and removed
     */
    @Override
    public boolean remove(Object key) {
        // HashtableOpen.remove returns null if the
        // key is not removed. */
        return (setMap.remove(key) != null);
    }

    /**
     * Adapter method size.
     * @return the size of the set
     */
    @Override
    public int size() {
        return setMap.size();
    }

    /**
     *  Adapter class for the Iterator
     */
    private static class Itr<K> implements Iterator<K> {

        Iterator<Map.Entry<K, K>> itr;

        public Itr(Iterator<Map.Entry<K, K>> itr) {
            this.itr = itr;
        }

        @Override
        public boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public K next() {
            return itr.next().getKey();
        }

        @Override
        public void remove() {
            itr.remove();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Itr<K>(((HashtableOpen) setMap).entrySet().iterator());
    }

// Insert solution to programming exercise 2, section 1, chapter 7 here
}
