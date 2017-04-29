package lectures;

public class ErrorHandling2 {
	public static int factorial(int n) {
		if (n < 0) {
			System.out.println("Only non-negative numbers have factorials.");
			return -1;
		}

		int answer = 1;

		for (int i = n; i > 0; i--) {
			answer *= i;
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(factorial(-3));
	}
}
