package lectures;

/**
 * Class: Sorter
 * 
 * Description: This class implements a few common-used sorting algorithms. The
 * result will be sorted into ascending order.
 * 
 * @author Niu Yunpeng
 */
public class Sorter<Type extends Comparable<Type>> {
	private void swap(Type[] arr, int p, int q) {
		Type temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}
	
	/********************************************************************
	 * Selection Sort
	 * 
	 * Time Complexity: 
	 * 		1. Best case: O (n^2)
	 * 		2. Average case: O (n^2)
	 * 		3. Worst case: O (n^2)
	 * 
	 * Space Complexity: 1
	 * 
	 * Stable: Yes
	 *******************************************************************/
	public void selectionSort(Type[] arr, int start, int end) {
		int size = end - start + 1;
		int maxIndex = -1;
		Type maxValue = null;

		for (int i = 0; i < size - 1; i++) {
			maxIndex = start + i;
			maxValue = arr[maxIndex];
			
			for (int j = start + i + 1; j <= end; j++) {
				if (arr[j].compareTo(maxValue) < 0) {
					maxIndex = j;
					maxValue = arr[j];
				}
			}

			if (maxIndex != start + i) {
				swap(arr, start + i, maxIndex);
			}
		}
	}
	
	/********************************************************************
	 * Insertion Sort
	 * 
	 * Time Complexity: 
	 * 		1. Best case: O (n)
	 * 		2. Average case: O (n^2)
	 * 		3. Worst case: O (n^2)
	 * 
	 * Space Complexity: 1
	 * 
	 * Stable: Yes
	 *******************************************************************/
	public void insertionSort(Type[] arr, int start, int end) {
		Type current = null;

		for (int i = start + 1; i <= end; i++) {
			current = arr[i];
			
			int j = i - 1;
			for (; j >= start && arr[j].compareTo(current) > 0; j--) {
				arr[j + 1] = arr[j];
			}

			arr[j + 1] = current;
		}
	}
	
	/********************************************************************
	 * Bubble Sort
	 * 
	 * Time Complexity: 
	 * 		1. Best case: O (n)
	 * 		2. Average case: O (n^2)
	 * 		3. Worst case: O (n^2)
	 * 
	 * Space Complexity: 1
	 * 
	 * Stable: Yes
	 *******************************************************************/
	public void bubbleSort(Type[] arr, int start, int end) {
		boolean hasSwap = true;
		int size = end - start + 1;
		
		// Repeat until no swap happens during traversing the array
		for (int i = 0; hasSwap && i < size - 1; i++) {
			hasSwap = false;

			for (int j = start; j < end - i; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					hasSwap = true;
					swap(arr, j, j + 1);
				}
			}
		}
	}

	/********************************************************************
	 * Merge Sort (Version 1)
	 * 
	 * Time Complexity: 
	 * 		1. Best case: O (n*logn)
	 * 		2. Average case: O (n*logn)
	 * 		3. Worst case: O (n*logn)
	 * 
	 * Space Complexity: O (n*logn)
	 * 
	 * Stable: Yes
	 *******************************************************************/
	public void mergeSort(int[] arr, int start, int end) {
		int[] sorted = mergeSortHelper(arr, start, end);

		System.arraycopy(sorted, 0, arr, start, end - start + 1);
	}
	
	private int[] mergeSortHelper(int[] arr, int start, int end) {
		if (end - start < 1) {
			int[] now = {arr[start]};

			return now;
		} else {
			int mid = (start + end) / 2;
			
			int[] first = mergeSortHelper(arr, start, mid);
			int[] second = mergeSortHelper(arr, mid + 1, end);

			return merge(first, second);
		}
	}

	private int[] merge(int[] first, int[] second) {
		int m = 0, n = 0;
		int size1 = first.length, size2 = second.length, size = size1+size2;
		int result[] = new int[size];

		for (int i = 0; i < size; i++) {
			if (m == size1) {
				result[i] = second[n++];
			} else if (n == size2) {
				result[i] = first[m++];
			} else if (first[m] <= second[n]) {
				result[i] = first[m++];
			} else {
				result[i] = second[n++];
			}
		}

		return result;
	}
	
	/********************************************************************
	 * Merge Sort (Version 2)
	 * 
	 * Time Complexity: 
	 * 		1. Best case: O (n*logn)
	 * 		2. Average case: O (n*logn)
	 * 		3. Worst case: O (n*logn)
	 * 
	 * Space Complexity: O (n)
	 * 
	 * Stable: Yes
	 *******************************************************************/
	public void mergeSort2(int[] arr, int start, int end) {
		if (end == start) {
			;
		} else {
			int mid = (start + end) / 2;

			mergeSort2(arr, start, mid);
			mergeSort2(arr, mid + 1, end);

			merge2(arr, start, mid, end);
		}
	}

	private void merge2(int[] arr, int start, int mid, int end) {
		int head1 = start, head2 = mid + 1;
		int size = end - start + 1;
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			if (head1 == mid + 1) {
				result[i] = arr[head2++];
			} else if (head2 == end + 1) {
				result[i] = arr[head1++];
			} else if (arr[head1] <= arr[head2]) {
				result[i] = arr[head1++];
			} else {
				result[i] = arr[head2++];
			}
		}

		System.arraycopy(result, 0, arr, start, size);
	}
}
