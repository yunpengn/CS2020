package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class CountInversionTest {
	private CountInversion<Integer> tester = new CountInversion<Integer>();
	private Random generator = new Random();

	@Test
	public void inversionTest1() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(10);
		arr.add(5);
		arr.add(3);

		int expected = 3;
		int result = tester.count(arr);

		assertEquals("Simple Inversion - Test 1-1", expected, result);
	}

	@Test
	public void inversionTest2() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		final int SIZE = 1000;
		final int BOUND = 60;
		
		for (int i = 0; i < SIZE; i++) {
			arr.add(generator.nextInt(BOUND));
		}
		
		int result1 = countInversions(arr);
		int result2 = tester.count(arr);

		assertEquals("Simple Inversion - Test 2", result1, result2);
	}

	/**
	 * Public Static Method: int countInversions(ArrayList<Integer>)
	 * 
	 * Description: This method calculates the number of inversions in a given
	 * array. It uses a brute-force approach and the time complexity is O(n^2).
	 * 
	 * @param intArray
	 *            is the input array of integers.
	 * 
	 * @return the number of inversions in a given array.
	 */
	public static int countInversions(ArrayList<Integer> intArray) {
		// Count the number of inversions
		int count = 0;

		// Size of the array list.
		int size = intArray.size();

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (intArray.get(i) > intArray.get(j)) {
					count++;
				}
			}
		}

		return count;
	}
}
