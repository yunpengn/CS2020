package sg.edu.nus.cs2020;

import java.util.List;
import java.util.ArrayList;

/**
 * @author ary
 */

public class Precision implements IPrecision {
	
	// Stores the list of decimal points in our quotient
	private List<Integer> decimals;
	// Stores the start/end indexes for a valid pattern found
	private int patternStart, patternEnd;
	
	
	/**
	 * Description: Resets all variables and data structures for computation of new 1/n
	 */
	private void resetComputation() {
		decimals = new ArrayList<Integer>();
		patternStart = patternEnd = -1;
	}
	
	/**
	 * Description: Produces the string representation for the non-recurring segment of 1/n
	 * @param endOfNonRecurring - the first index that does not contain the non-recurring segment
	 * @return A string representation of non-recurring representation of 1/n
	 */
	private String formatNonRecurring(int endOfNonRecurring) {
		// Non-recurring segment begins with '0.'
		StringBuilder result = new StringBuilder("0.");
		// Insert non-recurring segment
		for (int i = 0; i < endOfNonRecurring; i++) {
			result.append(String.valueOf(decimals.get(i)));
		}
		// Print out non-recurring segment
		return result.toString();
	}
	
	/**
	 * Description: Produces the string representation for any recurring segment(s) of 1/n
	 * @return A string representation of recurring representation of 1/n
	 */
	private String formatRecurring() {
		// Recurring segment begins with '['
		StringBuilder result = new StringBuilder("[");
		// Insert pattern as a String value
		for (int i = patternStart; i <= patternEnd; i++) {
			result.append(String.valueOf(decimals.get(i)));
		}
		// Recurring segment begins with ']'
		result.append("]");
		return result.toString();
	}
	
	/**
	 * Description: Attempts to find a repeating pattern to confirm the presence of a repeating decimal.
	 * 				Marks out the pattern starting and ending indexes if a pattern is found.
	 * @param nextPoint - the next decimal point number to be generated
	 * @return True if a pattern is found, false otherwise
	 */
	private boolean patternFound(int nextPoint) {
		// Idea: To check for recurring, we need 2x of the pattern to exist, let this be patternTwice
		// Try all possible 2x patterns to match starting at index i, ending at the last digit
		for (int i = decimals.size() - 1; i >= 0; i--) {
			List<Integer> patternTwice = decimals.subList(i, decimals.size());
			// For a valid 2x pattern, we must be able to divide it into 2
			if (patternTwice.size() % 2 != 0) continue;
			// Obtain pattern to check for (i.e. 2nd half of 2x pattern)
			List<Integer> pattern = patternTwice.subList(patternTwice.size() / 2, patternTwice.size());
			// Check for a valid pattern (see if that is a repeat)
			boolean validPattern = true;
			for (int k = 0; k < pattern.size() && validPattern; k++) {
				validPattern = validPattern && (patternTwice.get(k) == pattern.get(k));
			}
			// Returns true if we have found a valid pattern (and mark it out)
			if (validPattern) {
				patternStart = i;
				patternEnd = i + patternTwice.size() / 2 - 1;
				// Very special case: avoid repeated digit analysis, works at least for 1 <= n <= 100
				if (patternStart == patternEnd && decimals.get(patternEnd) != nextPoint) continue;
				return true; 
			}
		}
		// No valid recurring pattern found
		return false; 
	}
	
	/**
	 * Description: Computes the string representation of 1/n as needed by Mr. B
	 * @param n - The denominator n such that the computation of 1/n is required
	 * @return A string representation of the precise representation of 1/n
	 */
	public String calculateInverse(int n) {
		// Reset previously used computations
		resetComputation();
		// Case 1: n = 1 - Handle separately
		if (n == 1) {
			return "1.0";
		}
		// Case 2: 1 < n <= 100 - Perform long division and check for repetition
		int dividend = 1;
		// Long Division for 10000 times (sufficient)
		while (true) {
			// Multiply n by 10
			dividend *= 10;
			// Obtain quotient new d.p. and update divided to be remainder
			decimals.add(dividend / n);
			dividend %= n;
			// Case 2a: Exact decimal confirmed (no recurring), proceed to format string
			if (dividend == 0) {
				return formatNonRecurring(decimals.size());
			// Case 2b: Recurring decimal may exist, proceed to check
			} else if (patternFound(dividend * 10 / n)) {
				return formatNonRecurring(patternStart) + formatRecurring();
			}
		}
		
		//return "Error";
	}
}
