package sg.edu.nus.cs2020;

import sg.edu.nus.cs2020.ListSimulator.TestType;

/**
 * Class: MyFastList
 * 
 * Description: This class inherits from FixedLengthList. However, it optimizes
 * the way that linear search is conducted. Basically, a ranking search is
 * implemented. Items in the lists will be ranked according to the times that
 * they have already been searched for. More times they were searched, the
 * higher their ranking is, the frontier they are in the list, the quicker they
 * can be found.
 * 
 * Inspiration: All of the famous search engines, like Google will calculate the
 * PR (Page Ranking) value so as to decide the order in which the search result
 * is displayed. The core idea is that, those popular things tend to become more
 * and more popular; the unpopular things tend to become less and less popular.
 * This is backed up by Matthew effect.
 * 
 * @author Niu Yunpeng
 */

public class MyFastList extends FixedLengthList {

	/*
	 * Stores the number of times each item has been searched. Two arrays in
	 * this class, m_list and times correspond to each other, i.e., times[3]
	 * stores the number of times that m_list[3] has been searched.
	 */
	int[] times = null;

	public MyFastList(int length) {
		// Inherits from the constructor of the super class.
		super(length);

		// Initializes the array times to be all 0.
		times = new int[length];
		for (int i = 0; i < length; i++) {
			times[i] = 0;
		}
	}
	/**
	 * Public Method: search(int x)
	 * 
	 * Description: A ranking linear search is implemented.
	 * 
	 * Property: The ranking of items in the list is according to the two rules
	 * below, in decreasing priority - a) The times that has been searched for;
	 * b) the last time that has been searched.
	 * 
	 * Example: If a and b have both been searched for 5 times, but recently
	 * only b has been searched, then b will rank higher than a.
	 * 
	 * @param x
	 *            is the value that is being searched for.
	 * 
	 * @return true if x is found and false otherwise.
	 */
	@Override
	public boolean search(int x) {
		int index = -1;

		for (int i = 0; i < m_length && m_list[i] != -1; i++) {
			if (m_list[i] == x) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			// Return false since x is not in the list.
			return false;
		} else {
			// x has been searched for one more time now.
			times[index]++;

			// Move x to the suitable position in the array.
			move(index);
			return true;
		}
	}

	// Every time the method search is called, the ranking is updated instantly.
	private void move(int index) {
		int value = m_list[index];
		int rank = times[index];
		int i = -1;
		
		for (i = index; i > 0; i--) {
			if (times[i - 1] <= times[i]) {
				times[i] = times[i - 1];
				m_list[i] = m_list[i - 1];
			} else {
				break;
			}
		}

		m_list[i] = value;
		times[i] = rank;
	}

	public static void compare() {
		// Initialize the three experiments
		FixedLengthList lRandom = new MyFastList(ListSimulator.LISTSIZE);
		ListSimulator tRandom = new ListSimulator(TestType.RANDOM, lRandom);

		FixedLengthList lUp = new MyFastList(ListSimulator.LISTSIZE);
		ListSimulator tUp = new ListSimulator(TestType.INCREASING, lUp);

		FixedLengthList lDown = new MyFastList(ListSimulator.LISTSIZE);
		ListSimulator tDown = new ListSimulator(TestType.DECREASING, lDown);

		tRandom.simulate();
		tUp.simulate();
		tDown.simulate();

		System.out.println("Total cost random: " + tRandom.getTotal());
		System.out.println("Total cost increasing: " + tUp.getTotal());
		System.out.println("Total cost decreasing: " + tDown.getTotal());
	}
}
