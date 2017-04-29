package lectures;

/**
 * Class: IntBag
 * 
 * Purpose: This class provides a simple implementation of an Abstract Data Type
 * (ADT) - bag. It is an important kind of collections of objects. However, in 
 * this implementation, all of the items must be of type Integer.
 * 
 * Constructor:
 * 		IntBag():
 * 			Initialize place in memory to store items in the bag
 * 
 * Public Methods:
 * 		void add(Item x):
 * 			Add one new item to the bag.
 * 		boolean isEmpty():
 * 			Returns whether the bag is empty or not.
 *		int size():
 *     		Returns the number of items currently in the bag.
 * 
 * Property:
 * 		Once created, none of the items can be removed from the bag.
 * 
 * @author Niu Yunpeng
 */

public class IntBag {
	// Sets an arbitrary value to be the size of the bag.
	private final int SIZE = 100;

	// The array for storage of items in the bag.
	private int[] items = null;

	// Set the current index to be 0 (start from beginning)
	private int index = 0;

	/**
	 * Description: Constructor of the class.
	 */
	public IntBag() {
		items = new int[SIZE];
	}

	/**
	 * Description: Add one new item to the bag.
	 * 
	 * @param x
	 *            The new item that is going to be added.
	 */
	public void add(int x) {
		items[index++] = x;
	}

	/**
	 * Description: Checks whether the bag is empty.
	 * 
	 * @return Return a boolean value representing whether the bag is empty
	 *         (there is 0 item in the bag).
	 */
	public boolean isEmpty() {
		return index == 0;
	}

	/**
	 * Description: Checks whether the bag is empty.
	 * 
	 * Property:
	 * 		Although the index of an array starts from 0, the size should be
	 * 		index + 1, meaning that it counts from 1.
	 * 
	 * @return Return an integer value representing the size (the number of
	 *         items) in the bag.
	 */
	public int size() {
		return index + 1;
	}

	/**
	 * Description: Returns all the items in the bag.
	 * 
	 * @return Return an array consisting of all elements in the bag.
	 */
	public int[] iterate() {
		int[] temp = new int[index];

		for (int i = 0; i < index; i++) {
			temp[i] = items[i];
		}

		return temp;
	}
}
