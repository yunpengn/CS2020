package sg.edu.nus.cs2020;

import java.util.Iterator;

public class WootLinkedList<T> implements Iterable<T> {
	private class WootLinkedListNode {
		private T value;
		private WootLinkedListNode before;
		private WootLinkedListNode after;
	}
	
	private class WootIterator implements Iterator<T> {
		WootLinkedListNode current= head;
		
		public boolean hasNext() {
			return current.after != null;
		}

		public T next() {
			T value = current.value;
			current = current.after;
			
			return value;
		}
	}
	
	private WootLinkedListNode head;
	private WootLinkedListNode tail;
	private int size;
	
	public WootLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public Iterator<T> iterator() {
		return new WootIterator();
	}
	
	public void suspend(T x) {
		WootLinkedListNode now = new WootLinkedListNode();
		now.value = x;
		now.before = tail;
		
		if (size == 0) {
			head = now;
		} else {
			tail.after = now;
		}
		
		now.after = null;
		tail = now;
		size++;
	}
}
