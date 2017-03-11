package DS;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
	
	private Node first;
	private Node last;
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
	
	public void enqueue(T item){
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else{
			oldLast.next = last;
		}
		
		count++;
	}
	
	public T dequeue(){
		T item = first.item;
		first = first.next;
		count--;
		if(isEmpty()){
			last = null;
		}
		
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
			
		}
		
	}

}
