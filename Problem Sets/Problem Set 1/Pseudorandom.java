/**
 * Class: Pseudorandom
 * 
 * Purpose: This class wants to test whether an implementation of linear feedback
 * shift register is good enough to be used as a pseudorandom number generator.
 * 
 * Static Method:
 * 		void main(String[] args):
 * 			For testing purpose, get the number of times 0's and 1's appear.
 * 
 * @author Niu Yunpeng
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

public class Pseudorandom {
	/**
	 * Description: For testing purpose, get the number of times 0's and 1's
	 * appear in a specific number of total times.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		ShiftRegister shifter = new ShiftRegister(9, 7);
		int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
		shifter.setSeed(seed);
		int numOfOnes = 0, numOfZeros = 0;
		final int MAX = 10000;
		
		// Get the number of times 0's and 1's appear in MAX times.
		for (int i = 0; i < MAX; i++) {
			if (shifter.shift() == 1) {
				numOfOnes++;
			} else {
				numOfZeros++;
			}
		}

		System.out.printf("In %d times, 1's appear for %d times.\n", MAX, numOfOnes);
		System.out.printf("In %d times, 0's appear for %d times.\n", MAX, numOfZeros);
		System.out.println("The ratio of 1's / 0's = " + 1.0 * numOfOnes / numOfZeros);

		/*
		 * In 10000 times, 1's appear for 5480 times. In 10000 times, 0's appear
		 * for 4520 times. The ratio of 1's / 0's = 1.2123893805309736
		 */
	}
}
