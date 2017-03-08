package DS;

import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {
	
	private T[] a = (T[]) new Object[1];
	private int count = 0;
	
	public boolean isEmpty(){
		return count == 0;
	}
	
	public int size(){
		return count;
	}
	
	public void resize(int size){
		T[] temp = (T[]) new Object[size];
		for(int i=0;i<count;i++){
			temp[i] = a[i];
		}
		
		a = temp;
	}
	
	public void push(T item){
		if(count == a.length){
			resize(2*a.length);
		}
		
		a[count++] = item;
	}
	
	public T pop(){
		T item = a[--count];
		a[count] = null;
		if(count>0 && count == a.length/4){
			resize(a.length/2);
		}
		
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<T>{

		private int i = count;
		
		@Override
		public boolean hasNext() {
			return i>0;
		}

		@Override
		public T next() {
			return a[--i];
		}

		@Override
		public void remove() {
			
		}
		
	}

}
