package sg.edu.nus.cs2020;

import sg.edu.nus.cs2020.ListSimulator.TestType;

/**
 * Class: FixedLengthList
 * 
 * Description: Simple array-based implementation of a fixed-length list. All
 * the elements in the list are integers >= 0.
 */
public class FixedLengthList {

	// Maximum length of the list
	protected int m_length = 0;

	// Array for storing elements in the list
	protected int[] m_list = null;

	// Maximum full slot in the array
	// m_list[m_max] contains the last element added to the list
	protected int m_max = -1;

	/**
	 * Constructor: FixedLengthList
	 * 
	 * Description: Creates a new list of specified length. Initializes array
	 * m_list, setting every slot to -1.
	 * 
	 * @param length
	 *            the length of the list created.
	 */
	public FixedLengthList(int length) {
		m_length = length;
		m_list = new int[length];

		for (int i = 0; i < length; i++) {
			m_list[i] = -1;
		}
	}

	/**
	 * Public Method: add
	 * 
	 * Description: appends a new integer to the end of the list.
	 * 
	 * @param key
	 *            the integer to add to the list.
	 * 
	 * @return true if the add succeeds, and false otherwise (It will output an
	 *         error if the key is < 0, or the length of the list is exceeded.
	 */
	public boolean add(int key) throws LengthExceedException {
		m_max++;

		if (m_max < m_length) {
			m_list[m_max] = key;
			return true;
		} else {
			throw new LengthExceedException();
		}
	}

	/**
	 * Public Method: search
	 * 
	 * Description: Perform a linear search to check whether the given key is in
	 * the list.
	 * 
	 * @param key
	 *            the integer to search for in the list.
	 * 
	 * @return true if the key is in the last and false otherwise.
	 */
	public boolean search(int key) {
		for (int i = 0; i <= m_max; i++) {
			if (m_list[i] == key) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Public Method: toString
	 *
	 * Description: Converts the list into a printable string
	 */
	@Override
	public String toString() {
		String output = "";

		for (int i = 0; i <= m_max; i++) {
			output += m_list[i] + " ";
		}

		return output.trim();
	}

	public static void compare() {
		// Initialize the three experiments
		FixedLengthList lRandom = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tRandom = new ListSimulator(TestType.RANDOM, lRandom);

		FixedLengthList lUp = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tUp = new ListSimulator(TestType.INCREASING, lUp);

		FixedLengthList lDown = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tDown = new ListSimulator(TestType.DECREASING, lDown);

		tRandom.simulate();
		tUp.simulate();
		tDown.simulate();

		System.out.println("Total cost random: " + tRandom.getTotal());
		System.out.println("Total cost increasing: " + tUp.getTotal());
		System.out.println("Total cost decreasing: " + tDown.getTotal());
	}

	public static void compareNotFound() {
		// Initialize the three experiments
		FixedLengthList lRandom = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tRandom = new ListSimulator(TestType.RANDOM, lRandom);

		FixedLengthList lUp = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tUp = new ListSimulator(TestType.INCREASING, lUp);

		FixedLengthList lDown = new FixedLengthList(ListSimulator.LISTSIZE);
		ListSimulator tDown = new ListSimulator(TestType.DECREASING, lDown);

		tRandom.simulatNotFound();
		tUp.simulatNotFound();
		tDown.simulatNotFound();

		System.out.println("Total cost random: " + tRandom.getTotal());
		System.out.println("Total cost increasing: " + tUp.getTotal());
		System.out.println("Total cost decreasing: " + tDown.getTotal());
	}
}
