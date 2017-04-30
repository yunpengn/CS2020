package sg.edu.nus.cs2020;

public class BinarySearch {

	/**
	 * Returns the first index of occurrence of x in a sorted array. E.g.
	 * search({1, 2, 3, 3, 3}, 3) should return 2.
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
		while (begin < end) {
			int mid = (begin + end) / 2;
			if (arr[mid] < x) {
				begin = mid + 1;
			} else {
				end = mid;
			}
		}
		return begin;
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
		int[] ans = { -1, -1 };
		int leftBound = search(arr, x);
		ans[0] = leftBound;

		int begin = 0;
		int end = arr.length - 1;
		while (begin < end) {
			int mid = (begin + end + 1) / 2;
			if (arr[mid] > x) {
				end = mid - 1;
			} else {
				begin = mid;
			}
		}
		ans[1] = begin;

		return ans;
	}
}
