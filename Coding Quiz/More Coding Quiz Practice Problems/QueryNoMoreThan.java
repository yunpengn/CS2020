package coding_quiz;

import java.util.Arrays;

/**
 * Public Class: QueryNoMoreThan
 * 
 * Description: Queries about less or equal elements. See more details at
 * http://codeforces.com/problemset/problem/600/B
 * 
 * @author Niu Yunpeng
 */
public class QueryNoMoreThan {
	/**
	 * Public Method: int[] query(int[], int[])
	 * 
	 * Description: Given two arrays, for each element B[i], this method
	 * calculates the number of elements in array A that is no more than B[i].
	 * 
	 * @return an array storing the result for each element in array B.
	 */
	public int[] query(int[] arrA, int[] arrB) {
		// Size of the second array
		int sizeB = arrB.length;
		Arrays.sort(arrA);

		// Array for storing results
		int[] result = new int[sizeB];
		
		for (int i = 0; i < sizeB; i++) {
			int num = Arrays.binarySearch(arrA, arrB[i]);
			
			if (num < 0) {
				result[i] = -num - 1;
			} else {
				result[i] = num;
			}
		}

		return result;
	}
}
