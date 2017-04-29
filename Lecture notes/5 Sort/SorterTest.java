package lectures;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class SorterTest {
	final int SIZE = 20000;
	final int BOUND = 100;

	private Integer[] integerDataGenerator(int size, int bound) {
		Integer[] data = new Integer[size];
		Random generator = new Random();

		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(bound);
		}

		return data;
	}

	private int[] intDataGenerator(int size, int bound) {
		int[] data = new int[size];
		Random generator = new Random();

		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(bound);
		}

		return data;
	}

	private <DataType extends Comparable<DataType>> boolean isSorted(
			DataType[] arr, int start, int end) {

		for (int i = start; i < end; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0) {
				return false;
			}
		}

		return true;
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
	public void testSelectionSort() {
		Sorter<Integer> sort = new Sorter<Integer>();
		Integer[] origin = integerDataGenerator(SIZE, BOUND);
		sort.selectionSort(origin, 0, SIZE - 1);

		assertEquals(true, isSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testInsertionSort() {
		Sorter<Integer> sort = new Sorter<Integer>();
		Integer[] origin = integerDataGenerator(SIZE, BOUND);
		sort.insertionSort(origin, 0, SIZE - 1);

		assertEquals(true, isSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testBubbleSort() {
		Sorter<Integer> sort = new Sorter<Integer>();
		Integer[] origin = integerDataGenerator(SIZE, BOUND);
		sort.bubbleSort(origin, 0, SIZE - 1);

		assertEquals(true, isSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testMergeSort() {
		Sorter<Integer> sort = new Sorter<Integer>();
		int[] origin = intDataGenerator(SIZE, BOUND);
		sort.mergeSort(origin, 0, SIZE - 1);

		assertEquals(true, isIntSorted(origin, 0, SIZE - 1));
	}

	@Test
	public void testMergeSort2() {
		Sorter<Integer> sort = new Sorter<Integer>();
		int[] origin = intDataGenerator(SIZE, BOUND);
		sort.mergeSort2(origin, 0, SIZE - 1);

		assertEquals(true, isIntSorted(origin, 0, SIZE - 1));
	}
}
