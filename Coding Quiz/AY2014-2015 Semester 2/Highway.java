package coding_quiz;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Public Class: Highway
 * 
 * Description: AY2014-2015 Semester 2 CS2020 Coding Quiz
 * 
 * @author Niu Yunpeng
 */
public class Highway implements IHighway {
	// To store the range of the highway.
	private int begin = 0;
	private int end = 0;

	// To store the locations of al customers.
	private ArrayList<Integer> customers = new ArrayList<Integer>();


	/**
	 * Public Constructor: Highway(int, int)
	 * 
	 * Description: This is the constructor for the Highway class, assuming that
	 * begin <= end.
	 * 
	 * @param begin
	 *            is the starting position of the range
	 * @param end
	 *            is the ending position of the range
	 */
	public Highway(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}


	/**
	 * Public Method: void addCustomer(int)
	 * 
	 * Description: This method adds a new customer to the class. If the
	 * location provided is not within the range, it will throw an illegal
	 * argument exception.
	 * 
	 * @param location
	 *            is the location of this new customer
	 * 
	 */
	public void addCustomer(int location) throws IllegalArgumentException {
		if (location < begin || location > end) {
			throw new IllegalArgumentException();
		}

		customers.add(location - begin);
	}


	/**
	 * Public Method: int numberOfStations(int)
	 * 
	 * Description: This method returns the minimum number of stations needed in
	 * order to cover all customers, given that the range of each tower is a
	 * constant. It will throw an illegal argument exception if the range input
	 * is not possible.
	 * 
	 * @param range
	 *            is the range of each tower given
	 */
	public int numberOfStations(int range) throws IllegalArgumentException {
		if (range < 0) {
			throw new IllegalArgumentException();
		}

		// Sort all customers by their location first.
		Collections.sort(customers);

		return findNumberOfStations(range);
	}


	/**
	 * Public Method: int findSmallestRange(int)
	 * 
	 * Description: This method returns the minimum range of each tower if we
	 * want to cover all customers, given that the number of towers is a
	 * constant. It will throw an illegal argument exception if the numStations
	 * input is not possible.
	 * 
	 * @param numStations
	 *            is the maximal number of stations available.
	 */
	public int findSmallestRange(int numStations) throws IllegalArgumentException {
		if (numStations < 0) {
			throw new IllegalArgumentException();
		}

		if (numStations == 0 && customers.size() != 0) {
			throw new IllegalArgumentException();
		}

		// Sort all customers by their location first.
		Collections.sort(customers);

		// First test the situation when range = 0
		if (findNumberOfStations(0) <= numStations) {
			return 0;
		}

		// Aggressive finding algorithm starts
		int range = 1;
		while (findNumberOfStations(range) > numStations) {
			range *= 2;
		}
		
		// Now, we now the answer should be within (range/2, range].
		// Notice that range here is no larger than 2 * (end - begin).
		// We define the range (range/2, range] just being computed.
		int start = range / 2 + 1;
		int end = range;
		int mid = (start + end) / 2;

		// Binary search algorithm begins
		while (start < end) {
			mid = (start + end) / 2;
			
			// If we can use towers of range mid to meet the constraint, we will
			// decrease the search to the smaller half.
			if (findNumberOfStations(mid) <= numStations) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}

		return start;
	}


	/**
	 * Private Method: int findNumberOfStations(int)
	 * 
	 * Description: In order to cover all customers along the highway, this
	 * method finds the minimal number of stations needed given that the range
	 * of each tower is constant. It should be called by numberOfStations and
	 * findSmallestRange
	 * 
	 * @param range
	 *            is the range of each tower given
	 * @return the minimal number of stations needed to cover all customers
	 */
	private int findNumberOfStations(int range) {
		// Greedy algorithm begins, starting from the first customer.
		int numCustomers = customers.size();

		// The first and last customer that the current tower will cover.
		// The index starts from 0.
		int startCustomer = 0;
		int endCustomer = 0;

		int startPosition = begin;
		int endPosition = begin;

		// The number of towers needed in total
		int result = 0;

		// Increment the number of towers by 1 each time
		while (endCustomer < numCustomers) {
			// Add one more tower
			result++;

			startPosition = customers.get(startCustomer);
			endPosition = startPosition + range * 2;

			// Go and find the last customer that the current tower can cover.
			while (customers.get(endCustomer) <= endPosition) {
				endCustomer++;

				// If we already cover the last customer, return the result
				if (endCustomer >= numCustomers) {
					return result;
				}
			}

			startCustomer = endCustomer;
		}

		return result;
	}
}
