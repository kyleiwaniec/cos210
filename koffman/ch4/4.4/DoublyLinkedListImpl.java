public class DoublyLinkedListImpl<E> implements DoublyLinkedList<E> {
	private DNode<E> head;
	private DNode<E> tail;
	private DNode<E> current;
	private int size = 0;
	public DoublyLinkedListImpl() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	@Override
	public E getFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.head.element;
	}
	@Override
	public E getLast() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	@Override
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
	@Override
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
	@Override 
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element);
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
	@Override 
	public void addLast(E element) {
		if (this.size == 0) { // or, check: this.tail == null
			addFirst(element);
		} else {
			DNode<E> node = new DNode<E>(element);

			node.previous = this.tail;
			node.next = null;

			this.tail.next = node;
			this.tail = node;
			this.current = node;
			this.size++;
		}

	}
	@Override 
	public void addNext(E element) {
		if (this.size == 0) {
			addFirst(element);
		}else if(this.current == this.tail){
			addLast(element);
		}else{
			DNode<E> node = new DNode<E>(element);

			node.previous = this.current;
			node.next = this.current.next;
			
			this.current.next.previous = node;
			this.current.next = node;

			this.current = node;
			this.size++;
		}
	}
	@Override
	public void addPrevious(E element) {
		
		if(this.current == this.head){
			throw new java.util.NoSuchElementException();
		}else{
			DNode<E> node = new DNode<E>(element);
			node.next = this.current;
			node.previous = this.current.previous;


			this.current.previous.next = node;
			this.current.previous = node;
			this.current = node;
			this.size++;
		}

	}
	@Override 
	public void removeFirst() throws java.util.NoSuchElementException {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		

		if(this.size == 1){
			this.current = null;
			this.head = null;
		}else{
			this.current = this.head.next;
			this.head.next.previous = null
		}
		
		DNode<E> node = this.head;
		this.head = node.next;
		node.reset();

		this.size--;

		if(this.size == 1){
			this.tail = this.current;
		}
		
	} 
	@Override
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

		DNode<E> node = this.tail;
		this.tail = node.previous;

		node.reset();
		
		this.size--;

	}
	@Override //todo
	public void removeNext() {
		if (this.size == 0) {
			throw new java.util.NoSuchElementException();
		}





	}
	@Override //todo
	public void removePrevious() {
	}
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("< ");
		DNode<E> node = head;
		while (node != null) {
			if (node.element != null) {
				sb.append(node.element.toString() + " ");
			} else {
				sb.append("null ");
			}
			node = node.next;
		}
		sb.append(">");
		return sb.toString();
	}
	private class DNode<T> {
		private T element;
		private DNode<T> next = null;
		private DNode<T> previous = null;
		public DNode(T element) {
			this.element = element;
		}
		private void reset() {
			this.element = null;
			this.next = null;
			this.previous = null;
		}
	}
}
