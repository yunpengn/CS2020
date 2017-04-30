package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Public Class: MarkovModelWord
 * 
 * Description: This class implements a Markov model to capture the probability
 * of a specific word appearing after a specific word. Different from classic
 * Markov model, the order of occurence of words rather than single characters
 * is emphasized here.
 * 
 * @author Niu Yunpeng
 */
public class MarkovModelWord {
	// Stores all the words in the original text.
	private ArrayList<String> words = null;

	// Stores the probabilities in the Markov-word model, i.e., the probability
	// of a word appearing after another word.
	private HashMap<String, MarkovModelRecordWord> table = null;

	// The indicator for the word after a non-existing word.
	private final String NOWORD = "";

	// The generator for random numbers/characters.
	private Random generator = null;

	/**
	 * Public Constructor: MarkovModelWord(String, int)
	 * 
	 * @param text
	 *            is the specified text database.
	 * @param order
	 *            is the length of each specific preceding string.
	 */
	public MarkovModelWord(String text) {
		if (text == null || text.length() == 0) {
			throw new IllegalArgumentException("Input text cannot be empty.");
		} else {
			table = new HashMap<String, MarkovModelRecordWord>();
			readAllWords(text);
			process();
			generator = new Random();
		}
	}

	/**
	 * Private Method: void readAllWords(String)
	 * 
	 * Description: This method splits the given text into a list of words,
	 * which are separated by white spaces.
	 */
	private void readAllWords(String text) {
		words = new ArrayList<String>(Arrays.asList(text.split(" ")));
	}

	/**
	 * Private Method: void process()
	 * 
	 * Description: Reads through the whole text and compiles the relevant
	 * frequencies of different preceding and appending words.
	 */
	private void process() {
		String nowWord = words.get(0);
		String nextWord = "";
		MarkovModelRecordWord record = null;
		
		for (int i = 1; i < words.size(); i++) {
			nextWord = words.get(i);

			if (table.containsKey(nowWord)) {
				// Adds one more time of appearance for the next word if the
				// current word exists in the table.
				record = table.get(nowWord);
				record.add(nextWord);
			} else {
				// Creates a record for the current word if it does not exist
				// in the table before.
				record = new MarkovModelRecordWord();
				record.add(nextWord);
				table.put(nowWord, record);
			}

			nowWord = nextWord;
		}
	}

	/**
	 * Public Method: int getFrequency(String)
	 * 
	 * @return the number of times this specific prepanding word occurs in the
	 *         given text.
	 */
	public int getFrequency(String kgram) {
		if (kgram == null || kgram.length() == 0) {
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
	 * @return the number of times a certain word appears after a specific
	 *         preceding word in the text.
	 */
	public int getFrequency(String kgram, String after) {
		if (kgram == null || kgram.length() == 0) {
			throw new IllegalArgumentException("The given string is illegal");
		}

		if (table.containsKey(kgram)) {
			return table.get(kgram).getFrequency(after);
		} else {
			return 0;
		}
	}

	/**
	 * Public Method: String nextWord(String)
	 * 
	 * Description: This method randomly generates a word according to a given
	 * preceding word. The character is generated based on the frequency
	 * distribution for the word after this preceding word in the text.
	 */
	public String nextWord(String kgram) {
		if (kgram == null) {
			throw new IllegalArgumentException("The given string is null");
		} else if (kgram.length() == 0) {
			String message = "The given string," + kgram + ", is illegal";
			throw new IllegalArgumentException(message);
		}

		if (!table.containsKey(kgram)) {
			return NOWORD;
		} else {
			// The total number of times this word occurs in the text.
			int total = getFrequency(kgram);
			// Generates a random integer in the range [0, total).
			int randomized = generator.nextInt(total);

			// Gets the corresponding word according to the frequency
			// distribution of all different words after this string.
			return table.get(kgram).getCorrespondingWord(randomized);
		}
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

	/**
	 * Public Method: String getFirstWord()
	 * 
	 * Description: This method returns the first word in the given text.
	 */
	public String getFirstWord() {
		return words.get(0);
	}
}
