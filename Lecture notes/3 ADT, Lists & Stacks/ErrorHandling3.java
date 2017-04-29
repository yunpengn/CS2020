package lectures;

public class ErrorHandling3 {
	public static int factorial(int n) throws IllegalArgumentException {
		int answer = 1;
		
		if (n < 0) {
		}

		for (int i = n; i > 0; i--) {
			answer *= i;
		}

		return answer;
	}

	public static void main(String[] args) {
		try {
			int test = factorial(5);
			System.out.println(test);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Factorial Exceiption:" + e);
		} finally {
			System.out.println("Finally, we finish this program.");
		}
	}
}
