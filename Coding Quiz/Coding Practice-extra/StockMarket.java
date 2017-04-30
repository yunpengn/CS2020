package coding_quiz;

/**
 * Public Class: StockMarket
 * 
 * Description: Given an array of integers, this method tries to find the
 * maximum value of arr[j]-arr[i], if i < j. This is also known as maximum
 * single-sell profit problem.
 * 
 * @author Niu Yunpeng
 */
public class StockMarket {
	// Maximum single-sell profit problem
	public int getMaxStock(int[] arr) throws IllegalArgumentException {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException();
		}

		// The minimum value we have seen so far
		int min = arr[0];
		// The maximum profit we have made so far
		int profit = 0;
		// The number of days
		int size = arr.length;
		
		// Dynamic programming or greedy algorithm
		for (int i = 0; i < size; i++) {
			int now = arr[i];
			
			if (now - min > profit) {
				profit = now - min;
			} else if (now < min) {
				min = now;
			}
		}
		
		return profit;
	}

	/*
	 * Correctness: Suppose we know the maximum profit for first k elements, how
	 * can we know the maximum profit for (k+1) elements? If the maximum profit
	 * changes to a new value, that means arr[k+1] has a greater difference with
	 * the minimum value so far; otherwise, we keep the original profit.
	 */
}
