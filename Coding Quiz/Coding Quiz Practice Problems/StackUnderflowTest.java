package coding_quiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StackUnderflowTest {
	private StackUnderflow tester = new StackUnderflow();

	@Test
	public void simpleTest1() {
		String expression = "u32pu1u7u10pp";
		boolean result = tester.check(expression);

		assertEquals("Simple Test 1", true, result);
	}

	@Test
	public void simpleTest2() {
		String expression = "u32pu1u7u10ppu007ppu99pp";
		boolean result = tester.check(expression);

		assertEquals("Simple Test 2", false, result);
	}
}
