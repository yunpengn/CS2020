package sg.edu.nus.cs2020;

import sg.edu.nus.cs2020.ListSimulator.TestType;

public class NotFoundList extends FixedLengthList {
	public NotFoundList(int length) {
		super(length);
	}

	@Override
	public boolean add(int key) throws LengthExceedException {
		/*
		 * Notice: If key = 0, it indicates that this method must be called by
		 * the end-user rather than the search method. The search method will
		 * only add -k when k is strictly smaller than 0.
		 */
		if (key >= 0) {
			return super.add(key);
		} else {
			m_max++;

			if (m_max < m_length) {
				for (int i = m_max; i > 0; i--) {
					m_list[i] = m_list[i - 1];
				}
				m_list[0] = key;

				return true;
			} else {
				throw new LengthExceedException();
			}
		}
	}

	@Override
	public boolean search(int key) {
		if (super.search(key)) {
			return true;
		} else {
			// We cannot insert 0 into the array in case that 0 might originally
			// be already in the array. The consequence of this convention is we
			// still need exhaustively scan the whole list if we keep searching
			// for 0 and 0 does not exist. However, this convention is still
			// necessary, otherwise the result would be wrong.
			if (key != 0) {
				add(-key);
			}

			return false;
		}
	}

	public static void compareNotFound() {
		// We need some additional spaces to store the values after they are not
		// found, i.e., -k when k was searched and not found.
		final int ADDTIONAL = ListSimulator.LISTSIZE * 3;

		// Initialize the three experiments
		FixedLengthList lRandom = new FixedLengthList(ADDTIONAL);
		ListSimulator tRandom = new ListSimulator(TestType.RANDOM, lRandom);

		FixedLengthList lUp = new FixedLengthList(ADDTIONAL);
		ListSimulator tUp = new ListSimulator(TestType.INCREASING, lUp);

		FixedLengthList lDown = new FixedLengthList(ADDTIONAL);
		ListSimulator tDown = new ListSimulator(TestType.DECREASING, lDown);

		tRandom.simulatNotFound();
		tUp.simulatNotFound();
		tDown.simulatNotFound();

		System.out.println("Total cost random: " + tRandom.getTotal());
		System.out.println("Total cost increasing: " + tUp.getTotal());
		System.out.println("Total cost decreasing: " + tDown.getTotal());
	}
}
