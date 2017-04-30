package discussion_group;

/**
 * Public Class: Subsequence
 * 
 * Description: This class implements a solution to the longest increasing
 * subsequence(LIS) problem.
 * 
 * @author Niu Yunpeng
 */
public class Subsequence {
	public int LIS(int[] arr) {
		int[] result = new int[arr.length];
		result[0] = 1;

		for (int n = 1; n < arr.length; n++) {
			int max = 0;

			for (int i = 0; i < n; i++) {
				if (arr[n] > arr[i] || result[i] + 1 > max) {
					max = result[i] + 1;
				}
			}

			result[n] = max;
		}

		return result[arr.length - 1];
	}
}
