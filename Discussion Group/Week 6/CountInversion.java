package discussion_group;

public class CountInversion {
	public int countInversion(int[] arr) {
		return countInversion(arr, 0, arr.length - 1);
	}

	private int countInversion(int[] arr, int start, int end) {
		if (end - start <= 0) {
			return 0;
		} else {
			int mid = (start + end) / 2;
			
			// Count the number of inversions from the left and right half.
			int leftCount = countInversion(arr, start, mid);
			int rightCount = countInversion(arr, mid + 1, end);

			// Count the number of inversions from the merge process.
			int mergeCount = merge(arr, start, mid, end);

			return leftCount + rightCount + mergeCount;
		}
	}

	private int merge(int[] arr, int start, int mid, int end) {
		int head1 = start, head2 = mid + 1;
		int size = end - start + 1;
		int[] result = new int[size];

		// The number of inversions in the merge process
		int count = 0;

		for (int i = 0; i < size; i++) {
			if (head1 == mid + 1) {
				result[i] = arr[head2++];
			} else if (head2 == end + 1) {
				result[i] = arr[head1++];
			} else if (arr[head1] <= arr[head2]) {
				result[i] = arr[head1++];
			} else {
				result[i] = arr[head2++];
				count += (mid - head1 + 1);
			}
		}

		System.arraycopy(result, 0, arr, start, size);

		return count;
	}

	public static void main(String[] args) {
		CountInversion counter = new CountInversion();
		int[] test = {1, 2, 10, 5, 3};
		
		System.out.println(
				"The number of inversions is " + counter.countInversion(test));
	}
}
