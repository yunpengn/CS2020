package sg.edu.nus.cs2020;

public class ThreeDice {
	public int[] values = null;
	public ThreeDice(int[] values) {
		this.values = values;
	}

	public int roll() {
		return values[(int) (Math.random() * 6)];
	}

	// Example from Lecture 02 Page 55
	public static void main(String[] args) {
		int[] valuesA = { 2, 2, 4, 4, 9, 9 };
		int[] valuesB = { 1, 1, 6, 6, 8, 8 };
		int[] valuesC = { 3, 3, 5, 5, 7, 7 };
		
		ThreeDice diceA = new ThreeDice(valuesA);
		ThreeDice diceB = new ThreeDice(valuesB);
		ThreeDice diceC = new ThreeDice(valuesC);

		final int MAX = 10000;
		int[] times = { 0, 0, 0 };

		for (int i = 0; i < MAX; i++) {
			int resultA = diceA.roll();
			int resultB = diceB.roll();
			int resultC = diceC.roll();
			System.out.printf("%d %d %d ", resultA, resultB, resultC);

			if (resultA > resultB && resultA > resultC) {
				System.out.printf("A wins the game!\n");
				times[0]++;
			} else if (resultB > resultA && resultB > resultC) {
				System.out.printf("B wins the game!\n");
				times[1]++;
			} else {
				System.out.printf("C wins the game!\n");
				times[2]++;
			}
		}

		System.out.printf("In total, during %d times of games, the result is:\n", MAX);
		System.out.println("A wins the game for " + times[0] + " times.");
		System.out.println("B wins the game for " + times[1] + " times.");
		System.out.println("C wins the game for " + times[2] + " times.");
	}
}
