package sg.edu.nus.cs2020;

public class MergeSort {
	/**
	 * Sort the array of positive double arr with the following rules:
	 * - If floor(x) < floor(y), then x must come before y in the sorted order;
	 * - If floor(x) > floor(y), then x must come after y in the sorted order;
	 * - If floor(x) = floor(y), then the order of x and y in the sorted order
	 *   is the same as in the original order.
	 * 
	 * @param arr
	 *            Array of positive double to be sorted
	 */
	public static void sort(double[] arr) {
		MergeSort sorter = new MergeSort();
		sorter.mergeSort(arr, 0, arr.length - 1);
	}

	private void mergeSort(double[] arr, int start, int end) {
		if (start == end) {
			return;
		} else {
			int mid = (start + end) / 2;

			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);

			merge(arr, start, mid, end);
		}
	}

	private void merge(double[] arr, int start, int mid, int end) {
		int head1 = start, head2 = mid + 1;
		int size = end - start + 1;
		double[] result = new double[size];

		for (int i = 0; i < size; i++) {
			if (head1 == mid + 1) {
				result[i] = arr[head2++];
			} else if (head2 == end + 1) {
				result[i] = arr[head1++];
			} else if (compare(arr[head1], arr[head2]) <= 0) {
				result[i] = arr[head1++];
			} else {
				result[i] = arr[head2++];
			}
		}

		System.arraycopy(result, 0, arr, start, size);
	}

	private int compare(double a, double b) {
		int aFloor = (int) a;
		int bFloor = (int) b;

		if (aFloor < bFloor) {
			return -1;
		} else if (aFloor == bFloor) {
			return 0;
		} else {
			return 1;
		}
	}
}