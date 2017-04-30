/**
 * Class: StringEncode
 * 
 * Purpose: This class transforms a string into a binary array (an array of 0's and
 * 1's). Each character in the string is converted into 8 bits according to their 
 * value of charSet code in the current platform.
 * 
 * Static Method:
 * 		void main(String[] args):
 * 			For testing purpose, get an array of 0's and 1's based on a string.
 * 
 * Public Method:
 * 		int[] stringToBinaryArray(String input):
 * 			Returns 
 *
 * Private Method:
 * 		int[] charToBinaryArray(char c):
 * 			Returns 
 * 
 * @author Niu Yunpeng
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

public class StringEncode {
	/**
	 * Description: Converts a character to an array of 8 integers each of which
	 * is either 0 or 1 according to its code in the current platform charSet.
	 * 
	 * @param c
	 *            A single character to convert.
	 * @return an array of 0's and 1's with length of 8.
	 */
	private int[] charToBinaryArray(char c) {
		int[] binaries = new int[8];
		int i = 0;
		int charCode = (int) c;

		// Convert a base-10 number into base-2 and make sure the length is
		// exactly is 8.
		while (charCode > 0) {
			binaries[i] = charCode % 2;
			charCode = charCode / 2;
			i++;
		}

		return binaries;
	}

	/**
	 * Description: Convert a string into a binary array by converting each
	 * character in the string using the method charToBinaryArray.
	 * 
	 * Property: Assuming that the length of the string is n, the length of the
	 * total array is 8 * n.
	 * 
	 * @param input
	 *            A string of arbitrary length to convert.
	 * @return An array of 0's and 1's with length of 8 * n, where n is the
	 *         length of the input string.
	 */
	public int[] stringToBinaryArray(String input) {
		int strLen = input.length();
		int arrLen = 8 * strLen;

		int[] binaries = new int[arrLen];
		char[] chars = new char[strLen];
		int[] tempBinaries = new int[8];

		// Separate the string into an array of characters.
		input.getChars(0, strLen, chars, 0);

		for (int i = 0; i < strLen; i++) {
			tempBinaries = charToBinaryArray(chars[i]);

			for (int j = 0; j < 8; j++) {
				binaries[i * 8 + j] = tempBinaries[j];
			}
		}

		return binaries;
	}

	/**
	 * Description: For testing purpose, get an array of 0's and 1's based on a
	 * string.
	 * 
	 * @param args
	 *            an array of command line arguments
	 */
	public static void main(String[] args) {
		StringEncode encoder = new StringEncode();
		int[] seed = encoder.stringToBinaryArray("Singapore");
		
		for (int i : seed) {
			System.out.printf("%d ", i);
		}
	}
}
