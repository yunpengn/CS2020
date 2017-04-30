package discussion_group;

public class ArraySum {
	public boolean isSum(int[] arr, int sum) {
		return isSum(arr, 0, arr.length - 1, sum);
	}

	private boolean isSum(int[] arr, int start, int end, int sum) {
		if (start >= end) {
			return sum == 0;
		} else {
			return isSum(arr, start, end - 1, sum - arr[end]) || 
				   isSum(arr, start, end - 1, sum);
		}
	}
}
