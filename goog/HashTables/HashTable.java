package edu.mccc.cos210.ds;
import java.util.Iterator;
/**
 * Hash table implementation using chaining.
 **/
public class HashTable<K, V> implements HashMapInt<K, V> {

    /** The table */
    private DoublyLinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> implements MapEntryInt<K, V> {

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
        // toString method
        public String toString(){
            String result = "";
            result += this.key +" : "+this.value;
            return result;
        }
    }

    // Constructor
    public HashTable() {
        table = new DoublyLinkedList[CAPACITY];
    }

    public HashTable(int capcity) {
        table = new DoublyLinkedList[capcity];
    }
    /**
     * Method get for class HashTable.
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; // key is not in the table.
        }
        // Search the list at table[index] to find the key.
        // for (Entry<K, V> nextItem : table[index]) {
        //     if (nextItem.key.equals(key)) {
        //         return nextItem.value;
        //     }
        // }

        // TODO: ^^ REWRITE sans iterator
        


        Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        for(int i = 0; i < table[index].getSize(); i++){
            if (nextItem.key.equals(key)) {
                 return nextItem.value;
            }
            while(table[index].hasNext()){
                nextItem = (Entry<K, V>) table[index].getNext();
            }
        }
        
        return null;
    }
    // private int hashCode(){
    //     // TODO
    // }
    
    /**
     * Method put for class HashTable.
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if
     *         found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length; // THIS IS PRETTY AWFUL IF N IS NOT PRIME!! c = ((a*h + b) mod p) mod N)
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new DoublyLinkedList<Entry<K, V>>();
        }

        // Search the list at table[index] to find the key.
        // for (Entry<K, V> nextItem : table[index]) {
        //     // If the search is successful, replace the old value.
        //     if (nextItem.key.equals(key)) {
        //         // Replace value for this key.
        //         V oldVal = nextItem.value;
        //         nextItem.setValue(value);
        //         return oldVal;
        //     }
        // }

        // TODO: ^^ REWRITE sans iterator

    // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }

        return null;
    }
    public void getCurrent(){
        System.out.println(table[3].getCurrent());
    }

    public String toString(){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null){
                sb.append(", "+table[i].toString());
            }
        }
        if(sb.length()>=2){sb.delete(0,2);};
        return sb.toString();
    }

// Insert solution to programming exercise 5, section 4, chapter 7 here
    // getSize() method
    public int getSize(){
        return numKeys;
    }
    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

// Insert solution to programming exercise 2, section 4, chapter 7 here
    // rehash, and remove.
    public void rehash(){

    }
    /** removes all items with given key
    *   @return null.
    */
    public V remove(Object key){
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        int size = table[index].getSize();
        for(int i = 0; i < size; i++){
            System.out.println(table[index].getSize());
            if (nextItem.key.equals(key)) {
                  table[index].removeCurrent();
                  numKeys--;
            }
            while(table[index].hasNext()){
                nextItem = (Entry<K, V>) table[index].getNext();
            }

        }
        if(table[index].isEmpty()){
            table[index] = null;
        }
        return null;
    }


// Insert solution to programming project 7, chapter -1 here ????
}
