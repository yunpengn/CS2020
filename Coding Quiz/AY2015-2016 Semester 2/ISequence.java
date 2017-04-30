/**
 * CS2020 Coding Quiz 2016: Happy Numbers and Sad Numbers
 * 
 * @author gilbert
 */
package coding_quiz;

import java.util.Iterator;

public interface ISequence {
	// Returns the next value in the sequence after n
	public int nextValue(int n);
	
	// Returns true if there is a cycle in the first "length" steps of the
	// sequence and false otherwise.
	public boolean cycleExist(int n, int length);
	
	// Computes the smallest value in the cycle reached by the sequence starting
	// at n, if a cycle is reached in the first "length" steps.
	public int smallestValueInCycle(int n, int length);
	
	// Prints a sad cycle, beginning at the smallest value in the cycle. If
	// within the integers {1..max} there is no sad number with a cycle within
	// the first "length" integers, print "None."
	public void printSadCycle(int max, int length);
	
	// Return an iterator for the sequence beginning with n.
	public Iterator<Integer> iterator(int n);
}
