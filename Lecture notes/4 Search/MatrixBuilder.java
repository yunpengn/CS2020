package lectures;

import java.util.Random;

public class MatrixBuilder {
	public static void main(String[] args) {
		final int SIZE = 11;
		Random generator = new Random();

		for (int i = 0; i < SIZE; i++) {
			System.out.printf("{ ");
			for (int j = 0; j < SIZE - 1; j++) {
				System.out.printf("%d, ", generator.nextInt(100));
			}
			System.out.printf("%d },\n", generator.nextInt(100));
		}
	}
}
