package sg.edu.nus.cs2020;

import java.util.Iterator;

/**
 * @author ary
 */
public class WootLinkedList<T> implements Iterable<T> {

	private WootLinkedListNode<T> start;
	private WootLinkedListNode<T> end;
	
	// Constructor
	WootLinkedList() {
		start = end = null;
	}

	public void insertEnd(T value) {
		// Case 1: Single Node
		if (start == null) {
			start = end = new WootLinkedListNode<T>(value);
		// Case 2: Create new node, update pointers and set it as the end node
		} else {
			WootLinkedListNode<T> newNode = new WootLinkedListNode<T>(value);
			end.setNext(newNode);
			end = newNode;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			// Initialize to the start of WootLinkedList
			WootLinkedListNode<T> curr = start;
			
			@Override
			public boolean hasNext() {
				// Check if a value/WootLinkedListNode exists for us to retrieve
				return curr != null;
			}

			@Override
			public T next() {
				// Obtain the value of the current node and proceed to next node
				T result = curr.getValue();
				curr = curr.getNext();
				return result;
			}
		};
	}
}