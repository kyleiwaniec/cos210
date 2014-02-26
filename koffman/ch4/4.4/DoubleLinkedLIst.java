import java.util.*;

public class SingleLinkedList<E>{
	private Node<E> head = null;

	public void addFirst(E item){
		head = new Node<E>(item, head);
	}
	public void addLast(E item){
	}
	public void addBefore(Node<E> oldItem, Node<E> newItem){
		
	}
	public void addAfter(Node<E> oldItem, Node<E> newItem){
		newItem.next = oldItem.next;
		oldItem.next = newItem;
	}
	public void remove(Node<E> item){
	}
	public void replace(Node<E> item){
	}
}