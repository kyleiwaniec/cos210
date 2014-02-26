import java.util.*;
import java.io.*;

// deals with duplicate hashcodes, but not duplicate entries(keys).
// Open addressing ??
public class Table {
  private int manyItems;
  private Object[] keys;
  private Object[] data;
  private boolean[] hasBeenUsed;

  public Table(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException(
                "Capacity is negative.");
    keys = new Object[capacity];
    data = new Object[capacity];
    hasBeenUsed = new boolean[capacity];
  }

  // compute hash code and convert it to a valid index
  private int hash(Object key)
  {
    return Math.abs(key.hashCode()) % data.length;
  }

  // if the key is found,
  // return the index of the key in the keys array
  // otherwise, return -1
  private int findIndex(Object key) 
  {
    int count = 0;
    int i = hash(key);
    while (count < data.length && hasBeenUsed[i])
    {
      if (key.equals(keys[i]))
        return i;
      count++;
      i = nextIndex(i);
    }
    return -1;
  }

  private int nextIndex(int i)
  {
    if (i+1 == data.length) // npt checking against nul, becuase there could be null values in the table
      return 0;
    else return i+1;
  }

  // if the key is found
  // return a copy of the object with the specified key
  // otherwise, return null
  public Object get(Object key)
  {
    int index = findIndex(key);
    if (index == -1)
      return null;
    else 
      return data[index];
  }

  // put the new element in the table with the specified key
  // if the key was already present in the table,
  // then returns a reference to the old element (has been replaced)
  // otherwise, return null
  public Object put(Object key, Object element) 
  {
    int index = findIndex(key);
    Object answer;
    if (index != -1) {
      answer = data[index];
      data[index] = element;
      return answer;
    } else if (manyItems < data.length) { // table not full
      index = hash(key);
      while (keys[index] != null)
        index = nextIndex(index);
      keys[index] = key;
      data[index] = element;
      hasBeenUsed[index] = true;
      manyItems++;
      return null;
    } else 
      throw new IllegalStateException("Table is full."); 
  }

  public Object remove(Object key)
  { 
    int index = findIndex(key);
    Object answer = null;
    if (index != -1) {
      answer = data[index];
      keys[index] = null;
      data[index] = null;
      manyItems--;
    }
    return answer;
  }

  // show each (key, value) pair in the hash table
  public void showTable()
  {
    int i;
    for (i = 0; i < keys.length; i++) {
      if (keys[i] != null)
        System.out.println("(" + keys[i] + ", " + data[i] + ")");
    }
  }

  public static void main(String[] args) {
    Table phoneBook = new Table(100);
    phoneBook.put("Albert", "111-1111");
    phoneBook.put("Brian", "222-2222");
    phoneBook.put("Candy", "333-3333");
    phoneBook.put("Candy", "333-7777");
    phoneBook.showTable();
    System.out.println("Brian's phone number: " + 
                      (String) phoneBook.get("Brian"));

    System.out.println("remove Brian ..." +
                       (String) phoneBook.remove("Brian"));

    System.out.println("update Albert .. new phone number 999-9999");
    phoneBook.put("Albert", "999-9999");
    phoneBook.showTable();
  }
}
