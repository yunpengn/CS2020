package sg.edu.nus.cs2020;

/**
 * Public Class: QuestionGame
 * 
 * Description: This is the main class for running the game. It is based on the
 * abstract class QuestionGameBase.
 * 
 * @author gilbert
 *
 */
public class QuestionGame extends QuestionGameBase {
	/**
	 * Public Constructor: QuestionGame(String)
	 * 
	 * Description: This constructor reads in the QuestionObjects from a
	 * specific text file, chooses a random object, and simulate the game.
	 * 
	 * @param objectFileName
	 *            is the path to the database file.
	 */
	QuestionGame(String objectFileName) {
		super(objectFileName);
	}
	
	/**
	 * Public Method: void play(int)
	 * 
	 * Description: This method plays the game for a certain number of rounds,
	 * prints the relevant information, and calculates the average and maximum
	 * number of guesses to win the game. It will throw an exception if the
	 * player loses in any round of the game.
	 * 
	 * @param k
	 *            is the number of rounds to play the game.
	 */
	public void play(int k) {
		int maxGuess = 0;
		int totalGuess = 0;
		try {
			for (int i = 0; i < k; i++) {
				RestartGame();
				int queries = playGame();
				System.out.printf("Number of queries in round %d: %d\n", i, queries);

				// Update the maximum guess and total guesses if necessary
				if (queries > maxGuess) {
					maxGuess = queries;
				}
				totalGuess += queries;
			}
		} catch (Exception e) {
			System.out.println("Loser exception: " + e);
		}
		
		double average = totalGuess * 1.0 / k;

		System.out.println("=================================");
		System.out.printf("Congratulations! You have played %d rounds of games.\n", k);
		System.out.printf("The average number of guesses is %f.\n", average);
		System.out.printf("The maximum number of guesses is %d.\n", maxGuess);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
	}
}
