package coding_quiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StockMarketTest {
	StockMarket tester = new StockMarket();

	@Test
	public void simpleTest() {
		int[] arr = {1, 4, 6, 9, 5, 2, 3};
		int result = tester.getMaxStock(arr);

		assertEquals("Simple Test", 8, result);
	}

	@Test
	public void mountainTest() {
		int[] arr = new int[1999];

		for (int i = 0; i < 1000; i++) {
			arr[i] = 10 * i;
			arr[1998 - i] = 10 * i;
		}

		int result = tester.getMaxStock(arr);

		assertEquals("Simple Test", 9990, result);
	}
}
