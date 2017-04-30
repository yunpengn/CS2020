package sg.edu.nus.cs2020;

// Uses the Random library
import java.util.Random;

/**
 * Class: ListSimulator
 * 
 * Description: Simple simulator for testing list management heuristics. The
 * simulator inserts a specified number of items into a list, and then queries
 * the items according to a randomly chosen (exponential) distribution.
 */
public class ListSimulator {

	// Random number generator
	static Random s_generator = new Random();

	// Constant: size of the list to test
	final static int LISTSIZE = 1000;

	// Constant: number of searches to run on the list in each test
	final static long NUMQUERIES = 1000000;

	// Three different types of test, depending on the initial order of the list
	// INCREASING: the list is initially organized in order of increasing probability
	// DECREASING: the list is initially organized in order of decreasing probability
	// RANDOM: the list is initially organized in a random order
	public static enum TestType {INCREASING, DECREASING, RANDOM};

	// Array for storing the probability distribution. Item k is chosen with
	// probability m_ListProb[k].
	private double[] m_ListProb = new double[LISTSIZE];

	// A list for running the test on
	private FixedLengthList m_list = null;

	// A StopWatch for measuring how long the tests take
	private StopWatch m_watch = new StopWatch();

	/**
	 * Constructor: ListSimulator
	 * 
	 * Description: generates the probability distribution and inserts the keys
	 * into the list in the specified order.
	 * 
	 * @param type
	 *            specifies the type of experiment to run.
	 * @param list
	 *            specifies the list on which to run the experiment.
	 */
	ListSimulator(TestType type, FixedLengthList list) {
		// Save list
		m_list = list;

		// Reset clock
		m_watch.reset();

		// Generate access probabilities, where the largest probability is 0.1
		generateListProbs(0.1);

		// Insert all the items into the list in the appropriate order for this experiment.
		if (type == TestType.INCREASING) {
			for (int i = 0; i < LISTSIZE; i++) {
				m_list.add(LISTSIZE - i - 1);
			}
		} else if (type == TestType.DECREASING) {
			for (int i = 0; i < LISTSIZE; i++) {
				m_list.add(i);
			}
		} else if (type == TestType.RANDOM) {
			// Add all the items to an ArrayList
			java.util.ArrayList<Integer> itemsToAdd = new java.util.ArrayList<Integer>(LISTSIZE);

			for (int i = 0; i < LISTSIZE; i++) {
				itemsToAdd.add(i);
			}

			// For each item in the ArrayList:
			for (int i = 0; i < LISTSIZE; i++) {
				// Choose a random item in the ArrayList and add it to our list
				int toAdd = s_generator.nextInt(itemsToAdd.size());
				m_list.add(itemsToAdd.get(toAdd));

				// Remove the chosen item from the ArrayList
				itemsToAdd.remove(toAdd);
			}
		}
	}

	/**
	 * Private Method: generateListProbs generates a distribution over all keys.
	 * 
	 * Description: This method generates a distribution and stores it in
	 * m_ListProb. The distribution is stored in descending order, i.e.,
	 * m_ListProb[0] is the largest probability, while m_ListProb[LISTSIZE - 1]
	 * is the smallest probability. The distribution being generated is roughly
	 * exponential.
	 * 
	 * @param maxProb
	 *            specifies the maximum probability for a single item.
	 */
	private void generateListProbs(double maxProb) {
		// Begin with all the probability mass here
		double total = 1.0;

		// Assign a probability to each item in the list:
		for (int i = 0; i < LISTSIZE - 1; i++) {
			// Get a random double between 0 and 1
			double amt = s_generator.nextDouble();

			// Scale the randomly chosen double so that it is no bigger than maxProb
			amt = amt * maxProb;

			// amt now specifies the percentage of the remaining total
			// probability for each item.
			m_ListProb[i] = amt * total;

			// Subtract the newly allocated probability from total
			total = total - m_ListProb[i];
		}

		// Give any remaining probability to the last element in the list
		m_ListProb[LISTSIZE - 1] = total;

		// Sort the probabilities from smallest to largest.
		// Notice: Why do we sort it again here? Isn't it already been
		// in decreasing order before?
		// Answer: A small number of them may be in the inverse order.
		// So, we do sort again to reassure.
		java.util.Arrays.sort(m_ListProb);

		// Reverse the list so it is sorted from largest to smallest
		double temp = 0;

		for (int i = 0, j = LISTSIZE - 1; i < j; i++, j--) {
			temp = m_ListProb[i];
			m_ListProb[i] = m_ListProb[j];
			m_ListProb[j] = temp;
		}
	}

	public void printProbs() {
		for (int i = 0; i < m_ListProb.length; i++) {
			System.out.println(i + ":" + m_ListProb[i]);
		}
	}

	/**
	 * Private Method: chooseRandom
	 * 
	 * Description: Chooses a random item according to the distribution in
	 * m_ListProb.
	 */
	private int chooseRandom() {
		// Choose a random double between 0 and 1
		double randomChoice = s_generator.nextDouble();

		// Find the first index such that the sum of the prefixes in m_ListProb
		// >= randomChoice, which begins by initializing sum to zero
		double sum = 0;

		// Iterate through the distribution until we find the right element
		for (int i = 0; i < LISTSIZE; i++) {
			// Add the next element to sum
			sum += m_ListProb[i];

			// Check if the sum is big enough. If so, return i.
			if (randomChoice <= sum) {
				return i;
			}
		}

		// If there is an error (likely due to floating point rounding) just return element 0.
		return 0;
	}

	/**
	 * Private Method: chooseRandomIncludeNotFound
	 * 
	 * Description: Based on chooseRandom, the return value of this method
	 * sometimes does not exist in the list. It should only be used by
	 * simulateNotFound() in order to test NotFoundList().
	 */
	
	private int chooseRandomIncludeNotFound() {
		// This ratio decides the frequency that we search for items that are
		// not in the list. The larger, the more frequent.
		final int RATIO = 2;

		return chooseRandom() * RATIO;
	}

	/**
	 * Private Method: search
	 * 
	 * Description: searches for the key in the list. It also measures how long
	 * the search takes.
	 * 
	 * @param key
	 *            is the item to search for.
	 * 
	 * @return true if the element is found, false otherwise
	 * 
	 */
	private boolean search(int key) {
		// Check for null list
		if (m_list == null) {
			System.out.println("Error: null list.");
			return false;
		}

		// Start the StopWatch
		m_watch.start();

		// Perform the search
		boolean found = m_list.search(key);

		// Stop the StopWatch
		m_watch.stop();

		// If the element is not found, report an error.
		if (found == false) {
			System.out.println("Error!");
		}

		return found;
	}

	// Based on boolean search(int key), but does not report an error when the
	// element is not found.
	private boolean searchNotFound(int key) {
		// Check for null list
		if (m_list == null) {
			System.out.println("Error: null list.");
			return false;
		}

		// Start the StopWatch
		m_watch.start();

		// Perform the search
		boolean found = m_list.search(key);

		// Stop the StopWatch
		m_watch.stop();

		return found;
	}

	/**
	 * Public Method: getTotal
	 * 
	 * Description: Returns the cumulative time for all the search operations
	 * since the test was begun.
	 */
	public float getTotal() {
		return m_watch.getTime();
	}

	/**
	 * Public Method: simulate()
	 * 
	 * Description: Run NUMQEIRES searches and prints the total time used to the
	 * standard out. Therefore, we can simulate the capability of the list.
	 */
	public void simulate() {
		// Run NUMQERIES searches to get the total time.
		for (int i = 0; i < NUMQUERIES; i++) {
			// For each search, choose a random value according to the
			// distribution. Notice: the value tends to be smaller values among
			// [0, LISTSIZE].
			search(chooseRandom());
		}
	}

	/**
	 * Public Method: simulateNotFound()
	 * 
	 * Description: Run NUMQEIRES searches and prints the total time used to the
	 * standard out. Note that this method should only be used to compare
	 * NotFoundList class since its advantage only shows when we search for
	 * things that do not exist in the list.
	 */
	public void simulatNotFound() {
		// Run NUMQERIES searches to get the total time.
		for (int i = 0; i < NUMQUERIES; i++) {
			// For each search, choose a random value according to the
			// distribution. Notice: Although the values tend to be smaller,
			// they may exceed [0, LISTSIZE] and do not exist in the list.
			searchNotFound(chooseRandomIncludeNotFound());
		}
	}
}
