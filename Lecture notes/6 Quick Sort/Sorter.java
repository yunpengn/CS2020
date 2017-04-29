package lectures;

import java.util.Random;

public class Sorter<Type extends Comparable<Type>> {
	public void quickSort1(int[] arr, int start, int end) {
		if (end <= start) {
			;
		} else {
			int pivot = arr[start];
			int seperated = partition1(arr, start, end, pivot);

			quickSort1(arr, start, seperated - 1);
			quickSort1(arr, seperated + 1, end);
		}
	}

	// Returns the index of the pivot after partition.
	// Extra space needed: O(n)
	private int partition1(int[] arr, int start, int end, int pivot) {
		int size = end - start + 1;
		int low = 0, high = size - 1;
		int[] result = new int[size];

		for (int i = start + 1; i <= end; i++) {
			if (arr[i] < pivot) {
				result[low++] = arr[i];
			} else {
				result[high--] = arr[i];
			}
		}

		result[low] = pivot;

		for (int i = 0; i < size; i++) {
			arr[i + start] = result[i];
		}

		return low + start;
	}
	
	// In-place partition: no extra place needed
	// Since we use <= instead of <, duplicates will not crash the program.
	public void quickSort2(int[] arr, int start, int end) {
		if (end <= start) {
			;
		} else {
			int pivot = arr[start];
			int low = start + 1, high = end;
			int seperated = -1;

			while (low < high) {
				while (arr[low] <= pivot && low < high) {
					low++;
				}

				while (arr[high] > pivot && low < high) {
					high--;
				}

				if (low < high) {
					swap(arr, low, high);
				}
			}

			if (arr[low] >= pivot) {
				seperated = low - 1;
			} else {
				seperated = low;
			}

			swap(arr, start, seperated);

			quickSort2(arr, start, seperated - 1);
			quickSort2(arr, seperated + 1, end);
		}
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	// For the use of generating a random pivot index.
	private Random generator = new Random();
	
	// Use random-selected pivot index and use Paranoid quick sort.
	public void quickSort3(int[] arr, int start, int end) {
		if (end <= start) {
			return;
		} else {
			int size = end - start + 1;
			int pivotIndex = -1, seperated = -1, seperatedSize = -1;
			
			// The number of times for re-selecting the pivot is restricted to 4
			// to avoid being blocked. If all of the elements are the same, this
			// will become an infinite loop.
			for (int i = 0; i < 4 && seperatedSize < size / 10
					|| seperatedSize > 9 * size / 10; i++) {
				pivotIndex = generator.nextInt(end - start + 1) + start;
				seperated = partition3(arr, start, end, pivotIndex);
				seperatedSize = seperated - start + 1;
			}

			quickSort3(arr, start, seperated - 1);
			quickSort3(arr, seperated + 1, end);
		}
	}

	private int partition3(int[] arr, int start, int end, int pivotIndex) {
		int pivot = arr[pivotIndex];
		int low = start + 1, high = end;
		int seperated = -1;

		swap(arr, pivotIndex, start);

		while (low < high) {
			while (low < high && (arr[low] <= pivot || arr[high] > pivot)) {
				if (arr[low] <= pivot) {
					low++;
				}

				if (arr[high] > pivot) {
					high--;
				}
			}

			if (low < high) {
				swap(arr, low, high);
			}
		}

		if (arr[low] >= pivot) {
			seperated = low - 1;
		} else {
			seperated = low;
		}

		swap(arr, start, seperated);

		return seperated;
	}
	
	// Non-Paranoid random-pivot quick sort
	// However, duplicates are handled by packDuplicates method here.
	public void quickSort4(int[] arr, int start, int end) {
		if (end <= start) {
			return;
		} else {
			int pivotIndex = generator.nextInt(end - start + 1) + start;
			int newPivotIndex = packDuplicates(arr, start, pivotIndex);
			int numDuplicates = pivotIndex - newPivotIndex + 1;

			int seperated = partition3(arr, start, end, newPivotIndex);

			quickSort4(arr, start, seperated - numDuplicates);
			quickSort4(arr, seperated + 1, end);
		}
	}
	
	// This method works well when there are too many duplicates. However, it
	// may slow down the program when there are only a few duplicates or even
	// no duplicate at all.
	private int packDuplicates(int[] arr, int start, int pivotIndex) {
		int pivot = arr[pivotIndex];
		int i = start;

		while (i < pivotIndex) {
			if (arr[i] == pivot) {
				swap(arr, i, --pivotIndex);
			} else {
				i++;
			}
		}

		return pivotIndex;
	}
}