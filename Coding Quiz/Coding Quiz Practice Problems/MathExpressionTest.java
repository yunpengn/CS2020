package coding_quiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathExpressionTest {
	@Test
	public void balancedTest1() {
		String expression = "(3+2)/(7/2)";
		MathExpression evaluator = new MathExpression(expression);
		boolean result = evaluator.isBalanced();

		assertEquals("isBalanced Test 1", true, result);
	}

	@Test
	public void balancedTest2() {
		String expression = "(3 + 2) / {7 / 2} * 5";
		MathExpression evaluator = new MathExpression(expression);
		boolean result = evaluator.isBalanced();

		assertEquals("isBalanced Test 2", true, result);
	}

	@Test
	public void balancedTest3() {
		String expression = "(3 + 2) / [7 / 2) * 5";
		MathExpression evaluator = new MathExpression(expression);
		boolean result = evaluator.isBalanced();

		assertEquals("isBalanced Test 3", false, result);
	}

	@Test
	public void balancedTest4() {
		String expression = "{(3 + 2) / ((7 / 2)) * 5";
		MathExpression evaluator = new MathExpression(expression);
		boolean result = evaluator.isBalanced();

		assertEquals("isBalanced Test 3", false, result);
	}

	@Test
	public void calcualteTest1() {
		String expression = "(3+2)/(7/2)";
		MathExpression evaluator = new MathExpression(expression);
		int result = evaluator.calculate();

		assertEquals("Caulcate Test 1", 1, result);
	}

	@Test
	public void calcualteTest2() {
		String expression = "(3+2)*7";
		MathExpression evaluator = new MathExpression(expression);
		int result = evaluator.calculate();

		assertEquals("Caulcate Test 2", 35, result);
	}

	@Test
	public void calcualteTest3() {
		String expression = "(((5 + 4) * 6) / ((2 + 3) * 4)) * 2";
		MathExpression evaluator = new MathExpression(expression);
		int result = evaluator.calculate();

		assertEquals("Caulcate Test 3", 4, result);
	}
}
