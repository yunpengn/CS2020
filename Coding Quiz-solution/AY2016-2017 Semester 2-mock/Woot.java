package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author ary
 */
public class Woot implements IWoot {
	
	/**
	 * Description: Attempts to compute x1 - x2 + x3 ... +/- x_m for all m elements in a WootLinkedList
	 * @param wootList - The WootLinkedList to perform computation on
	 * @return the computed sum for the wootLinkedList
	 */
	public int computeWoot(WootLinkedList<Integer> wootList) {
		int sign = 1, sum = 0;
		// Iterate through wotLinkedList using implemented iterator
		Iterator<Integer> wootLinkedListIterator = wootList.iterator();
		while (wootLinkedListIterator.hasNext()) {
			// Compute cumulative sum
			sum += sign * wootLinkedListIterator.next();
			// Alternate between +/- when summing
			sign = -sign;
		}
		// Return computed sum
		return sum;
	}
	
	/**
	 * Description: Attempts to construct a WootLinkedList given k and an original linked list
	 * @param originalList - A linked list consisting of an arbitrary amount of integers
	 * @param k - The limit which to determine whether an element should be in the WootLinkedList
	 * @return a constructed WootLinkedList based on the originalList
	 */
	public WootLinkedList<Integer> processWOOT(LinkedList<Integer> originalList, int k) {
		// Create new WootLinkedList
		WootLinkedList<Integer> wootLinkedList = new WootLinkedList<Integer>();
		// Iterate through original list
		Iterator<Integer> originalListIterator = originalList.iterator();
		while (originalListIterator.hasNext()) {
			// Obtain current value to be tested inside original list
			int currentValue = originalListIterator.next();
			// Add value into WootLinkedList if constraints are satisfied
			if (Math.abs(currentValue) <= k) {
				wootLinkedList.insertEnd(currentValue);
			}
		}
		// Return constructed WootLinkedList
		return wootLinkedList;
	}

}
