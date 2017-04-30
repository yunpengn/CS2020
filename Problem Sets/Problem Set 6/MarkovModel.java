package sg.edu.nus.cs2020;

import java.util.HashMap;
import java.util.Random;

/**
 * Public Class: MarkovModel
 * 
 * Description: This class implements a Markov model to capture the probability
 * of a specific letter appearing after a specific preceding string.
 * 
 * @author Niu Yunpeng
 */
public class MarkovModel {
	// Stores the original text to determine the probabilities.
	private String m_text = "";

	// The order of Markov model.
	private int m_order = -1;
	
	// Stores the probabilities in the Markov model, i.e., the probability of a
	// specific letter appearing after a specific preceding string.
	private HashMap<String, MarkovModelRecord> table = null;

	// The indicator for the character after a non-existing string.
	final char NOCHARACTER = (char) (255);

	// The generator for random numbers/characters.
	Random generator = null;

	/**
	 * Public Constructor: MarkovModel(String, int)
	 * 
	 * @param text
	 *            is the specified text database.
	 * @param order
	 *            is the length of each specific preceding string.
	 */
	public MarkovModel(String text, int order) {
		if (text == null || text.length() == 0) {
			throw new IllegalArgumentException("Input text cannot be empty.");
		} else if (order <= 0) {
			throw new IllegalArgumentException("Order must be larger than 0.");
		} else {
			this.m_order = order;
			this.m_text = text;
			table = new HashMap<String, MarkovModelRecord>();
			process();
			generator = new Random();
		}
	}

	/**
	 * Private Method: void process()
	 * 
	 * Description: Reads through the whole text and compiles the relevant
	 * frequencies of different preceding strings and appending characters.
	 */
	private void process() {
		int size = m_text.length();
		String nowStr = "";
		char c = 0;
		MarkovModelRecord record = null;

		for (int i = 0; i < size - m_order; i++) {
			// The current corresponding preceding string.
			nowStr = m_text.substring(i, i + m_order);
			// The current corresponding character after the string.
			c = m_text.charAt(i + m_order);

			if (table.containsKey(nowStr)) {
				// Adds one more time of appearance for the current character if
				// the current string exists in the table.
				record = table.get(nowStr);
				record.add(c);
			} else {
				// Creates a record for the current string if it does not exist
				// in the table before.
				record = new MarkovModelRecord();
				record.add(c);
				table.put(nowStr, record);
			}
		}
	}

	/**
	 * Public Method: int getFrequency(String)
	 * 
	 * @return the number of times this specific string occurs in the text.
	 */
	public int getFrequency(String kgram) {
		if (kgram == null || kgram.length() != m_order) {
			throw new IllegalArgumentException("The given string is illegal");
		}

		if (table.containsKey(kgram)) {
			return table.get(kgram).getTotalFrequency();
		} else {
			return 0;
		}
	}

	/**
	 * Public Method: int getFrequency(String, char)
	 * 
	 * @return the number of times a certain character appears after a specific
	 *         string in the text.
	 */
	public int getFrequency(String kgram, char c) {
		if (kgram == null || kgram.length() != m_order) {
			throw new IllegalArgumentException("The given string is illegal");
		}

		if (table.containsKey(kgram)) {
			return table.get(kgram).getFrequency(c);
		} else {
			return 0;
		}
	}

	/**
	 * Public Method: char nextCharacter(String)
	 * 
	 * Description: This method randomly generates a character according to a
	 * given preceding string. The character is generated based on the frequency
	 * distribution for the characters after this preceding string in the text.
	 */
	public char nextCharacter(String kgram) {
		if (kgram == null || kgram.length() != m_order) {
			throw new IllegalArgumentException("The given string is illegal");
		}

		if (!table.containsKey(kgram)) {
			return NOCHARACTER;
		} else {
			// The total number of times this string occurs in the text.
			int total = getFrequency(kgram);
			// Generates a random integer in the range [0, total).
			int randomized = generator.nextInt(total);

			// Gets the corresponding letter according to the frequency
			// distribution of all different characters after this string.
			return table.get(kgram).getCorrespondingLetter(randomized);
		}
	}

	/**
	 * Public Method: int order()
	 * 
	 * @return the length of each specific preceding string in this model.
	 */
	public int order() {
		return m_order;
	}

	/**
	 * Public Method: void setRandomSeed(long)
	 * 
	 * Description: This method sets the seed of the random number generator to
	 * a specified long integer.
	 */
	public void setRandomSeed(long s) {
		generator = new Random(s);
	}
}
