package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class QuickSelectTests {
	final int SIZE = (int) Math.pow(10, 7);
	final int BOUND = SIZE / 100;

	private static int[] intDataGenerator(int size, int bound) {
		int[] data = new int[size];
		Random generator = new Random();

		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(bound);
		}

		return data;
	}

	@Test
	public void simpleTest() {
		int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int x = 3;
		int result = 0;
		
		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}
		
		assertEquals("Basic select test", 3, result);
	}

	@Test
	public void errorTest1() {
		int[] testArray = null;
		int x = 3;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Error test 1", 0, result);
	}

	@Test
	public void errorTest2() {
		int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int x = -1;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Error test 2", 0, result);
	}

	@Test
	public void errorTest3() {
		int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int x = 100;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Error test 3", 0, result);
	}

	@Test
	public void bigDataTest() {
		int[] testArray = QuickSelectTests.intDataGenerator(SIZE, BOUND);
		int[] sortedArray = new int[SIZE];
		System.arraycopy(testArray, 0, sortedArray, 0, SIZE);
		Arrays.sort(sortedArray);

		int x = 1234 % SIZE;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Big Data test", sortedArray[x - 1], result);
	}

	@Test
	public void firstElementTest() {
		int[] testArray = QuickSelectTests.intDataGenerator(SIZE, BOUND);
		int[] sortedArray = new int[SIZE];
		System.arraycopy(testArray, 0, sortedArray, 0, SIZE);
		Arrays.sort(sortedArray);

		int x = 1;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("First Element test", sortedArray[x - 1], result);
	}

	@Test
	public void lastElementTest() {
		int[] testArray = QuickSelectTests.intDataGenerator(SIZE, BOUND);
		int[] sortedArray = new int[SIZE];
		System.arraycopy(testArray, 0, sortedArray, 0, SIZE);
		Arrays.sort(sortedArray);

		int x = SIZE;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Last Element test", sortedArray[x - 1], result);
	}

	@Test
	public void lotsOfDuplicatesTest() {
		int[] testArray = QuickSelectTests.intDataGenerator(SIZE, 10);
		int[] sortedArray = new int[SIZE];
		System.arraycopy(testArray, 0, sortedArray, 0, SIZE);
		Arrays.sort(sortedArray);

		int x = 34567 % SIZE;
		int result = 0;

		try {
			result = QuickSelect.select(testArray, x);
		} catch (NotFoundException e) {
			assert (true);
		}

		assertEquals("Duplicating test", sortedArray[x - 1], result);
	}
}
