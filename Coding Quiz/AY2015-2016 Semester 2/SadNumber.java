package coding_quiz;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Public Class: SadNumber
 * 
 * @author Niu Yunpeng
 */
public class SadNumber implements ISequence {
	/**
	 * Public Method: int nextValue(int)
	 * 
	 * Description: This method returns the next value after n. The rule is the
	 * next value of n is the sum of all digits of n squared.
	 */
	public int nextValue(int n) {
		int result = 0;

		while (n > 0) {
			result += (n % 10) * (n % 10);
			n /= 10;
		}

		return result;
	}

	/**
	 * Public Method: boolean cycleExist(int, int)
	 * 
	 * Description: This method checks whether the sequence starting at n
	 * reaches at 1 forever or begins a cycle in the first length elements.
	 */
	public boolean cycleExist(int n, int length) {
		LinkedList<Integer> sequence = new LinkedList<Integer>();

		while (length > 0) {
			sequence.add(n);
			n = nextValue(n);
			length--;

			if (sequence.contains(n)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Public Method: int smallestValueInCycle(int, int)
	 * 
	 * Description: This method returns the smallest value in the cycle. If
	 * there is no cycle, returns -1 instead.
	 */
	public int smallestValueInCycle(int n, int length) {
		LinkedList<Integer> sequence = new LinkedList<Integer>();
		int smallest = -1;

		while (length > 0) {
			sequence.add(n);
			n = nextValue(n);
			length--;

			if (sequence.contains(n)) {
				return findSmallest(sequence, n);
			}
		}

		return smallest;
	}

	/**
	 * Private Method: int findSmallest(LinkedList<Integer>, int)
	 * 
	 * Description: This methods find the smallest element in a linked list. The
	 * range of finding starts from the element whose value is n in the list.
	 */
	private int findSmallest(LinkedList<Integer> sequence, int n) {
		int smallest = n;
		int size = sequence.size();
		boolean inRange = false;
		int now = 0;

		for (int i = 0; i < size; i++) {
			now = sequence.get(i);

			if (now == n) {
				inRange = true;
			}

			if (inRange && now < smallest) {
				smallest = now;
			}
		}

		return smallest;
	}

	/**
	 * Public Method: void printSadCycle(int, int)
	 * 
	 * Description: This method prints the cycle starting from the smallest
	 * element in the cycle.
	 */
	public void printSadCycle(int max, int length) {
		int smallest = 0;

		for (int i = max; i > 1; i++) {
			smallest = smallestValueInCycle(i, length);

			if (smallest == 1) {
				continue;
			} else if (smallest != -1) {
				printCycleFrom(smallest);
				return;
			}
		}
		System.out.println("None");
	}

	/**
	 * Private Method: void printCycleFrom(int)
	 * 
	 * Description: This method prints the cycle starting from n.
	 */
	private void printCycleFrom(int n) {
		int now = nextValue(n);
		System.out.println(n);
		
		while (now != n) {
			System.out.println(now);
			now = nextValue(now);
		}
	}

	/**
	 * Public Method: Iterator<Integer> iterator(int)
	 * 
	 * Description: This method returns an iterator which stops at 1.
	 */
	public Iterator<Integer> iterator(int n) {
		return new myIterator(n);
	}

	private class myIterator implements Iterator<Integer> {
		private int now = 0;
		private boolean stop = false;

		public myIterator(int n) {
			now = n;
		}

		public boolean hasNext() {
			if (stop) {
				return false;
			} else if (now == 1) {
				stop = true;
			}

			return true;
		}

		public Integer next() {
			int before = now;
			now = nextValue(now);
			
			return before;
		}
	}
}
