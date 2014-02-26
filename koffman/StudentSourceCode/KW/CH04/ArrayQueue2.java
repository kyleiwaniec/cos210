package KW.CH04;

import java.util.*;

/**
 * Implements the Queue interface using a circular array.
 * @author Koffman & Wolfgang
 **/
public class ArrayQueue2<E> extends AbstractQueue<E>
        implements Queue<E> {

    // Data Fields
    /** Index of the front of the queue. */
    private int front;
    /** Index of the rear of the queue. */
    private int rear;
    /** Current size of the queue. */
    private int size;
    /** Current capacity of the queue. */
    private int capacity;
    /** Default capacity of the queue. */
    private static final int DEFAULT_CAPACITY = 10;
    /** Array to hold the data. */
    private E[] theData;

    // Constructors
    /** Construct a queue with the default initial capacity.
     */
    public ArrayQueue2() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Construct a queue with the specified initial capacity.
     * @param initCapacity The initial capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue2(int initCapacity) {
        capacity = initCapacity;
        theData = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }

    // Public Methods
    /**
     * Inserts an item at the rear of the queue.
     * @post: item is added to the rear of the queue.
     * @param item The element to add
     * @return true (always successful)
     */
    @Override
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        theData[rear] = item;
        return true;
    }

    /**
     * Returns the item at the front of the queue without removing it.
     * @return The item at the front of the queue if successful;
     * return null if the queue is empty
     */
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        } else {
            return theData[front];
        }
    }

    /**
     * Removes the entry at the front of the queue and returns it
     * if the queue is not empty.
     * @post front references item that was second in the queue.
     * @return The item removed if successful or null if not
     */
    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

//Exercise solution removed

    // Private Methods
    /**
     * Double the capacity and reallocate the data.
     * @pre The array is filled to capacity.
     * @post The capacity is doubled and the first half of the
     *       expanded array is filled with data.
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];
        int j = front;
// Insert solution to programming exercise 4, section 3, chapter 4 here
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        theData = newData;
    }

    /*<listing chapter="4" number="5">*/
    /** Inner class to implement the Iterator<E> interface. */
    private class Iter implements Iterator<E> {
        // Data Fields
        // Index of next element */

        private int index;
        // Count of elements accessed so far */
        private int count = 0;

        // Methods
        // Constructor
        /** Initializes the Iter object to reference the
        first queue element.
         */
        public Iter() {
            index = front;
        }

        /**
         * Returns true if there are more elements in the queue to access.
         * @return true if there are more elements in the queue to access.
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Returns the next element in the queue.
         * @pre index references the next element to access.
         * @post: index and count are incremented.
         * @return The element with subscript index
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = theData[index];
            index = (index + 1) % capacity;
            count++;
            return returnValue;
        }

        /**
         * Remove the item accessed by the Iter object -- not implemented.
         * @throws UnsupportedOperationException when called
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    /*</listing>*/
}
/*</listing>*/
