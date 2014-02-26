/*<listing chapter="7" section="4">*/
package KW.CH07;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Hash table implementation using open addressing.
 * @author Koffman and Wolfgang
 */
public class HashtableOpen<K, V> extends AbstractMap<K, V>
        implements KWHashMap<K, V> {
    // Data Fields

    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;
    private double LOAD_THRESHOLD = 0.75;
    private int numKeys;
    private int numDeletes;
    private final Entry<K, V> DELETED =
            new Entry<K, V>(null, null);
// Insert solution to programming exercise 6, section 4, chapter 7 here

    // Constructor
    public HashtableOpen() {
        table = new Entry[START_CAPACITY];
    }

    /*<listing chapter="7" number="3">*/
    /** Contains key-value pairs for a hash table. */
    public static class Entry<K, V> implements Map.Entry<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

// Insert solution to programming exercise 3, section 4, chapter 7 here
    }
    /*</listing>*/

// Insert solution to programming exercise 5, section 4, chapter 7 here

    /** Returns true if empty */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /*<listing chapter="7" number="4">*/
    /**
     * Finds either the target key or the first empty slot in the
     * search chain using linear probing.
     * @pre The table is not full.
     * @param key The key of the target object
     * @return The position of the target or the first empty slot if
     *         the target is not in the table.
     */
    private int find(Object key) {
// Insert solution to programming exercise 6, section 4, chapter 7 here
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length; // Make it positive.
        }
        // Increment index until an empty slot is reached
        // or the key is found.
        while ((table[index] != null)
                && (!key.equals(table[index].key))) {
// Insert solution to programming exercise 6, section 4, chapter 7 here
            index++;
            // Check for wraparound.
            if (index >= table.length) {
                index = 0; // Wrap around.
            }
        }
        return index;
    }
    /*</listing>*/

    /*<listing chapter="7" number="5">*/
    /**
     * Method get for class HashtableOpen.
     * @param key The key being sought
     * @return the value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V get(Object key) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if (table[index] != null) {
            return table[index].value;
        } else {
            return null; // key not found.
        }
    }
    /*</listing>*/

    /*<listing chapter="7" number="6">*/
    /**
     * Method put for class HashtableOpen.
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed. If the LOAD_THRESHOLD
     *       is exceeded, the table is expanded.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return Old value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);

        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<K, V>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor =
                    (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD) {
                rehash();
            }
            return null;
        }

        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].value;
        table[index].value = value;
        return oldVal;
    }
    /*</listing>*/

    /*<listing chapter="7" number="7">*/
    /**
     * Expands table size when loadFactor exceeds LOAD_THRESHOLD
     * @post The size of table is doubled and is an odd integer.
     *       Each nondeleted entry from the original table is
     *       reinserted into the expanded table.
     *       The value of numKeys is reset to the number of items
     *       actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }
    /*</listing>*/

// Insert solution to programming exercise 1, section 4, chapter 7 here

    /*<listing chapter="7" number="11">*/
    /**
     * Returns a set view of the entries in the Map
     * @return a Set view of the entries in the Map
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    /** Inner class to implement the set view. */
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        /** Return the size of the set. */
        @Override
        public int size() {
            return numKeys;
        }

        /** Return an iterator over the set. */
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }
    }
    /*</listing>*/

// Insert solution to programming project 6, chapter -1 here

// Insert solution to programming exercise 1, section 3, chapter 7 here

// Insert solution to programming exercise 3, section 4, chapter 7 here

// Insert solution to programming exercise 6, section 4, chapter 7 here

}
/*</listing>*/
