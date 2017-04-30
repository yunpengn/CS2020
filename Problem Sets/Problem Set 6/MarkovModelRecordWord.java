package sg.edu.nus.cs2020;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Public Class: MarkovModelRecord
 * 
 * Description: This class implements the record for each specific preceding
 * word in a Markov word model.
 * 
 * @author Niu Yunpeng
 */
public class MarkovModelRecordWord {
	// A tree to store the times that different word occur after the specific
	// preceding word.
	private TreeMap<String, Integer> frequency = null;

	// The number of times this preceding word has occurred in the text.
	private int count = 0;

	// The set of entries for the treeMap, used by getCorrespondingWord.
	private Set<Entry<String, Integer>> entries = null;

	// The iterator for the entry set, used by getCorrespondingWord.
	private Iterator<Entry<String, Integer>> iter = null;

	/**
	 * Public Constructor: public MarkovModelRecordWord()
	 * 
	 * Description: Initialize a treeMap, in which each element represents the
	 * number of times the word (corresponding string) appears after the
	 * specific preceding word.
	 */
	public MarkovModelRecordWord() {
		frequency = new TreeMap<String, Integer>();
	}

	/**
	 * Public Method: void add(char)
	 * 
	 * Description: This method adds one more time of appearance that a certain
	 * word appears after the specific preceding word.
	 */
	public void add(String str) {
		count++;

		if (frequency.containsKey(str)) {
			int origin = frequency.get(str);
			frequency.put(str, origin + 1);
		} else {
			frequency.put(str, 1);
		}
	}

	/**
	 * Public Method: int getTotalFrequency()
	 * 
	 * @return the number of times this specific preceding word has occurred.
	 */
	public int getTotalFrequency() {
		return count;
	}

	/**
	 * Public Method: int getFrequency(String)
	 * 
	 * @return the number of times that a certain word appears after the
	 *         specific preceding word.
	 */
	public int getFrequency(String str) {
		if (frequency.containsKey(str)) {
			return frequency.get(str);
		} else {
			return 0;
		}
	}

	/**
	 * Public Method: char getCorrespondingLetter(int)
	 * 
	 * Description: Given an integer within [0, count), this method returns the
	 * corresponding word according the values in the frequency treeMap.
	 */
	public String getCorrespondingWord(int num) {
		// Update the entry set and its iterator
		entries = frequency.entrySet();
		iter = entries.iterator();

		// The current entry in the treeMap.
		Entry<String, Integer> now = null;

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
