package discussion_group;

public class SwiftSearch {
	private boolean unsortedSearch(int key, int[] array, int begin, int end) {
		// Base case:
		if (begin >= end) {
			return key == array[begin];
		}

		// Check mid-point of the array
		int mid = (begin + (end - begin) / 2);

		if (key == mid) {
			return true;
		}

		// Do recursive searching
		boolean leftSearch = unsortedSearch(key, array, begin, mid - 1);
		boolean rightSearch = unsortedSearch(key, array, mid + 1, end);

		// Check return value
		if (leftSearch || rightSearch) {
			return true;
		} else {
			return false;
		}
	}

	public boolean unsortedSearch(int key, int[] array) {
		int length = array.length;

		return unsortedSearch(key, array, 0, length - 1);
	}
}
