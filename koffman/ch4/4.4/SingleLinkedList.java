import java.util.*;

public class SingleLinkedList<E>{
	private Node<E> head = null;
	public void addFirst(E item){
		head = new Node<E>(item, head);
	}
	public void insertAfter(Node<E> oldItem, Node<E> newItem){
		newItem.next = oldItem.next;
		oldItem.next = newItem;
	}
	public void removeNext(Node<E> item){
		item.next = item.next.next;
	}

}