package coding_quiz;

public class StackUnderflow {
	// Notice: This method does not check whether the parameter integer for push
	// is eligible. For example, the number may be illegal because of leading
	// zeroes like "u007".
	public boolean check(String operations) {
		int size = 0;
		// 1 stands for push "uXXX", 0 stands for pop "p", -1 stands for a digit
		int operation = 0;

		for (int i = 0; i < operations.length(); i++) {
			char now = operations.charAt(i);

			if (operation == 1) {
				// Last character is "u", must be followed by a number
				if (Character.isDigit(now)) {
					// Enlarge the size of the stack by 1
					size++;
					operation = -1;
				} else {
					return false;
				}
			} else if (operation == 0) {
				if (now == 'u') {
					// Push after pop
					operation = 1;
				} else if (now == 'p') {
					// Pop again after pop
					size--;
					// Check whether there are
					if (size < 0) {
						return false;
					}
				} else {
					// Last character is "p", must not followed by a number
					return false;
				}
			} else {
				// Last character is a digit
				if (Character.isDigit(now)) {
					;
				} else if (now == 'u') {
					operation = 1;
				} else if (now == 'p') {
					operation = 0;

					// Pop again after pop
					size--;
					// Check whether there are
					if (size < 0) {
						return false;
					}
				} else {
					return false;
				}
			}
		}

		// A series of operations cannot end with a "u".
		if (operation == 1) {
			return false;
		} else {
			return true;
		}
	}
}
