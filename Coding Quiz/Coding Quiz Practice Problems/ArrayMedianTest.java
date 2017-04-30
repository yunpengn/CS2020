package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class ArrayMedianTest {
	ArrayMedian tester = new ArrayMedian();
	Random generator = new Random();
	final int BOUND = 200;

	@Test
	public void simpleTest1() {
		int[] arr1 = {1, 2, 5, 7, 8};
		int[] arr2 = {3, 4, 6, 9, 10};
		int result = tester.median(arr1, arr2);

		assertEquals("Simple Test 1", 5, result);
	}

	@Test
	public void simpleTest2() {
		int[] arr1 = {57, 68, 74, 75, 80, 96, 104, 115, 118, 125};
		int[] arr2 = {76, 84, 85, 88, 97, 105, 106, 112, 130, 140};
		int result = tester.median(arr1, arr2);

		assertEquals("Simple Test 2", 97, result);
	}

	@Test
	public void errorTest1() {
		int[] arr1 = {};
		int[] arr2 = {1, 4, 6, 7, 9};
		int result = tester.median(arr1, arr2);

		assertEquals("Error Test 1", 6, result);
	}

	@Test
	public void errorTest2() {
		int[] arr1 = null;
		int[] arr2 = {1, 4, 6, 7, 9};
		
		try {
			int result = tester.median(arr1, arr2);
			System.out.println(result);
		} catch (Exception e) {
			System.err.println("Illegal argument. Failed.");
		}
	}

	@Test
	public void randomDataTest() {
		final int size = 20;
		int[] arr1 = generateData(size);
		int[] arr2 = generateData(size);
		int result = tester.median(arr1, arr2);
		int[] merged = merge(arr1, arr2);
		int expected = merged[(merged.length - 1) / 2];

		assertEquals("Random Data Test", expected, result);
	}

	private int[] merge(int[] arr1, int[] arr2) {
		int size1 = arr1.length;
		int size2 = arr2.length;
		int[] result = new int[size1 + size2];
		int index1 = 0;
		int index2 = 0;
		
		for (int i = 0; i < size1 + size2; i++) {
			if (index1 == size1) {
				result[i] = arr2[index2++];
			} else if (index2 == size2) {
				result[i] = arr1[index1++];
			} else if (arr1[index1] <= arr2[index2]) {
				result[i] = arr1[index1++];
			} else {
				result[i] = arr2[index2++];
			}
		}

		return result;
	}

	// Generate n sorted random numbers and store them in an array.
	private int[] generateData(int size) {
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			result[i] = generator.nextInt(BOUND);
		}

		Arrays.sort(result);

		return result;
	}
}
