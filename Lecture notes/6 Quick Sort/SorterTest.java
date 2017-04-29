package lectures;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class SorterTest {
	final int SIZE = 100000;
	final int BOUND = 100;

	private int[] intDataGenerator(int size, int bound) {
		int[] data = new int[size];
		Random generator = new Random();

		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(bound);
		}

		return data;
	}

	private boolean isIntSorted(int[] arr, int start, int end) {
		for (int i = start; i < end; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}

		return true;
	}

	@Test
	public void testQuickSort1() {
		Sorter<Integer> sort = new Sorter<Integer>();
		int[] origin = intDataGenerator(SIZE, BOUND);
		sort.quickSort1(origin, 0, SIZE - 1);

		assertEquals(true, isIntSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testQuickSort2() {
		Sorter<Integer> sort = new Sorter<Integer>();
		int[] origin = intDataGenerator(SIZE, BOUND);
		sort.quickSort2(origin, 0, SIZE - 1);

		assertEquals(true, isIntSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testQuickSort3() {
		Sorter<Integer> sort = new Sorter<Integer>();
		// The upper bound has been changed from BOUND to SIZE, since it can be
		// quite slow if there are so many duplicates.
		int[] origin = intDataGenerator(SIZE, SIZE);
		sort.quickSort3(origin, 0, SIZE - 1);
		System.out.println();

		assertEquals(true, isIntSorted(origin, 0, SIZE - 1));
	}
}