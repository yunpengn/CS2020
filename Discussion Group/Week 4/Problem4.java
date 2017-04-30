package discussion_group;

public class Problem4 {
	public void SlowerFunction(int x) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println("Quack.");
			}
		}
	}

	public void FasterFunction(int x) {
		for (int i = 1; i < x; i *= 2) {
			System.out.println("Quack.");
		}
	}

	public int RecursiveFunctionToo(int x) {
		if (x < 2) {
			System.out.println("Quack.");
			return 1;
		} else {
			for (int j = 1; j < x; j++) {
				System.out.println("Quack.");
			}

			int a = RecursiveFunctionToo(x / 2);
			int b = RecursiveFunctionToo(x / 2);

			return a + b;
		}
	}
}
