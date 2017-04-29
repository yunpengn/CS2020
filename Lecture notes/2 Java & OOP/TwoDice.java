package sg.edu.nus.cs2020;

public class TwoDice {
	public int[] values = null;

	public TwoDice(int[] values) {
		this.values = values;
	}

	public int roll() {
		return values[(int) (Math.random() * 6)];
	}

	// Example from Lecture 02 Page 55
	public static void main(String[] args) {
		int[] valuesA = { 3, 3, 5, 5, 7, 7 };
		int[] valuesC = { 1, 1, 6, 6, 8, 8 };

		ThreeDice diceA = new ThreeDice(valuesA);
		ThreeDice diceC = new ThreeDice(valuesC);

		final int MAX = 10000;
		int[] times = { 0, 0 };

		for (int i = 0; i < MAX; i++) {
			int resultA = diceA.roll();
			int resultC = diceC.roll();
			System.out.printf("%d %d ", resultA, resultC);

			if (resultA > resultC) {
				System.out.printf("A wins the game!\n");
				times[0]++;
			} else {
				System.out.printf("C wins the game!\n");
				times[1]++;
			}
		}

		System.out.printf("In total, during %d times of games, the result is:\n", MAX);
		System.out.println("A wins the game for " + times[0] + " times.");
		System.out.println("C wins the game for " + times[1] + " times.");
	}
}
