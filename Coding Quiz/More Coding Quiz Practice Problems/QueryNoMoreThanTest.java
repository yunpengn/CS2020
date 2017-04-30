package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class QueryNoMoreThanTest {
	QueryNoMoreThan tester = new QueryNoMoreThan();

	@Test
	public void simpleTest() {
		int[] arrA = {1, 3, 5, 7, 9};
		int[] arrB = {6, 4, 2, 8};
		int[] result = tester.query(arrA, arrB);
		int[] expected = {3, 2, 1, 4};
		
		assertEquals("Simple Test", true, Arrays.equals(expected, result));
	}
}
