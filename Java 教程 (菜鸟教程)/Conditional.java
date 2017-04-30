package tutorial;

public class Conditional {
	public static void main(String[] args) {
		int x = 10;

		if (x < 5) {
			System.out.println("x is smaller than 5.");
		} else if (x < 10) {
			System.out.println("x is smaller than 10.");
		} else {
			System.out.println("x is equal or greater than 10.");
		}

		switch (x) {
		case 5:
			System.err.println("The value of x is 5.");
			break;

		case 10:
			System.out.println("The value of x is 10.");
			break;

		default:
			System.out.println("The value of x is unknown.");
			break;
		}
	}
}
