package lectures;

public class ErrorHandling1 {
	public static int factorial(int n) {
		assert (n >= 0) : "Only non-negative numbers have factorials.";
		
		int answer = 1;
		
		for (int i = n; i > 0; i--) {
			answer *= i;
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(factorial(-1));
	}
}
