/**
 * Class: ShiftRegister
 * 
 * Purpose: This class implements the interface ILFShiftRegister. This is a simple
 * linear feedback shift register.
 * 
 * Constructor:
 * 		ShiftRegister(int size, int tap):
 * 			size is the size of seeds, tap is the index of the tap bit.
 * 
 * Public Methods:
 * 		void setSeed(int[] seed):
 * 			Receives the initial value of seeds.
 * 		int shift():
 * 			Returns the feedback bit after one time of shift operation.
 * 		int generate(int k):
 * 			Shift for n times and get a base-2 number according to the feedback of each
 *			shift. Converts to base-10 and return it.
 *		int[] getBit():
 *			Returns all bits as an array.
 *
 * Private Method:
 * 		int[] inputVerify(int[] origin):
 * 			Returns the seed after filtering illegal input.
 * 
 * @author Niu Yunpeng
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

public class ShiftRegister implements ILFShiftRegister {
	// The size of all seeds in the register.
	private int size = 0;

	// The index of the tap bit.
	private int tap = 0;

	// Stores all the values of seeds in an array.
	private int[] seed = null;
	
	/**
	 * Description: Constructor of this class. Determine and initialize the
	 * value of size and tap.
	 * 
	 * @param size
	 *            The size of all seeds in the register.
	 * @param tap
	 *            The index of the tap bit.
	 */
	public ShiftRegister(int size, int tap) {
		if (size > 0 && tap >= 0 && tap < size) {
			this.size = size;
			this.tap = tap;
			seed = new int[size];
		} else {
			System.err.println("Error: Invalid input of size or tap.");
		}
	}

	/**
	 * Description: Verify the input array so that wrong input are filtered.
	 * 
	 * @param origin
	 *            The original input array of seeds.
	 * @return The verified array of seeds, filtering out illegal input.
	 */
	private int[] inputVerify(int[] origin) {
		int[] verified = new int[size];

		// The original size of input array, may not be necessarily the same as
		// this.size
		int inputSize = origin.length;

		/*
		 * We are quite friendly for illegal input here. We try to accept them
		 * and converts them into the values that we can use.
		 * 
		 * The following 3 scenarios are targeted here: 1) When the length of
		 * the input array is longer than required size, the remaining elements
		 * are ignored; 2) When the length of the input array is shorter than
		 * required size, the remaining elements are set as 0; 3) When the
		 * values of elements in the input array is other than 0 and 1, any
		 * value other than 0 is considered as 1 in the result array.
		 */
		for (int i = 0; i < size; i++) {
			if (i >= inputSize) {
				verified[i] = 0;
			} else {
				verified[i] = origin[i] == 0 ? 0 : 1;
			}
		}

		return verified;
	}

	/**
	 * Description: Sets the value of the shift register to the specified seed.
	 * The values of seeds will be verified by the method inputVerify.
	 * 
	 * @param seed
	 *            The original input array of seeds
	 */
	public void setSeed(int[] seed) {
		if (size > 0) {
			this.seed = inputVerify(seed);
		} else {
			System.err.println("Error: The register has not been initialized with a valid size and tap.");
		}
	}

	/**
	 * Description: Shifts the register one time, returning the low-order bit.
	 * 
	 * @return Returns the value of the feedback bit.
	 */
	public int shift() {
		if (size == 0) {
			System.err.println("Error: The register has not been initialized with a valid size and tap.");
			return 0;
		} else {
			// Do the XOR operation on the leftmost bit and tap bit.
			int feedback = seed[size - 1] ^ seed[tap];

			// Shift each bit to the left for one time.
			for (int i = size - 1; i > 0; i--) {
				seed[i] = seed[i - 1];
			}

			// Set the rightmost bit as the feedback bit.
			seed[0] = feedback;

			return seed[0];
		}
	}

	/**
	 * Description: Get a base-2 number by shifting k times, convert this number
	 * to base-10 and return it.
	 * 
	 * @param k
	 *            The times of shift operation.
	 * @return A base-10 number based on the values of feedback bits.
	 */
	public int generate(int k) {
		if (size == 0) {
			System.err.println("Error: The register has not been initialized with a valid size and tap.");

			// Cannot return 0 here since the result will also 0 if k is 0. Need
			// to differentiate these two situations here.
			return -1;
		} else {
			int sum = 0;

			// Converts the base-2 number of base-10.
			for (int i = 0; i < k; i++) {
				sum = sum * 2 + shift();
			}

			return sum;
		}
	}
	
	/**
	 * Description: This method makes it possible to access the values of all
	 * seed from outside the class.
	 * 
	 * @return Get the current values in the shift register
	 */
	public int[] getBit() {
		return seed;
	}
}
