package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Public Class: MarkovModelRecord
 * 
 * Description: This class implements the record for each specific preceding
 * string in a Markov model.
 * 
 * @author Niu Yunpeng
 */
public class MarkovModelRecord {
	// A tree to store the times that different characters occur after the
	// specific preceding string.
	private TreeMap<Character, Integer> frequency = null;

	// The number of times this preceding string has occurred in the text.
	private int count = 0;

	// The set of entries for the treeMap, used by getCorrespondingLetter.
	private Set<Entry<Character, Integer>> entries = null;

	// The iterator for the entry set, used by getCorrespondingLetter.
	private Iterator<Entry<Character, Integer>> iter = null;

	/**
	 * Public Constructor: public MarkovModelRecord()
	 * 
	 * Description: Initialize a treeMap, in which each element represents the
	 * number of times the character (corresponding ASCII code) appears after
	 * the specific preceding string.
	 */
	public MarkovModelRecord() {
		frequency = new TreeMap<Character, Integer>();
	}

	/**
	 * Public Method: void add(char)
	 * 
	 * Description: This method adds one more time of appearance that a certain
	 * character appears after the preceding string.
	 */
	public void add(char c) {
		count++;

		if (frequency.containsKey(c)) {
			int origin = frequency.get(c);
			frequency.put(c, origin + 1);
		} else {
			frequency.put(c, 1);
		}
	}

	/**
	 * Public Method: int getTotalFrequency()
	 * 
	 * @return the number of times this specific preceding string has occurred.
	 */
	public int getTotalFrequency() {
		return count;
	}

	/**
	 * Public Method: int getFrequency(char)
	 * 
	 * @return the number of times that a certain character appears after the
	 *         specific preceding string.
	 */
	public int getFrequency(char c) {
		if (frequency.containsKey(c)) {
			return frequency.get(c);
		} else {
			return 0;
		}
	}

	/**
	 * Public Method: char getCorrespondingLetter(int)
	 * 
	 * Description: Given an integer within [0, count), this method returns the
	 * corresponding letter according the values in the frequency treeMap.
	 */
	public char getCorrespondingLetter(int num) {
		// Update the entry set and its iterator
		entries = frequency.entrySet();
		iter = entries.iterator();

		// The current entry in the treeMap.
		Entry<Character, Integer> now = null;

		while (iter.hasNext()) {
			now = iter.next();
			num = num - now.getValue();

			if (num < 0) {
				return now.getKey();
			}
		}

		return now.getKey();
	}
}
