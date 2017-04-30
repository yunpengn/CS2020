package coding_quiz;

import java.util.TreeSet;

public class MutableString {
	TreeSet<Character> str = null;

	// We may need an augmented tree to solve this problem. This is similar to
	// order statistic problem on the Lecture Notes.
	public MutableString(String str) throws IllegalArgumentException {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException();
		} else {
			this.str = new TreeSet<Character>();

			for (int i = 0; i < str.length(); i++) {
				char now = str.charAt(i);
				this.str.add(now);
			}
		}
	}

	public char get(int i) {
		return 0;
	}

	public void insert(int i, char c) {

	}

	public void reverse() {

	}
}
