package coding_quiz;

import java.util.Stack;

/**
 * Public Class: MathExpression
 * 
 * Description: This class simulates a simple parser for math expression. It can
 * check whether the brackets match and calculate the result of this expression.
 * The expression must be fully parenthesized.
 * 
 * @author Niu Yunpeng
 */
public class MathExpression {
	private String expression;

	public MathExpression(String expression) throws IllegalArgumentException {
		if (expression == null || expression.length() == 0) {
			throw new IllegalArgumentException();
		}
		this.expression = expression;
	}

	/**
	 * Public Method: boolean isBalanced()
	 * 
	 * Description: This method checks whether the open and closing brackets
	 * match in a math expression.
	 * 
	 * @return true if the brackets match and false otherwise
	 */
	public boolean isBalanced() {
		// Take note of the number of different kinds of brackets
		int smallBrackets = 0;
		int midBrackets = 0;
		int largeBrackets = 0;

		// Length of the math expression
		int size = expression.length();
		
		for (int i = 0; i < size; i++) {
			char now = expression.charAt(i);

			// Check whether the current character is a bracket
			if (now == '(') {
				smallBrackets++;
			} else if (now == ')') {
				smallBrackets--;
			} else if (now == '[') {
				midBrackets++;
			} else if (now == ']') {
				midBrackets--;
			} else if (now == '{') {
				largeBrackets++;
			} else if (now == '}') {
				largeBrackets--;
			}

			if (smallBrackets < 0 || midBrackets < 0 || largeBrackets < 0) {
				return false;
			}
		}

		// Finally, if the open and close brackets match each other respectively
		if (smallBrackets == 0 && midBrackets == 0 && largeBrackets == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Public Method: int calculate()
	 * 
	 * Description: This method evaluates the math expression. The math
	 * expression must be fully parenthesized and only accept binary operators,
	 * including plus, minus, times and division.
	 * 
	 * @return the result of evaluating such an expression.
	 */
	public int calculate() throws IllegalArgumentException {
		if (!isBalanced()) {
			throw new IllegalArgumentException();
		} else {
			// Two stacks for storing the left operands and past operators
			Stack<Character> operators = new Stack<Character>();
			Stack<Integer> operands = new Stack<Integer>();

			// Length of the math expression
			int size = expression.length();

			for (int i = 0; i < size; i++) {
				char now = expression.charAt(i);

				if (Character.isDigit(now)) {
					// Push in a new operand
					int value = Character.digit(now, 10);
					operands.push(value);
				} else if (now == '+' || now == '-' || now == '*' || now == '/') {
					// Push in a new operator
					operators.push(now);
				} else if (now == ')' || now == ']' || now == '}') {
					// Closing bracket means a part of the expression finishes.
					int rightOperand = operands.pop();
					int leftOperand = operands.pop();
					char operator = operators.pop();
					int result = 0;

					// Evaluate the binary expression.
					if (operator == '+') {
						result = leftOperand + rightOperand;
					} else if (operator == '-') {
						result = leftOperand - rightOperand;
					} else if (operator == '*') {
						result = leftOperand * rightOperand;
					} else {
						result = leftOperand / rightOperand;
					}

					// Push the result back as an operand again.
					operands.push(result);
				}
			}

			// The outermost binary expression
			int rightOperand = operands.pop();
			int leftOperand = operands.pop();
			char operator = operators.pop();

			if (operator == '+') {
				return leftOperand + rightOperand;
			} else if (operator == '-') {
				return leftOperand - rightOperand;
			} else if (operator == '*') {
				return leftOperand * rightOperand;
			} else {
				return leftOperand / rightOperand;
			}
		}
	}
}
