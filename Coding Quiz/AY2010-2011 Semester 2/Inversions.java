package coding_quiz;

import java.util.ArrayList;

/**
 * Public Class: Inversions
 * 
 * Description: This class counts the number of inversions in an array. The
 * assumption is that all elements in the array is distinct.
 * 
 * @author Niu Yunpeng
 */
public class Inversions {
	/**
	 * Public Static Method: int countInversions(ArrayList<Integer>)
	 * 
	 * Description: This method calculates the number of inversions in a given
	 * array. It uses a brute-force approach and the time complexity is O(n^2).
	 * 
	 * @param intArray
	 *            is the input array of integers.
	 * 
	 * @return the number of inversions in a given array.
	 */
	public static int countInversions(ArrayList<Integer> intArray) {
		// Count the number of inversions
		int count = 0;
		
		// Size of the array list.
		int size = intArray.size();
		
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (intArray.get(i) > intArray.get(j)) {
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * Public Static Method: int countInversionsFast(ArrayList<Integer>)
	 * 
	 * Description: This method also calculates the number of inversions in a
	 * given array. It uses merge-sort algorithm to be efficient as O(nlogn)
	 * 
	 * @param intArray
	 *            is the input array of integers.
	 * 
	 * @return the number of inversions in a given array.
	 */
	public static int countInversionsFast(ArrayList<Integer> intArray) {
		return count(intArray, 0, intArray.size() - 1);
	}

	/**
	 * Private Static Method: int count(ArrayList<Integer>, int, int)
	 * 
	 * Description: This method is actual merge-like to count inversions.
	 */
	private static int count(ArrayList<Integer> arr, int start, int end) {
		if (start == end) {
			return 0;
		} else {
			// Divide the arrayList into two parts and calculate separately.
			int mid = (start + end) / 2;

			return count(arr, start, mid) + count(arr, mid + 1, end)
					+ merge(arr, start, mid, end);
		}
	}

	/**
	 * Private Static Method: void merge(ArrayList<Integer>, int, int, int)
	 * 
	 * Description: This method merges the first half and the second half of the
	 * array list together.
	 */
	private static int merge(ArrayList<Integer> arr, int start, int mid, int end) {
		// Temporary array for storing the merged values
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		// Pointers for the left and right half of the array.
		int leftIndex = start;
		int rightIndex = mid + 1;

		// The number of elements in the segment of arrayList
		int size = end - start + 1;

		// Count the number of inversions for each element in the first half.
		int result = 0;

		// Property: Since the first and second half has been sorted internal,
		// if A from the first half is greater than B from the second half, then
		// all elements after A in the first half is greater than B.
		for (int i = 0; i < size; i++) {
			if (leftIndex == mid + 1) {
				temp.add(arr.get(rightIndex++));
			} else if (rightIndex == end + 1) {
				temp.add(arr.get(leftIndex++));
			} else if (arr.get(leftIndex) > arr.get(rightIndex)) {
				result += (mid - leftIndex + 1);
				temp.add(arr.get(rightIndex++));
			} else {
				temp.add(arr.get(leftIndex++));
			}
		}

		for (int i = start; i <= end; i++) {
			arr.set(i, temp.get(i - start));
		}

		return result;
	}

	/**
	 * Public Static Method: ArrayList<Integer> createInversions(ArrayList<Integer>, int)
	 * 
	 * Description: This method takes a sorted array of length n and create
	 * inversions on it. The number of inversions should be < n.
	 * 
	 * @return the new array with inversions
	 */
	public static ArrayList<Integer> createInversions(
			ArrayList<Integer> intArray, int numInversions) {
		int last = intArray.get(numInversions);

		// Shift each element in the interval forward.
		for (int i = numInversions; i > 0; i--) {
			intArray.set(i, intArray.get(i - 1));
		}
		intArray.set(0, last);

		return intArray;
	}
}
