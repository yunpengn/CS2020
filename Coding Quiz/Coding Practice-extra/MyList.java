package coding_quiz;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class MyList implements IList {
	// Store all 7 elements in the list
	private LinkedList<Integer> elements = new LinkedList<Integer>();
	// The number of items now
	private int size = 0;

	public void add(int x) {
		if (size == 7) {
			elements.removeFirst();
			elements.add(x);
		} else {
			elements.add(x);
			size++;
		}
	}

	public void remove() {
		elements.removeLast();
	}

	public int calculate(int k) {
		Iterator<Integer> myIterator = iterator();
		int sum = 0;

		while (myIterator.hasNext()) {
			int now = myIterator.next();

			if (now > k) {
				sum += now;
			} else if (now < k) {
				sum -= now;
			}
		}

		return sum;
	}

	public Iterator<Integer> iterator() {
		return elements.iterator();
	}

	@Override
	public String toString() {
		Iterator<Integer> myIterator = iterator();
		Stack<Integer> values = new Stack<Integer>();
		StringBuilder myBuilder = new StringBuilder();

		while (myIterator.hasNext()) {
			int now = myIterator.next();
			values.push(now);
		}
		
		while(values.size() > 0) {
			myBuilder.append(values.pop().toString());
		}

		return myBuilder.toString();
	}
}
