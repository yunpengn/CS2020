package lectures;

/**
 * Class: IntList
 * 
 * Purpose: This class provides a simple implementation of an Abstract Data Type
 * (ADT) - list. It is an important kind of collections of objects. However, in 
 * this implementation, all of the items must be of type Integer.
 * 
 * Constructor:
 * 		IntList():
 * 			Initialize place in memory to store items in the list
 * 
 * Public Methods:
 * 		void add(int x):
 * 			Add one new item to the list.
 * 		boolean isEmpty():
 * 			Returns whether the list is empty or not.
 *		int size():
 *     		Returns the number of items currently in the list.
 * 
 * @author Niu Yunpeng
 */

public class IntList {
	// Sets an arbitrary value to be the size of the list.
	private final int SIZE = 100;

	// The array for storage of items in the list.
	private int[] items = null;

	// The ending index of the current state in the list.
	private int end = 0;

	/**
	 * Description: Constructor of the class.
	 */
	public IntList() {
		items = new int[SIZE];
	}

	public void append(int x) {
		items[end++] = x;
	}

	public void prepend(int x) {
		end++;
		for(int i = end; i > 0; i--) {
			items[i] = items[i - 1];
		}
		items[0] = x;
	}

	// Slot should be similar to index, starting from 0.
	public void put(int x, int slot) {
		end++;
		for (int i = end; i > slot; i--) {
			items[i] = items[i - 1];
		}
		items[slot] = x;
	}

	public void remove(int x) {
		int found = -1;
		for (int i = 0; i < end; i++) {
			if (x == items[i]) {
				found = i;
				break;
			}
		}

		if (found >= 0) {
			for (int i = found; i < end; i++) {
				items[i] = items[i + 1];
			}
			end--;
		} else {
			System.out.println(x + "does not exist in the list.");
		}
	}

	public boolean contains(int x) {
		for (int i = 0; i < end; i++) {
			if (items[i] == x) {
				return true;
			}
		}
		return false;
	}

	private int findNextNotIndex(int x, int start) {
		for (int i = start; i < end; i++) {
			if (items[i] != x) {
				return i;
			}
		}
		return -1;
	}

	public void removeAll(int x) {
		for (int before = 0, after = 0; before < end; before++, after++) {
			if (after >= end) {
				end = before;
				break;
			} else {
				after = findNextNotIndex(x, after);

				if (after == -1) {
					end = before;
					break;
				} else {
					items[before] = items[after];
				}
			}
		}
	}

	public int getFirst() {
		return items[0];
	}

	public int getLast() {
		return items[end];
	}

	public int get(int slot) {
		return items[slot];
	}

	public void set(int slot, int x) {
		items[slot] = x;
	}

	public boolean isEmpty() {
		return end == 0;
	}

	public void print() {
		for (int i : iterate()) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}

	public int[] iterate() {
		int[] temp = new int[end];

		for (int i = 0; i < end; i++) {
			temp[i] = items[i];
		}

		return temp;
	}
}
