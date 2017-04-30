/**
 * Class: PSOne
 * 
 * Purpose: This classes includes a function that performs exponential operation. And test
 * it with one set of parameters.
 * 
 * Static Methods:
 * 		int MysteryFunction(int argA, int argB):
 * 			Returns the result of argA in power of argB. In other words, the result is the
 * 			same as Math.pow(int argA, int argB).
 * 		void main(String[] args):
 * 			For test purpose, display the result of MysteryFunction(5, 5) to standard output.
 * 
 * @author Niu Yunpeng
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

public class PSOne {
	/**
	 * 
	 * @param argA
	 *            first operator, the base of exponential
	 * @param argB
	 *            second operator, the power of exponential
	 * @return the result of argA in power of argB
	 */
	static int MysteryFunction(int argA, int argB) {
		int c = 1;
		int d = argA;
		int e = argB;
		
		// In consideration of time complexity, this is even better than
		// Math.pow in the sense that it repeats the operation "squaring and
		// divided by 2". The time complexity is no longer than linear.
		while (e > 0) {
			if (2 * (e / 2) != e) {
				c = c * d;
			}
			
			d = d * d;
			e = e / 2;
		}
		
		return c;
	}
	
	/**
	 * Description: For testing MysteryFunction, prints the result of
	 * MysteryFunction(5, 5).
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		int output = MysteryFunction(5, 5);
		System.out.printf("The answer is: " + output + ".");
	}
	
	// The answer is: 3125.
}