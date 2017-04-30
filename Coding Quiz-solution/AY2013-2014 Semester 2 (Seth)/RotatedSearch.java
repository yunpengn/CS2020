package cs2020;

import java.util.Arrays;

/**
 * Class containing the static searchMax method for finding the 
 * maximum in a sorted, but rotated, array.
 * @author gilbert
 *
 */
public class RotatedSearch {
	
	/**
	 * searchMax
	 * @param <T>
	 * @param list an array of items
	 * Takes a list of items and returns the maximum item in the list.
	 * This is a generic method, and works for an array of any type of items.
	 * This is a static method.
	 * 
	 * The list of items is sorted from smallest to largest, but it has been
	 * rotated, i.e., the smallest item is not in array slot 0 and the sequence
	 * wraps around the end of the array.
	 */
//////////////////////////////////////////////////////////////////////////
//	Your solution goes here
//////////////////////////////////////////////////////////////////////////
	
	/**
	 * Method: Divide array by 2 and compare largest elements of both halves recursively
	 * @param A (rotated sorted array)
	 * @return Largest element in rotated sorted array
	 */
	public static <T extends Comparable<T>> T searchMax(T[]  A) {
		
		//Base cases: length == 2
		//Compare the two elements
		if (A.length == 2) {
			int comparison = A[0].compareTo(A[1]);
			//if element in leftArray was 'bigger', return that
			if (comparison > 0) {
				return A[0];
			//else return element in rightArray 
			//(since elements are distinct we do not consider comparison == 0)
			} else {
				return A[1];
			}
		//Base cases: length == 1
		//Return element
		} else if (A.length == 1) {
			return A[0];
			
		//Compare largest elements of both halves
		} else {
			T leftMax = searchMax(Arrays.copyOfRange(A, 0, A.length/2));
			T rightMax = searchMax(Arrays.copyOfRange(A, A.length/2, A.length));
			
			int leftRightComparison = leftMax.compareTo(rightMax);
			if (leftRightComparison > 0) {
				return leftMax;
			} else {
				return rightMax;
			}
		}
	}

	
//////////////////////////////////////////////////////////////////////////

	
	public static void main(String[] args){
		
		Integer[] integers = {39, 47, 53, 3, 13, 14, 16, 18, 25, 31};
		System.out.println(searchMax(integers));
		// Prints: 53
		
		Double[] doubles = {16.69, 23.89, 27.61, 33.05, 34.48, 36.63, 46.62, 5.96, 8.3, 11.44};
		System.out.println(searchMax(doubles));
		// Prints: 46.62
		
		String[] names = {"Franny", "Glen", "Harry", "Isabelle", "Julia", "Alice", "Bob", "Collin", "David", "Elissa"};
		System.out.println(searchMax(names));
		// Prints: Julia
		
	}

}
