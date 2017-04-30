/**
 * Class: ShiftRegisterRepeat
 * 
 * Purpose: This class tests how many times it needs for the shift register to
 * repeat itself. Meaning that, all the element values in the array seed are the
 * same as the initial values again.
 * 
 * Static Method:
 * 		void main(String[] args):
 * 			Test how many times it needs to repeat.
 * 
 * @author Niu Yunpeng
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

import java.util.Arrays;

public class ShiftRegisterRepeat {
	/**
	 * Description: For testing how many times it needs for the shift register
	 * to repeat itself. Meaning that, all the element values in the array seed
	 * are the same as the initial values again.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		ShiftRegister shifter = new ShiftRegister(9, 7);
		int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
		shifter.setSeed(seed);
		int i;
		shifter.shift();

		// Use the method Arrays.equals to test whether two arrays are equal.
		// The equality of two arrays means that every pair of two elements with
		// the same index has the same value and data type.
		for (i = 1; !Arrays.equals(seed, shifter.getBit()); i++) {
			shifter.shift();
		}

		System.out.printf("It takes %d times for this shift register to repeat.\n", i);
		// Result:
		// It takes 73 times for this shift register to repeat.
	}
}
