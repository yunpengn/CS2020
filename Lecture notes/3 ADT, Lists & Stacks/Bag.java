package lectures;

/**
 * Class: Bag
 * 
 * Purpose: This class provides a simple implementation of an Abstract Data Type
 * (ADT) - bag. It is an important kind of collections of objects.
 * 
 * Constructor:
 * 		Bag():
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

public class Bag<Item> implements IBag<Item> {
	// Sets an arbitrary value to be the size of the bag.
	final int SIZE = 100;

	Item[] items = null;

	/**
	 * Description: Constructor of the class.
	 */
	public Bag() {
		// items = new Item[SIZE];
		// Cannot create a generic array of Item
	}

	/**
	 * Description: Add one new item to the bag.
	 * 
	 * @param x
	 *            The new item that is going to be added.
	 */
	public void add(Item x) {

	}

	/**
	 * Description: Checks whether the bag is empty.
	 * 
	 * @return Return a boolean value representing whether the bag is empty
	 *         (there is 0 item in the bag).
	 */
	public boolean isEmpty() {
		return true;
	}

	/**
	 * Description: Checks whether the bag is empty.
	 * 
	 * @return Return an integer value representing the size (the number of
	 *         items) in the bag.
	 */
	public int size() {
		return 0;
	}
}
