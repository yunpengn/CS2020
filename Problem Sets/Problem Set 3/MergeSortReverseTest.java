package sg.edu.nus.cs2020;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MergeSortReverseTest {
	@Test
	public void testSortReverse1() {
		double[] testArray = {7.9, 7.2, 1.0};
		double[] expectedArray = {1.0, 7.2, 7.9};

		MergeSortReverse.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray, expectedArray));
	}

	@Test
	public void testSortReverse2() {
		double[] testArray = {7.9, 7.2, 7.3, 1.0};
		double[] expectedArray = {1.0, 7.3, 7.2, 7.9};

		MergeSortReverse.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray, expectedArray));
	}

	@Test
	public void testSortReverse3() {
		double[] testArray = {7.9, 7.2, 1.0, 0, -3.6, -3.7};
		double[] expectedArray = {-3.7, -3.6, 0, 1.0, 7.2, 7.9};

		MergeSortReverse.sort(testArray);
		assertTrue("Sample testcase", Arrays.equals(testArray, expectedArray));
	}
}
