package sg.edu.nus.cs2020;

import java.util.Arrays;

public class BinarySearch {

	/**
	 * Description: Returns the first index of occurrence of x in a sorted
	 * array. E.g. search({1, 2, 3, 3, 3}, 3) should return 2.
	 * 
	 * Property: 1) If the given array is not sorted in the increasing order,
	 * this function will sort it first; 2) If the given value is not found in
	 * the array, it will return -1 as a dummy value.
	 *
	 * @param arr
	 *            Sorted integer array.
	 * @param x
	 *            The key to be searched in arr.
	 * @return The index of x in arr, using 0-based index. x is guaranteed to be
	 *         in arr.
	 */
	public static int search(int[] arr, int x) {
		int begin = 0;
		int end = arr.length - 1;
		int mid = -1;
		
		// If there is no element in the array (length is 0, so end is -1),
		// returns -1 since we certainly cannot find anything in the array.
		if (end == -1) {
			return -1;
		}

		// If the array is not sorted, it will be sorted before binary search.
		if (!checkSorted(arr)) {
			Arrays.sort(arr);
		}

		while (begin < end) {
			mid = (begin + end) / 2;

			if (arr[mid] < x) {
				begin = mid + 1;
			} else {
				end = mid;
			}
		}
		
		if (arr[begin] == x) {
			return begin;
		} else {
			// If x cannot be found in the array, return -1.
			return -1;
		}
	}

	/**
	 * Checks whether a given array is sorted in the increasing order.
	 * 
	 * @param arr
	 *            The array that is going to be tested
	 * @return A boolean value representing whether the array is sorted.
	 */
	private static boolean checkSorted(int[] arr) {
		int size = arr.length;

		for (int i = 0; i < size - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the range in which x occurs in arr
	 * E.g. search({1, 2, 3, 3, 3}, 3) should return [2, 4].
	 *
	 * @param arr
	 *            Sorted integer array
	 * @param x
	 *            The key to be searched in arr.
	 * @return An integer array of size 2, in which the first index is the index
	 *         of first occurrence of x, while the second index is the index of
	 *         last occurrence of x. x is guaranteed to be in arr.
	 */
	public static int[] searchRange(int[] arr, int x) {
		int[] range = { -1, -1 };

		// If the array is not sorted, it will be sorted before binary search.
		if (!checkSorted(arr)) {
			Arrays.sort(arr);
		}

		// Find the position where x appears for the first time.
		int first = search(arr, x);

		if (first == -1) {
			// If x does not appear in the array, return the array of [-1, -1].
			return range;
		} else {
			range[0] = first;

			// Find the position where x appears for the last time.
			int begin = first;
			int end = arr.length - 1;
			int mid = (begin + end + 1) / 2;
			while (begin < end) {
				mid = (begin + end + 1) / 2;

				if (arr[mid] <= x) {
					begin = mid;
				} else {
					end = mid - 1;
				}
			}

			range[1] = begin;

			return range;
		}

	}
}
