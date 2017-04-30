/**
 * Class: WordCountPair
 * Purpose: Contains a pair consisting of a word and a count.
 * 
 * Constructor: WordCountPair(String word, int count)
 * Behavior: Sets the word/count pair to the specified word and count.
 *  
 *  Public Class Methods:
 *  	int getWord() : Returns the word.
 *  	int getCount() : Returns the count. 
 */

// This class is part of the cs2020 package.
package cs2020;

// Class declaration:
public class WordCountPair
{
	/*****************************
	 * Class member variables 
	 *****************************/
	String word;
	int count;
		
	/**
	 * Constructor creates a word/count pair
	 * @param s word
	 * @param c count
	 */
	WordCountPair(String s, int c)
	{
		word = s;
		count = c;
	}
	
	/**
	 * getWord
	 * @return the word stored in the word/count pair.
	 */
	String getWord()
	{
		return word;
	}
	
	/**
	 * getCount
	 * @return the count stored in the word/count pair.
	 */
	int getCount()
	{
		return count;
	}
}
