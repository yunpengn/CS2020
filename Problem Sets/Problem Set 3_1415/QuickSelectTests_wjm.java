package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class QuickSelectTests {

	@Test
	public void test() throws Exception {
		int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int x = 3;
		assertEquals("Basic select test", 3, QuickSelect.select(testArray, x));
	}

	int size = 500;

	@Test
	public void testSelectLargeArray() throws Exception {
		int[] arr = new int[size];
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			arr[i] = r.nextInt();
		}

		int index = (int) (Math.random() * size + 1);
		int result = QuickSelect.select(arr, index);
		Arrays.sort(arr);
		assertEquals("Select with large arrays, random, dupilicate and negative elements", result, arr[index - 1]);
	}

	@Test
	public void testErrorInput() {
		int[] testArray = {};
		int x = 1;
		try {
			QuickSelect.select(testArray, x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFirst() throws Exception {
		int[] arr = new int[size];
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			arr[i] = r.nextInt();
		}

		int result = QuickSelect.select(arr, 1);
		Arrays.sort(arr);
		assertEquals("Select the first element", result, arr[0]);
	}

	@Test
	public void testLast() throws Exception {
		int[] arr = new int[size];
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			arr[i] = r.nextInt();
		}

		int result = QuickSelect.select(arr, arr.length);
		Arrays.sort(arr);
		assertEquals("Select the last element", result, arr[arr.length - 1]);

	}

	@Test
	public void testLotsofduplicates() throws Exception {
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = (int) (Math.random() * 10);
		}

		int index = (int) (Math.random() * size);
		int result = QuickSelect.select(arr, index);
		Arrays.sort(arr);
		assertEquals("select in an array with lots of duplicates", result, arr[index - 1]);
	}
}
