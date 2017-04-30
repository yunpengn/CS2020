package discussion_group;

public class MaxSumSubarray {
	public int MaxSubarray(int[] arr) {
		return MaxSubarray(arr, arr.length - 1);
	}

	private int MaxSubarray(int[] arr, int n) {
		if (n == 0) {
			return 0;
		} else {
			return Math.max(MaxSubarray(arr, n - 1), arr[n]);
		}
	}
}
