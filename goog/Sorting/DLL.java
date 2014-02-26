import java.util.*;
import java.util.Iterator;


public class DLL<E extends Comparable<E>> implements Iterable{
	public Node<E> head;
	public Node<E> tail;
	public Node<E> current;
	public int size = 0;

	public DLL() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	
	public E getFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head.element;
	}
	public Node<E> getHead(){
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head;
	}
	public E getLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	public E getNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.next;
		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	public boolean hasNext(){
		if (this.size == 0 || this.current == null) {
			return false;
		}
		if(this.current.next == null){
			return false;
		}
		return true;
	}
	public E getPrevious() {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.previous;

		if (this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	public E getCurrent() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			throw new java.util.NoSuchElementException();
		}
		E element = this.current.element;
		return element;
	}
	public void addFirst(E element) {
		Node<E> node = new Node<E>(element);
		if(this.head != null){
			
			node.next = this.head;
			node.previous = null;

			this.head.previous = node;
			this.head = node;

		}else{
			node.next = null;
			node.previous = null;
			this.tail = node;
			this.head = node;
		}
		this.current = node;
		this.size++;

	}
	public void addLast(E element) {
		if (this.size == 0) { // or, check: this.tail == null
			addFirst(element);
		} else {
			Node<E> node = new Node<E>(element);

			node.previous = this.tail;
			node.next = null;

			this.tail.next = node;
			this.tail = node;
			this.current = node;
			this.size++;
		}

	}
	public void addNext(E element) {
		if (this.size == 0) {
			addFirst(element);
		}else if(this.current == this.tail){
			addLast(element);
		}else{
			Node<E> node = new Node<E>(element);

			node.previous = this.current;
			node.next = this.current.next;
			
			this.current.next.previous = node;
			this.current.next = node;

			this.current = node;
			this.size++;
		}
	}
	public void addPrevious(E element) {
		
		if(this.current == this.head){
			throw new java.util.NoSuchElementException();
		}else{
			Node<E> node = new Node<E>(element);
			node.next = this.current;
			node.previous = this.current.previous;


			this.current.previous.next = node;
			this.current.previous = node;
			this.current = node;
			this.size++;
		}

	}
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		if(this.size == 1){
			this.current = null;
			this.head = null;
		}else{
			this.current = this.head.next;
			this.head.next.previous = null;
		}
		Node<E> node = this.head;
		this.head = node.next;
		node.reset();
		this.size--;
		if(this.size == 1){
			this.tail = this.current;
		}
	} 
	public void removeLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		if (size == 1) {
			this.current = null;
			this.head = null;
		} else {
			this.current = this.tail.previous;
			this.tail.previous.next = null;
		}
		Node<E> node = this.tail;
		this.tail = node.previous;
		node.reset();
		this.size--;
	}
	public void removeNext() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.next == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		Node<E> node = this.current.next;
		this.current.next = node.next;
		if (this.current.next != null) {
			this.current.next.previous = this.current;
		} else {
			this.tail = this.current;
			//this.current = null;
		}
		node.reset();
		this.size--;
	}
	public void removePrevious() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null || this.current.previous == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		Node<E> node = this.current.previous;
		this.current.previous = node.previous;
		if (this.current.previous != null) {
			this.current.previous.next = this.current;
		} else {
			this.head = this.current;
			//this.current = null;
		}
		node.reset();
		this.size--;
	}

	public void removeCurrent() throws java.util.NoSuchElementException {
		if (this.size == 0 || this.current == null) {
			this.current = null;
			throw new java.util.NoSuchElementException();
		}
		if(this.current.next == null){
			removeLast();
		}else if (this.current.previous == null) {
			removeFirst();
		} else {
			Node<E> node = this.current;
			this.current.next.previous = this.current.previous;
			this.current.previous.next = this.current.next;
			this.current = this.current.next;
			node.reset();
			this.size--;
		}
	}
	public int getSize() {
		return this.size;
	}
	public boolean isEmpty() {
		return this.size == 0;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		Node<E> node = this.head;
		while (node != null) {
			if (node.element != null) {
				sb.append(", "+node.element.toString());
			} else {
				sb.append("null ");
			}
			node = node.next;
		}
		if(sb.length()>=2){sb.deleteCharAt(1);};
		sb.append(" >");
		return sb.toString();
	}
	public class Node<T extends Comparable<T>>{
		public T element;
		public Node<T> next = null;
		public Node<T> previous = null;
		public Node(){};
		public Node(T element) {
			this.element = element;
		}
		public void reset() {
			this.element = null;
			this.next = null;
			this.previous = null;
		}
	}
	
	public boolean contains(E el){
		Node<E> node = head;
		while (node != null) {
			if (node.element != null) {
				 if(el.equals(node.element)){return true;};
			}
			node = node.next;
		}
		return false;
	}

	public Node<E> merge(Node<E>  a, Node<E>  b){
		Node<E> dummy = new Node<E>();
		Node<E> head = dummy;
		Node<E> c = head;
		while ((a != null) && (b != null)) {
			if (a.element.compareTo(b.element) < 0 || a.element.equals(b.element)){
				c.next = a; 
				c = a; 
				a = a.next;
			}else{
				c.next = b; 
				c = b; 
				b = b.next;
			}	
		}
		c.next = (a == null) ? b : a;
		return head.next;

		// Node<E> l = new Node<E>();
		// if(a.element.compareTo(b.element) < 0 || a.element.equals(b.element)){
		// 	l = a;
		// 	a = a.next;
		// }else{
		// 	l = b;
		// 	b = b.next;
		// }
		// Node<E> prev = l;
		// while(prev != null && prev.next != null){
		// 	if(a.element.compareTo(b.element) < 0 || a.element.equals(b.element)){
		// 		prev = prev.next = a;
		// 		a = a.next;
		// 	}else{
		// 		prev = prev.next = b;
		// 		b = b.next;
		// 	}
			
		// }
		// return l;

	}

	public Node<E> mergeSort(Node<E> c){
		if (c == null || c.next == null) {
			return c;
		}
		Node<E> a = c, b = c.next;
		while ((b != null) && (b.next != null)) {
			c = c.next; 
			b = b.next.next;
		}
		b = c.next; 
		c.next = null;

		a = mergeSort(a);
		b = mergeSort(b);

		return merge(a, b);
	}

	public String nodeToString(Node<E> n){
		StringBuilder sb = new StringBuilder();
		while(n != null){
			sb.append(", "+n.element.toString());
			n = n.next;
		}
		return sb.toString();
	}

	@Override
	public Iterator<E> iterator(){
		return new Iter();
	};
	
	private class Iter extends DLL<E> implements Iterator<E> {
        // Data Fields
        // Index of next element */

        private int index;
        // Count of elements accessed so far */
        private int count = 0;

        // Methods
        // Constructor
        /**
         * Initializes the Iter object to reference the
         * first queue element.
         */
        public Iter() {
           count = 0;
           DLL.this.current = DLL.this.head;
        }

        /**
         * Returns true if there are more elements in the queue to access.
         * @return true if there are more elements in the queue to access.
         */
        @Override
        public boolean hasNext() {
            return count < DLL.this.getSize();
        }

        /**
         * Returns the next element in the queue.
         * @pre index references the next element to access.
         * @post index and count are incremented.
         * @return The element with subscript index
         */
        @Override
        public E next() {
        	
			count++;
			E element = DLL.this.current.element;
			DLL.this.current = DLL.this.current.next;
			return element;
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

}
