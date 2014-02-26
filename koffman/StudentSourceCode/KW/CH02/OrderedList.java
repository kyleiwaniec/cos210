/*<listing chapter="2" section="10">*/
package KW.CH02;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;

/**
 * A class to represent an ordered list. The data is stored in a linked
 *  list data field.
 *  @author Koffman & Wolfgang
 */
public class OrderedList<E extends Comparable<E>>
     implements Iterable<E> {

    /** A linked list to contain the data. */
    private LinkedList<E> theList = new LinkedList<E>();

    public OrderedList() {
        theList = new LinkedList<E>();
    }

    /**
     * Insert obj into the list preserving the list's order.
     * @pre The items in the list are ordered.
     * @post obj has been inserted into the list
     *       such that the items are still in order.
     * @param obj The item to be inserted
     */
    public void add(E obj) {
        ListIterator<E> iter = theList.listIterator();
        // Find the insertion position and insert.
        while (iter.hasNext()) {
            if (obj.compareTo(iter.next()) < 0) {
                // Iterator has stepped over the first element
                // that is greater than the element to be inserted.
                // Move the iterator back one.
                iter.previous();
                // Insert the element.
                iter.add(obj);
                // Exit the loop and return.
                return;
            }
        }
        // assert: All items were examined and no item is larger than
        // the element to be inserted.
        // Add the new item to the end of the list.
        iter.add(obj);
    }

// Insert solution to programming exercise 2, section 10, chapter 2 here

    /**
     * Returns the element at the specified position.
     * @param index The index of the specified position
     * @return The element at position index
     */
    E get(int index) {
        return theList.get(index);
    }

// Insert solution to programming exercise 1, section 10, chapter 2 here
}
/*</listing>*/
