package discussion_group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KnapSackTest {
	Knapsack tester = new Knapsack();

	@Test
	public void simpleTest1() {
		int[] values = { 13, 34, 61, 20, 25 };
		int[] weights = { 8, 4, 5, 6, 1 };
		int result = tester.knapsack(values, weights, 13);

		assertEquals("Simple Test 1", 120, result);
	}

	@Test
	public void simpleTest2() {
		int[] values = { 13, 34, 61, 20, 25, 72, 89, 123 };
		int[] weights = { 8, 3, 11, 2, 4, 5, 6, 1 };
		int result = tester.knapsack(values, weights, 20);

		assertEquals("Simple Test 1", 343, result);
	}
}
