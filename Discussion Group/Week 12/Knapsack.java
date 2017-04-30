package discussion_group;

import java.util.Arrays;

/**
 * Public Class: Knapsack
 * 
 * Description: This class implements a simple solution for 0-1 knapsack
 * problem. Given n items, each has a weight and a value; and a bag that can
 * hold up to certain weight. This problem wants to know which of the items
 * should be held in the bag.
 * 
 * @author Niu Yunpeng
 */
public class Knapsack {
	/**
	 * Public Method: Knapsack(int[], int[], int)
	 * 
	 * Description: This method computes the maximum value of all items in the
	 * bag if their total weight does not exceed a certain limit. Dynamic
	 * programming is used in it.
	 * 
	 * @param values
	 *            are the values for respective items.
	 * @param weights
	 *            are the weights of respective items.
	 * @param limit
	 *            is the maximum weight the bag can hold.
	 * 
	 * @return the maximum value of all items in the bag.
	 */
	public int knapsack(int[] values, int[] weights, int limit) {
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException("The array of values is empty.");
		} else if (weights == null || weights.length == 0) {
			throw new IllegalArgumentException("The array of weights is mepty.");
		} else if (limit < 0) {
			String message = "The weight limit of the bag must be non-negative.";
			throw new IllegalArgumentException(message);
		} else if (values.length != weights.length) {
			String message = "The number of items in two arrays are different.";
			throw new IllegalArgumentException(message);
		}

		// The number of items.
		int num = values.length;
		// The 2D array to store the results of optimal sub-problems.
		int[][] result = new int[num + 1][limit + 1];

		// Initialization: When there is no item in the bag, no matter the
		// weight limit, the value should always be 0.
		Arrays.fill(result[0], 0);
		
		// Builds the memory table in a bottom-up manner.
		for (int n = 1; n <= num; n++) {
			// When the weight limit is 0, we cannot hold anything in the bag.
			result[n][0] = 0;

			for (int w = 1; w <= limit; w++) {
				// Notice: result index starts from 1, while values and weights
				// index start from 0.
				if (w >= weights[n - 1]) {
					// Returns the larger one between including the current item
					// and excluding the current item.
					result[n][w] = Math.max(result[n - 1][w], 
							values[n - 1] + result[n - 1][w - weights[n - 1]]);
				} else {
					// The bag is not large enough the hold the current item,
					// has to exclude it.
					result[n][w] = result[n - 1][w];
				}
			}
		}

		// The final problem is that: we have all of the items, and the weight
		// limit is equal to the parameter, so we return the last cell.
		return result[num][limit];
	}
}
