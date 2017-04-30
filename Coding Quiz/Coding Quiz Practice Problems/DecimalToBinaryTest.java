package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class DecimalToBinaryTest {
	private DecimalToBinary tester = new DecimalToBinary();
	private Random generator = new Random();

	@Test
	public void systemTest() {
		int number = 7;
		String expected = "111";
		String result = tester.systemConvert(number);

		assertEquals("System Sample Test", expected, result);
	}

	@Test
	public void convertTest1() {
		int number = 23;
		String expected = tester.systemConvert(number);
		String result = tester.manualConvert(number);

		assertEquals("Convert Test 1", expected, result);
	}

	@Test
	public void convertTest2() {
		int number = 12345;
		String expected = tester.systemConvert(number);
		String result = tester.manualConvert(number);

		assertEquals("Convert Test 2", expected, result);
	}

	@Test
	public void randomTest() {
		final int times = 1000;
		final int BOUND = 100;
		
		for (int i = 0; i < times; i++) {
			int number = generator.nextInt(BOUND) + 1;
			String expected = tester.systemConvert(number);
			String result = tester.manualConvert(number);

			assertEquals("Random Test " + i, expected, result);
		}
	}
}
