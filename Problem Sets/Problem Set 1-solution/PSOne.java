/**
 * Problem Set 1, Problem 1
 * Sample solution
 * CS2020 2014
 */
package sg.edu.nus.cs2020;

/**
 * Class: PSOne
 * @author dcsslg
 * Description: Includes a static "MysteryFunction" that performs exponentiation
 */
public class PSOne {
	
	/**
	 * MysteryFunction performs exponentiation
	 * @param argA Base 
	 * @param argB Exponent
	 * @return pow(argA, argB)
	 * Description: Performs exponentiation by repeated squaring.  Runs in O(log n) time.
	 */
	static int MysteryFunction(int argA, int argB)
	{				
		int c = 1;		
		int d = argA;	
		int e = argB;
		while (e > 0)
		{			
			if (2*(e/2) !=e)
			{																
				c = c*d;			
			}
			d = d*d;			
			e = e/2;
		}				
		return c;
	}
	
	/**
	 * main tests the MysteryFunction
	 * @param args
	 * Description: Prints out the following string:
	 * "The answer is: 3125."
	 */
	public static void main(String args[])
	{
		// Calculate 5^5
		int output = MysteryFunction(5, 5);
		// Print out the answer
		System.out.printf("The answer is: " + output + ".");
	}
}
