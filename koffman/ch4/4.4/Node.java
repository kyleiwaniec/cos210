private static class Node<E>{
	private E data;
	private Node next = null;
	private Node prev = null;

	private Node(E dataItem){
		data = dataItem;
	}
}