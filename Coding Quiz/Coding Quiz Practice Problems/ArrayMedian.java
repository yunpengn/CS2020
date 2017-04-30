package coding_quiz;

public class ArrayMedian {
	private int[] arr1 = null;
	private int[] arr2 = null;

	/**
	 * Public Method: int median(int[], int[])
	 * 
	 * Description: Given two sorted arrays, this method will return the median
	 * element if these arrays are merged together. Note that an array of length
	 * n has the median element with index of (n-1)/2.
	 */
	public int median(int[] X, int[] Y) throws IllegalArgumentException {
		if (X == null || Y == null || (X.length == 0 && Y.length == 0)) {
			throw new IllegalArgumentException();
		} else if (X.length == 0) {
			return Y[(Y.length - 1) / 2];
		} else if (Y.length == 0) {
			return X[(X.length - 1) / 2];
		}

		// Gives a copy of two arrays in the member varaibles
		arr1 = X;
		arr2 = Y;

		return median(0, X.length - 1, 0, Y.length - 1);
	}

	private int median(int start1, int end1, int start2, int end2) {
		int mid1 = (start1 + end1) / 2;
		int mid2 = (start2 + end2) / 2;

		if (start1 == end1 && start2 == end2) {
			return Math.min(arr1[start1], arr2[start2]);
		} else if (start1 == end1) {
			return medianOfThree(arr1[start1], arr2[start2], arr2[end2]);
		} else if (start2 == end2) {
			return medianOfThree(arr1[start1], arr1[end1], arr2[start2]);
		} else if (arr1[mid1] == arr2[mid2]) {
			return arr1[mid1];
		} else if (arr1[mid1] > arr2[mid2]) {
			return median(start1, mid1, mid2, end2);
		} else {
			return median(mid1, end1, start2, mid2);
		}
	}

	// Returns the median of 3 integers.
	private int medianOfThree(int a, int b, int c) {
		if ((a - b) * (b - c) > 0) {
			return b;
		} else if ((a - c) * (c - b) > 0) {
			return c;
		} else {
			return a;
		}
	}
}
