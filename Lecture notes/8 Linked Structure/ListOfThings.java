package lectures;

import java.util.Iterator;

public class ListOfThings implements Iterable<String> {
	private String[] things = {"Martin", "KokLim", "Seth", "Alan"};

	private class ListOfThingsIterator implements Iterator<String> {
		int now = -1;
		int length = things.length;

		public boolean hasNext() {
			return now < length - 1;
		}

		public String next() {
			return things[++now];
		}
	}

	public Iterator<String> iterator() {
		Iterator<String> iter = new ListOfThingsIterator();
		return iter;
	}

	public static void main(String[] args) {
		ListOfThings test = new ListOfThings();
		Iterator<String> myIter = test.iterator();

		while (myIter.hasNext()) {
			System.out.println(myIter.next());
		}
	}
}
