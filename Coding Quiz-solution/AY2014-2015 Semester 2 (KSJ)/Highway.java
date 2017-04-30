package cs2020;

import java.util.ArrayList;
import java.util.Collections;

public class Highway implements IHighway {
	ArrayList<Integer> customers;
	int length, begin, end;
	boolean sorted;

	Highway(int begin, int end) {
		// No error checks needed
		this.customers = new ArrayList<>();
		this.length = end - begin;
		this.begin = begin;
		this.end = end;
		sorted = true;
	}

	@Override
	public void addCustomer(int location) throws IllegalArgumentException {
		if (location < begin || location > end) {
			throw new IllegalArgumentException();
		} else {
			customers.add(location - begin);
		}
		sorted = false;
	}

	@Override
	public int numberOfStations(int range) throws IllegalArgumentException {
		if (range < 0) {
			throw new IllegalArgumentException();
		}
		if(customers.size() == 0) {
			return 0;
		}
		if (!sorted) {
			// This is important! Otherwise it will affecr runtime of findSmallestRange
			Collections.sort(customers);
			sorted = true;
		}
		int numStations = 1;
		int coverageEnd = 2 * range + customers.get(0);
		for (int i = 1; i < customers.size(); i++) {
			if (customers.get(i) <= coverageEnd)
				continue;
			else {
				numStations++;
				coverageEnd = customers.get(i) + 2 * range;
			}
		}
		return numStations;
	}

	@Override
	public int findSmallestRange(int numStations)
			throws IllegalArgumentException {
		if (numStations <= 0) {
			throw new IllegalArgumentException();
		}
		int begin = 0;
		int end = length;
		while (begin < end) {
			int mid = (begin + end) / 2;
			if (numberOfStations(mid) <= numStations) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		return begin;
	}

}
