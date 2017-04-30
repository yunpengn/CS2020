package sg.edu.nus.cs2020;

import sg.edu.nus.cs2020.ListSimulator.TestType;

public class MoveToFrontList extends FixedLengthList {
	public MoveToFrontList(int length) {
		// Inherits from the constructor of the super class.
		super(length);
	}

	@Override
	public boolean search(int x) {
		int result = -1;

		for (int i = 0; i < m_length && m_list[i] != -1; i++) {
			if (m_list[i] == x) {
				result = i;
				break;
			}
		}

		if (result == -1) {
			// Return false since x is not in the list
			return false;
		} else {
			// Move the element being searched to the front of the list
			moveFront(result);
			return true;
		}
	}

	private void moveFront(int index) {
		int temp = m_list[index];

		// Shift every intervening item back one slot
		for (int i = index; i > 0; i--) {
			m_list[i] = m_list[i - 1];
		}

		// Move the selected value to the front slot
		m_list[0] = temp;
	}

	public static void compare() {
		// Initialize the three experiments
		FixedLengthList lRandom = new MoveToFrontList(ListSimulator.LISTSIZE);
		ListSimulator tRandom = new ListSimulator(TestType.RANDOM, lRandom);

		FixedLengthList lUp = new MoveToFrontList(ListSimulator.LISTSIZE);
		ListSimulator tUp = new ListSimulator(TestType.INCREASING, lUp);

		FixedLengthList lDown = new MoveToFrontList(ListSimulator.LISTSIZE);
		ListSimulator tDown = new ListSimulator(TestType.DECREASING, lDown);

		tRandom.simulate();
		tUp.simulate();
		tDown.simulate();

		System.out.println("Total cost random: " + tRandom.getTotal());
		System.out.println("Total cost increasing: " + tUp.getTotal());
		System.out.println("Total cost decreasing: " + tDown.getTotal());
	}
}
