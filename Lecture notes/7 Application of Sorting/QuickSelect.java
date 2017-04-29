package sg.edu.nus.cs2020;

import java.util.Random;

/**
 * Class: QuickSelect
 * 
 * Description: This class implements a quick select method. Using quick select,
 * you can find the nth smallest element in an unsorted array. The running time
 * is O(n).
 * 
 * @author Niu Yunpeng
 */
public class QuickSelect {
	// For the use of generating a random pivot index.
	private static Random generator = new Random();

    /**
	 * Public Static Method: int select(int[], int)
	 * 
	 * Description: Finds the nth smallest element in an unsorted array in O(n)
	 * time. If the index given is not possible, it will throw a not - found -
	 * exception.
	 *
	 * @param arr
	 *            The unsorted array to select from
	 * @param k
	 *            The index that we are looking for
	 * 
	 * @return the nth smallest element in the array
	 */
	public static int select(int[] arr, int k) throws NotFoundException {
		if (arr == null || k < 1 || k > arr.length) {
			throw new NotFoundException();
		}
		
		// array counts from 0 but k counts from 1.
		return selection(arr, k - 1, 0, arr.length - 1);
    }

	/**
	 * Private Static Method: int selection(int[], int, int, int)
	 * 
	 * Description: This method is the actual recursive part of the quick select
	 * algorithm. It uses divide-and-conquer to reduce the size of the problem
	 * gradually. The average running time is O(n). The partition used by this
	 * method will have a random-selected pivot.
	 * 
	 * @return the value of nth smallest element in the array
	 */
	private static int selection(int[] arr, int key, int start, int end) {
		// Generate a random (absolute) index for pivot.
		int pIndex = start + generator.nextInt(end - start + 1);

		// This position is the absolute index of the pivot after partition.
		int position = partition(arr, key, start, end, pIndex);

		if (position == key) {
			return arr[key];
		} else if (key < position) {
			return selection(arr, key, start, position - 1);
		} else {
			return selection(arr, key, position + 1, end);
		}
	}

	/**
	 * Private Static Method: int partition(int[], int, int, int, int)
	 * 
	 * Description: Use the same routine as the partition in the quick sort.
	 * Since neither two-pass partition nor three-way partition is used, this
	 * algorithm can be quite slow when most of the element are duplicates.
	 * 
	 * Property: Absolute index means the actual position in the whole array. It
	 * does not change with the value of start and end.
	 * 
	 * @return the absolute index of the pivot after partition operation.
	 */
	private static int partition(int[] arr, int key, int start, int end, int pIndex) {
		int pivot = arr[pIndex];
		int low = start + 1, high = end;
		int seperated = -1;

		// Swaps the pivot to the 1st position first.
		swap(arr, pIndex, start);

		// Move until low and high meet each other
		while (low < high) {
			while (low < high && (arr[low] <= pivot || arr[high] > pivot)) {
				// Low pointer will keep moving to the right until it meets an
				// element greater than the pivot.
				if (arr[low] <= pivot) {
					low++;
				}

				// High point will keep moving to the left until it meets an
				// element smaller than or equal to the pivot.
				if (arr[high] > pivot) {
					high--;
				}
			}

			// When low < high and arr[high] <= pivot < arr[low], swap these two
			// elements. After that, low and high pointers continue moving.
			if (low < high) {
				swap(arr, low, high);
			}
		}

		// The start and end may both be the last element originally. Do special
		// consideration for this case to avoid array-out-of-bound-exception.
		if (low >= arr.length) {
			return start;
		}

		// When low and high meets each other, swap the pivot (1st element in
		// the array) with arr[low] or arr[low - 1], depending on which pointer
		// moves the last step.
		if (arr[low] >= pivot) {
			seperated = low - 1;
		} else {
			seperated = low;
		}

		swap(arr, start, seperated);

		return seperated;
	}

	/**
	 * This method swaps two elements in a given array.
	 */
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
