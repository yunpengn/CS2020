package coding_quiz;

/**
 * Public Class: RotatedSearch
 * 
 * Description: This class contains the static searchMax method for finding the
 * maximum in a sorted, but rotated, array.
 * 
 * @author Niu Yunpeng
 * based on
 * @author gilbert
 */
public class RotatedSearch {
	/**
	 * Public Method: char[] searchMax(Object[])
	 * 
	 * Description: The list of items is sorted from smallest to largest, but it
	 * has been rotated, i.e., the smallest item is not in array slot 0 and the
	 * sequence wraps around the end of the array.
	 * 
	 * Property: This is a generic method, and works for an array of any type of
	 * items. This is a static method.
	 * 
	 * @param list
	 *            an array of items sorted and rotated
	 * 
	 * @return the maximum item in the list.
	 */
	public static <T extends Comparable<T>> T searchMax(T[] items) {
		// Avoid illegal input
		if (items == null || items.length == 0) {
			return null;
		}

		// Initialize related local variables
		int start = 0;
		int end = items.length - 1;
		int mid = (start + end) / 2;
		
		// Binary search algorithm begins
		while (start < end) {
			mid = (start + end + 1) / 2;
			
			if (items[start].compareTo(items[end]) < 0) {
				return items[end];
			} else if (items[mid].compareTo(items[end]) > 0) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}

		return items[end];
	}

//////////////////////////////////////////////////////////////////////////

	public static void main(String[] args){
		Integer[] integers = {39, 47, 53, 3, 13, 14, 16, 18, 25, 31};
		System.out.println(searchMax(integers));
		// Prints: 53
		
		Double[] doubles = {16.69, 23.89, 27.61, 33.05, 34.48, 36.63, 46.62,
				5.96, 8.3, 11.44};
		System.out.println(searchMax(doubles));
		// Prints: 46.62
		
		String[] names = {"Franny", "Glen", "Harry", "Isabelle", "Julia",
				"Alice", "Bob", "Collin", "David", "Elissa"};
		System.out.println(searchMax(names));
		// Prints: Julia
	}
}
