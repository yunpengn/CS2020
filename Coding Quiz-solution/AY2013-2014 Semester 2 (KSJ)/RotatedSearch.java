package cs2020;

/**
 * Class containing the static searchMax method for finding the maximum in a
 * sorted, but rotated, array.
 * 
 * @author gilbert
 * 
 */
public class RotatedSearch {

	/**
	 * searchMax
	 * 
	 * @param <T>
	 * @param list
	 *            an array of items Takes a list of items and returns the
	 *            maximum item in the list. This is a generic method, and works
	 *            for an array of any type of items. This is a static method.
	 * 
	 *            The list of items is sorted from smallest to largest, but it
	 *            has been rotated, i.e., the smallest item is not in array slot
	 *            0 and the sequence wraps around the end of the array.
	 */
	// ////////////////////////////////////////////////////////////////////////
	// Your solution goes here
	// ////////////////////////////////////////////////////////////////////////
	public static <T extends Comparable<T>> T searchMax(T[] list) {
		if (list == null)
			return null;
		else if (list.length == 0)
			return null;

		T first = list[0];
		int begin = 0;
		int end = list.length - 1;
		while (begin < end) {
			int mid = (begin + end) / 2;
			if (list[mid].compareTo(first) > 0) begin = mid + 1;
			else end = mid;
		}
		if (first.compareTo(list[end]) > 0) return list[end - 1];
		else return list[end];
	}

	// ////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {

		Integer[] integers = { 39, 47, 53, 3, 13, 14, 16, 18, 25, 31 };
		System.out.println(searchMax(integers));
		// Prints: 53

		Double[] doubles = { 16.69, 23.89, 27.61, 33.05, 34.48, 36.63, 46.62,
				5.96, 8.3, 11.44 };
		System.out.println(searchMax(doubles));
		// Prints: 46.62

		String[] names = { "Franny", "Glen", "Harry", "Isabelle", "Julia",
				"Alice", "Bob", "Collin", "David", "Elissa" };
		System.out.println(searchMax(names));
		// Prints: Julia

	}

}
