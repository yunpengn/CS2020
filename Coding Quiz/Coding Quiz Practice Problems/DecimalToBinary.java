package coding_quiz;

public class DecimalToBinary {
	public String systemConvert(int number) {
		return Integer.toBinaryString(number);
	}

	// Input must be positive
	public String manualConvert(int number) {
		int size = (int) (Math.log(number) / Math.log(2)) + 1;
		char[] digits = new char[size];

		for (int i = size - 1; i >= 0; i--) {
			digits[i] = (char) (number % 2 + 48);
			number /= 2;
		}

		return new String(digits);
	}
}
