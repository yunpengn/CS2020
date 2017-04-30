package coding_quiz;

import java.util.LinkedList;

public class ListMedian<T> {
	// This methods uses the Java library API
	public T findMedian(LinkedList<T> list) {
		return list.get((list.size() - 1) / 2);
	}

	/*
	 * What if we are not allowed to use Java system library? Then, if the list
	 * is doubly linked, we will have two pointers to move forward and backward
	 * respectively, until they meet each other; if the list is singly linked,
	 * we will traverse once first to determine its length, then traverse to the
	 * middle node.
	 */
}
