package lectures;

/**
 * Class: IntStack
 * 
 * Purpose: This class provides a simple implementation of an Abstract Data Type
 * (ADT) - stack. It is an important kind of collections of objects. However, in 
 * this implementation, all of the items must be of type Integer.
 * 
 * Constructor:
 * 		IntStack():
 * 			Initialize place in memory to store items in the stack
 * 
 * Public Methods:
 * 		void push(int x):
 * 			Add one new item to the stack.
 * 		int pop():
 * 			Delete the last item and return its value.
 * 		int peek():
 * 			Returns the value of the top item without deleteing it.
 * 		boolean isEmpty():
 * 			Returns whether the stack is empty or not.
 *		int size():
 *     		Returns the number of items currently in the stack.
 * 
 * Property:
 * 		Stack is First-in-Last-Out (FILO).
 * 
 * @author Niu Yunpeng
 */

public class IntStack {
	// Sets an arbitrary value to be the size of the list.
	private final int SIZE = 100;

	// The array for storage of items in the list.
	private int[] items = null;

	// The index of the lastly visited element in the stack.
	private int top = 0;

	public IntStack() {
		items = new int[SIZE];
	}

	public void push(int x) {
		items[top++] = x;
	}

	public int pop() {
		return items[--top];
	}

	public int peek() {
		return items[top];
	}

	public boolean isEmpty() {
		return top == 0;
	}

	public int size() {
		return top + 1;
	}
}
