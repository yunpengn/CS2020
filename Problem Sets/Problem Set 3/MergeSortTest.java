package sg.edu.nus.cs2020;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MergeSortTest {
	@Test
	public void testSort1() {
		double[] testArray = {7.9, 7.2, 7.3, 1.0};
		double[] expectedArray = {1.0, 7.9, 7.2, 7.3};

		MergeSort.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray,expectedArray));
	}

	@Test
	public void testSort2() {
		double[] testArray = {7.9, 7.2, 1.0};
		double[] expectedArray = {1.0, 7.9, 7.2};

		MergeSort.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray, expectedArray));
	}

	@Test
	public void testSort3() {
		double[] testArray = {7.9, 7.2, 1.0, 0, -3.6, -3.7};
		double[] expectedArray = {-3.6, -3.7, 0, 1.0, 7.9, 7.2};

		MergeSort.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray, expectedArray));
	}
}