package lectures;

import java.util.Stack;

// This class is for demonstration purpose only. With no guarantee of WANRRANTY.
public class StackSort {
	public static Stack<Integer> sort(Stack<Integer> input) {
		Stack<Integer> output = new Stack<Integer>();
		
		while (!input.isEmpty()) {
			int now = input.pop();

			while (!output.isEmpty() && output.peek() > now) {
				input.push(output.pop());
			}
			output.push(now);
		}

		return output;
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();

		test.push(4);
		test.push(1);
		test.push(3);
		test.push(2);
		test.push(6);
		test.push(5);
		sort(test);
		System.out.println("Stack sort finished.");
	}
}
