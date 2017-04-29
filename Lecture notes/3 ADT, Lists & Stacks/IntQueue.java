package lectures;

/**
 * Class: IntQueue
 * 
 * Purpose: This class provides a simple implementation of an Abstract Data Type
 * (ADT) - queue. It is an important kind of collections of objects. However, in 
 * this implementation, all of the items must be of type Integer.
 * 
 * Constructor:
 * 		IntQueue():
 * 			Initialize place in memory to store items in the stack
 * 
 * Public Methods:
 * 		void enqueue(int x):
 * 			Adds a new item to the end of the queue.
 * 		int dequeue():
 * 			Deletes the first item at the beginning of the queue and returns it.
 * 		boolean isEmpty():
 * 			Returns whether the queue is empty or not.
 *		int size():
 *     		Returns the number of items currently in the queue.
 * 
 * Property:
 * 		Stack is First-in-First-Out (FIFO).
 * 
 * @author Niu Yunpeng
 */

public class IntQueue {
	// Sets an arbitrary value to be the size of the list.
	private final int SIZE = 100;

	// The array for storage of items in the list.
	private int[] items = null;

	// The starting and ending index of the queue right now.
	private int start = 0;
	private int end = 0;

	public IntQueue() {
		items = new int[SIZE];
	}

	public void enqueue(int x) {
		items[end++] = x;
	}

	public int dequeue() {
		return items[start++];
	}

	public boolean isEmpty() {
		return start == end;
	}

	public int size() {
		return end - start + 1;
	}
}
