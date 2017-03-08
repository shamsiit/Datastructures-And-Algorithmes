package DS;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
	
	private Node first;
	private int count;
	
	private class Node{
		T item;
		Node next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public int size(){
		return count;
	}
	
	public void push(T item){
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		count++;
	}
	
	public T pop(){
		T item = first.item;
		first = first.next;
		count--;
		
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T>{

		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
