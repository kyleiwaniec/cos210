  class Entry {
    Object key;
    Object value;
    final int hash;
    Entry next;

    /**
    * Create new entry.
    */
    Entry(int h, Object k, Object v, Entry n) { 
      value = v; 
      next = n;
      key = k;
      hash = h;
    }

  /* Returns a hash value for the specified object.  */
  static int hash(Object x) {
    int h = x.hashCode();
    return h;
  }

  /* Returns index for hash code h. */
  static int indexFor(int h, int length) {
    return h & (length-1);
  }

  /* Returns the value to which the specified key is mapped in this identity
     hash map, or null if the map contains no mapping for this key. */
  public Object get(Object key) {
    int hash = hash(key);
    int i = indexFor(hash, table.length);
    Entry e = table[i]; 
    while (true) {
      if (e == null)
        return e;
      if (e.hash == hash && eq(k, e.key)) 
        return e.value;
      e = e.next;
    }
  }

  /**
  * Associates the specified value with the specified key in this hash table.
  * If the hash table previously contained a mapping for this key, the old
  * value is replaced.
  */
  public Object put(Object key, Object value) {
    int hash = hash(key);
    int i = indexFor(hash, table.length);

    for (Entry e = table[i]; e != null; e = e.next) {
      if (e.hash == hash && eq(k, e.key)) {
        Object oldValue = e.value;
        e.value = value;
        return oldValue;
      }
    }
    addEntry(hash, k, value, i);
    return null;
  }

  /**
  * Add a new entry with the specified key, value and hash code to
  * the specified bucket. It is the responsibility of this 
  * method to resize the table if appropriate.
  */
  void addEntry(int hash, Object key, Object value, int bucketIndex) {
    table[bucketIndex] = new Entry(hash, key, value, table[bucketIndex]);
    if (size++ >= threshold) 
      resize(2 * table.length);
  }

  /**
  * Removes the mapping for this key from this hash table if present.
  */
  public Object remove(Object key) {
    Entry e = removeEntryForKey(key);
    return (e == null ? e : e.value);
  }

  /**
  * Removes and returns the entry associated with the specified key
  * in the Hash Table. Returns null if the Hash Table contains no mapping
  * for this key.
  */
  Entry removeEntryForKey(Object key) {
    int hash = hash(key);
    int i = indexFor(hash, table.length);
    Entry prev = table[i];
    Entry e = prev;

    while (e != null) {
      Entry next = e.next;
      if (e.hash == hash && eq(k, e.key)) {
        size--;
        if (prev == e) 
          table[i] = next;
        else
          prev.next = next;
        return e;
      }
      prev = e;
      e = next;
    }
    return e;
  }

  /**
  * Rehashes the contents of this hash table into a new array with a
  * larger capacity. This method is called automatically when the
  * number of keys in this hash table reaches its threshold.
  */
  void resize(int newCapacity) {
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {
      threshold = Integer.MAX_VALUE;
      return;
    }

    Entry[] newTable = new Entry[newCapacity];
    transfer(newTable);
    table = newTable;
    threshold = (int)(newCapacity * loadFactor);
  }

  /** 
  * Transfer all entries from current table to newTable.
  */
  void transfer(Entry[] newTable) {
    Entry[] src = table;
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) {
      Entry e = src[j];
      if (e != null) {
        src[j] = null;
        do {
          Entry next = e.next;
          int i = indexFor(e.hash, newCapacity); 
          e.next = newTable[i];
          newTable[i] = e;
          e = next;
        } while (e != null);
      }
    }
  }

  /* Each entry stores a (key, value) pair, it's hash value and
   * a reference to the next entry with the same hash value */
  
  }