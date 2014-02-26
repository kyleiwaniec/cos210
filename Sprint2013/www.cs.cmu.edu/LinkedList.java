/***************************************************************************
 * A Linked List class with a private static inner Node class.
 *
 *****************************************************************************/

import java.util.*;

public class LinkedList<AnyType> implements Iterable<AnyType>
{
   private Node<AnyType> head;

 /**
   *  Constructs an empty list
   */
   public LinkedList()
   {
      head = null;
   }
 /**
   *  Returns true if the list is empty
   *
   */
   public boolean isEmpty()
   {
      return head == null;
   }
 /**
   *  Inserts a new node at the beginning of this list.
   *
   */
   public void addFirst(AnyType item)
   {
      head = new Node<AnyType>(item, head);
   }
 /**
   *  Returns the first element in the list.
   *
   */
   public AnyType getFirst()
   {
      if(head == null) throw new NoSuchElementException();

      return head.data;
   }
 /**
   *  Removes the first element in the list.
   *
   */
   public AnyType removeFirst()
   {
      AnyType tmp = getFirst();
      head = head.next;
      return tmp;
   }
 /**
   *  Inserts a new node to the end of this list.
   *
   */
   public void addLast(AnyType item)
   {
      if( head == null)
         addFirst(item);
      else
      {
         Node<AnyType> tmp = head;
         while(tmp.next != null) tmp = tmp.next;

         tmp.next = new Node<AnyType>(item, null);
      }
   }
 /**
   *  Returns the last element in the list.
   *
   */
   public AnyType getLast()
   {
      if(head == null) throw new NoSuchElementException();

      Node<AnyType> tmp = head;
      while(tmp.next != null) tmp = tmp.next;

      return tmp.data;
   }
 /**
   *  Removes all nodes from the list.
   *
   */
   public void clear()
   {
      head = null;
   }
 /**
   *  Returns true if this list contains the specified element.
   *
   */
   public boolean contains(AnyType x)
   {
      for(AnyType tmp : this)
         if(tmp.equals(x)) return true;

      return false;
   }
 /**
   *  Returns the data at the specified position in the list.
   *
   */
   public AnyType get(int pos)
   {
      if (head == null) throw new IndexOutOfBoundsException();

      Node<AnyType> tmp = head;
      for (int k = 0; k < pos; k++) tmp = tmp.next;

      if( tmp == null) throw new IndexOutOfBoundsException();

      return tmp.data;
   }
 /**
   *  Returns a string representation
   *
   */
   public String toString()
   {
      StringBuffer result = new StringBuffer();
      for(Object x : this)
      	result.append(x + " ");

      return result.toString();
   }
 /**
   *  Inserts a new node after a node containing the key.
   *
   */
   public void insertAfter(AnyType key, AnyType toInsert)
   {
      Node<AnyType> tmp = head;

      while(tmp != null && !tmp.data.equals(key)) tmp = tmp.next;

      if(tmp != null)
         tmp.next = new Node<AnyType>(toInsert, tmp.next);
   }
 /**
   *  Inserts a new node before a node containing the key.
   *
   */
   public void insertBefore(AnyType key, AnyType toInsert)
   {
      if(head == null) return;

      if(head.data.equals(key))
      {
         addFirst(toInsert);
         return;
      }

      Node<AnyType> prev = null;
      Node<AnyType> cur = head;

      while(cur != null && !cur.data.equals(key))
      {
         prev = cur;
         cur = cur.next;
      }
      //insert between cur and prev
      if(cur != null)
         prev.next = new Node<AnyType>(toInsert, cur);
   }
 /**
   *  Removes the first occurrence of the specified element in this list.
   *
   */
   public void remove(AnyType key)
   {
      if(head == null)
         throw new RuntimeException("cannot delete");

      if( head.data.equals(key) )
      {
         head = head.next;
         return;
      }

      Node<AnyType> cur  = head;
      Node<AnyType> prev = null;

      while(cur != null && !cur.data.equals(key) )
      {
         prev = cur;
         cur = cur.next;
      }

      if(cur == null)
         throw new RuntimeException("cannot delete");

      //delete cur node
      prev.next = cur.next;
   }
 /**
   *  Returns a deep copy of the list
   *  Complexity: O(n^2)
   */
   public  LinkedList<AnyType> copy1()
   {
      LinkedList<AnyType> twin = new LinkedList<AnyType>();
      Node<AnyType> tmp = head;
      while(tmp != null)
      {
         twin.addLast( tmp.data );
         tmp = tmp.next;
      }

      return twin;
   }
 /**
   *  Returns a deep copy of the list
   *  Complexity: O(n)
   */
   public LinkedList<AnyType> copy2()
   {
      LinkedList<AnyType> twin = new LinkedList<AnyType>();
      Node<AnyType> tmp = head;
      while(tmp != null)
      {
         twin.addFirst( tmp.data );
         tmp = tmp.next;
      }

      return twin.reverse();
   }
 /**
   *  Reverses the list
   *  Complewxity: O(n)
   */
   public LinkedList<AnyType> reverse()
   {
      LinkedList<AnyType> list = new LinkedList<AnyType>();
      Node<AnyType> tmp = head;
      while(tmp != null)
      {
         list.addFirst( tmp.data );
         tmp = tmp.next;
      }
      return list;
   }
 /**
   *  Returns a deep copy of the immutable list
   *  It uses a tail reference.
   *  Complexity: O(n)
   */
   public LinkedList<AnyType> copy3()
   {
      LinkedList<AnyType> twin = new LinkedList<AnyType>();
      Node<AnyType> tmp = head;
      if(head==null) return null;
      twin.head = new Node<AnyType>(head.data, null);
      Node<AnyType> tmpTwin = twin.head;
      while(tmp.next != null)
      {
         tmp = tmp.next;
         tmpTwin.next = new Node<AnyType>(tmp.data, null);
         tmpTwin = tmpTwin.next;
      }

      return twin;
   }

 /*******************************************************
 *
 *  The Node class
 *
 ********************************************************/
   private static class Node<AnyType>
   {
      private AnyType data;
      private Node<AnyType> next;

      public Node(AnyType data, Node<AnyType> next)
      {
         this.data = data;
         this.next = next;
      }
   }

 /*******************************************************
 *
 *  The Iterator class
 *
 ********************************************************/

   public Iterator<AnyType> iterator()
   {
      return new LinkedListIterator();
   }

   private class LinkedListIterator  implements Iterator<AnyType>
   {
      private Node<AnyType> nextNode;

      public LinkedListIterator()
      {
         nextNode = head;
      }

      public boolean hasNext()
      {
         return nextNode != null;
      }

      public AnyType next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         AnyType res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }



/*****   Include the main() for testing and debugging  *****/


   public static void main(String[] args)
   {
      LinkedList<String> list = new LinkedList <String>();
      list.addFirst("p");
      list.addFirst("a");
      list.addFirst("e");
      list.addFirst("h");
      System.out.println(list);

		LinkedList<String> twin = list.copy3();
      System.out.println(twin);

      System.out.println(list.get(0));
//		System.out.println(list.get(4));   //exception

      list.addLast("s");
      Iterator itr = list.iterator();
      while(itr.hasNext())
         System.out.print(itr.next() + " ");
      System.out.println();

      for(Object x : list)
         System.out.print(x + " ");
      System.out.println();

      list.insertAfter("e", "ee");
      System.out.println(list);
      System.out.println(list.getLast());

      list.insertBefore("h", "yy");
      System.out.println(list);

      list.remove("p");
      System.out.println(list);


	}
}