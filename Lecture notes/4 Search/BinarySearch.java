package lectures;

public class BinarySearch {
	public int searchFirstIterative(int[] arr, int target) {
		int start = 0, end = arr.length - 1, mid = -1;

		while (start < end) {
			mid = (start + end) / 2;

			if (arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		if (arr[start] == target) {
			return start;
		} else {
			return -1;
		}
	}

	public int searchFirstRecursive(int[] arr, int target) {
		return searchFirstHelper(arr, target, 0, arr.length - 1);
	}

	// This helper function should only be called by searchFirstRecursive.
	private int searchFirstHelper(int[] arr, int target, int start, int end) {
		if (start >= end) {
			return arr[start] == target ? start : -1;
		} else {
			int mid = (start + end) / 2;

			if (arr[mid] < target) {
				return searchFirstHelper(arr, target, mid + 1, end);
			} else {
				return searchFirstHelper(arr, target, start, mid);
			}
		}
	}

	public int searchLastIterative(int[] arr, int target) {
		int start = 0, end = arr.length - 1, mid = -1;

		while (start < end) {
			mid = (start + end + 1) / 2;

			if (arr[mid] > target) {
				end = mid - 1;
			} else {
				start = mid;
			}
		}

		if (arr[end] == target) {
			return end;
		} else {
			return -1;
		}
	}

	public int searchLastRecursive(int[] arr, int target) {
		return searchLastHelper(arr, target, 0, arr.length - 1);
	}

	// This helper function should only be called by searchLastRecursive.
	private int searchLastHelper(int[] arr, int target, int start, int end) {
		if (start >= end) {
			return arr[end] == target ? end : -1;
		} else {
			int mid = (start + end + 1) / 2;

			if (arr[mid] > target) {
				return searchLastHelper(arr, target, start, mid - 1);
			} else {
				return searchLastHelper(arr, target, mid, end);
			}
		}
	}
}
