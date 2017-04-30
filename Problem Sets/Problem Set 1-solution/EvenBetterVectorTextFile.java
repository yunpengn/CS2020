/**
 * Class: EvenBetterVectorTextFile
 * 
 * Purpose: Represents a text file as a vector, i.e., 
 * as a sorted array of word/count pairs that appear 
 * in the text file.
 * 
 * Inherits from: BetterVectorTextFile
 * 
 * Constructor: EvenBetterVectorTextFile(String fileName)
 * 
 * All the functionality is equivalent to the BetterVectorTextFile,
 * except for sorting, which is performed more efficiently using
 * MergeSort, instead of InsertionSort.
 *   
 * Public Class Methods:
 * 		int Norm() : Returns the norm of the vector.
 * 
 * Static Methods:
 * 		double DotProduct(VectorTextFile A, VectorTextFile B) : 
 * 			Returns the dot product of two vectors.
 * 		double Angle(VectorTextFile A, VectorTextFile B) : 
 * 			Returns the angle between two vectors. 
 * 
 * @author gilbert
 * 
 */

// This class is part of the cs2020 package
package cs2020;

// The new class is declared here.
// It extends BetterVectorTextFile
public class EvenBetterVectorTextFile extends BetterVectorTextFile {
	
	/**
	 * Constructor: EvenBetterVectorTextFile
	 * @param name file with which to build a vector
	 */
	EvenBetterVectorTextFile(String name){
		// Call the constructor in the parent class
		super(name);
	}
	
	
	/**
	 * SortWords: Sorts the words in the wordList. 
	 * @param wordList array of words to be sorted
	 * 
	 * Note: The list of words ends at the first null string.
	 * 
	 * Properties: On completion, the words in wordList are sorted 
	 * alphabetically.
	 * 
	 * Methodology: The sorting is accomplished via `merge sort.'
	 * This sorting method runs in O(n log n) time, instead of the 
	 * slower `insertion sort' used in the parent class.
	 */
	protected void SortWords(String[] wordList)
	{
		// Sort all the words in the list.
		MergeSortWords(wordList, 0, wordList.length-1);
	}
	
	//-----------------------------------------------------------------------
	// MergeSortWords: Sorts the words in the array m_WordList. 
	//
	// Input: Begin and End are indices into the array m_WordList.
	// Output: None
	// Assumptions: m_WordList holds a non-empty set of words, and
	// m_FileWordCount has a count of the number of words.  Also,
	// we require that: 0 <= Begin <= End < m_FileWordCount.
	// Properties: On completion, the words in m_WordList[Begin..End] are  
	// sorted alphabetically, and the flag m_Sorted is set to true. Note 
	// that the array indices are inclusive, i.e., m_WordList[Begin] and
	// m_WordList[End] are both sorted.
	// Methodology: The sorting is accomplished via `merge sort.'  	
	//-----------------------------------------------------------------------
	/**
	 * SortWords: Sorts the words in the wordList. 
	 * @param wordList array of words to be sorted
	 * @param begin index to begin sorting, inclusive
	 * @param end index to stop sorting, inclusive
	 * 
	 * Note: The list of words ends at the first null string.
	 * 
	 * Properties: On completion, the words in wordList 
	 * bettween Begin and End are sorted alphabetically.
	 * 
	 * Methodology: The sorting is accomplished via `merge sort.'
	 * This sorting method runs in O(n log n) time, instead of the 
	 * slower `insertion sort' used in the parent class.
	 */
	private void MergeSortWords(String[] wordList, int begin, int end) 
	{	
		// First, check for errors
		if (end < begin)
		{
			throw new IllegalArgumentException("Failed MergeSortWords: End is not greater than Begin.");
		}
		if ((wordList==null) || (wordList.length<1))
		{
			throw new IllegalArgumentException("Failed in MergeSortWords: no words to sort.");
		}
		
		// Determine the number of words in the array to sort
		int numWords = end-begin+1;
		
		// If there is only one element in the list to sort, then
		// by definition, it is already well sorted.
		if (numWords < 2)
		{
			return;
		}
		
		// If the entire sublist is null, then return
		if (wordList[begin]==null)
		{
			return;
		}
		
		// We now divide the list into two parts, each 1/2 the size
		// of the initial list.  The first list is from [Begin..Middle-1]
		// and the second list is from [Middle..End].
		//
		// Note that division by two automatically rounds to an integer.
		int middle = begin + numWords/2;		
				
		// Recursively sort each half-list.
		MergeSortWords(wordList, begin, middle-1);
		MergeSortWords(wordList, middle, end);		
		
				
		// Merge the two sorted lists.
		Merge(wordList, begin, middle, end);
	}
	
	//-----------------------------------------------------------------------
	// Merge: Merges two sorted lists. 
	//
	// Input: Begin, Middle, and End are indices into the array m_WordList.
	// Output: None
	// Assumptions: m_WordList holds a non-empty set of words, and
	// m_FileWordCount has a count of the number of words.  In addition,
	// we require that 0 <= Begin <= Middle <= End < m_FileWordCount.
	// Also, we require that Begin < End.
	// Properties: On completion, the words in m_WordList[Begin..End] are  
	// sorted alphabetically, and the flag m_Sorted is set to true.
	// Methodology: The merging is accomplished by iteratively scanning each
	// of the two lists, selecting the smallest element at each step.
	//-----------------------------------------------------------------------
	/**
	 * Merge: Merges two sorted lists
	 * @param wordList array containing lists to merge
	 * @param begin beginning of the first list 
	 * @param middle beginning of the second list
	 * @param end end of the second list
	 * 
	 * Assumptions: wordList holds a non-empty set of words.  In addition,
	 * we require that 0 <= begin <= middle <= end < wordList.length.
	 * Also, we require that begin < end.  Finally, we assume that the
	 * array wordList[begin, middle-1] and the array wordList[middle, end] 
	 * are sorted.
	 * 
	 * Properties: On completion, the words in wordList[begin..end] are  
	 * sorted alphabetically.
	 * 
	 * Methodology: The merging is accomplished by iteratively scanning each
	 * of the two lists, selecting the smallest element at each step.
	 */
	private void Merge(String[] wordList, int begin, int middle, int end)
	{
		// Determine the number of words in the array to sort
		int numWords = end-begin+1;
		
		// Create a temporary list to store the merged lists
		String[] mergedList = new String[numWords];
		
		// We begin at the beginning of each of the two lists
		int AListHead = begin;
		int BListHead = middle;		
		
		for (int i=0; i<numWords; i++)
		{
			// If we have exhausted all the words in AList,
			// then we copy a word from BList
			if (AListHead == middle)
			{
				mergedList[i] = wordList[BListHead];
				BListHead++;
			}
			// Otherwise, if we have exhausted all the words in BList,
			// then we copy a word from AList
			else if ((BListHead == end+1) || (wordList[BListHead]==null))
			{
				mergedList[i] = wordList[AListHead];
				AListHead++;
			}		
			// Otherwise, we compare the words at the head of AList and BList.
			// If the word at the head of BList is smaller than the word at
			// the head of AList, then we copy a word from BList.
			else if (wordList[AListHead].compareTo(wordList[BListHead]) > 0)
			{
				mergedList[i] = wordList[BListHead];
				BListHead++;
			}
			// Otherwise, if the word at the head of AList is smaller than the word
			// at the head of BList, then we copy a word from AList.
			else
			{
				mergedList[i] = wordList[AListHead];
				AListHead++;
			}
		}	
		// Now that we are done merging, we copy the MergedList back into the 
		// original array		
		for (int i=begin; i<=end; i++)
		{
			wordList[i] = mergedList[i-begin];
		}
	}
}
