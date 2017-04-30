/**
 * Class: VectorTextFile
 * 
 * Purpose: Represents a text file as a vector, i.e., 
 * as a sorted array of word/count pairs that appear 
 * in the text file.
 * 
 * Constructor: VectorTextFile(String fileName)
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


// This class is part of the cs2020 package.
package cs2020;

// This class uses the following two packages (associated with reading files):
import java.io.FileInputStream;
import java.io.IOException;

// Class declaration:
public class VectorTextFile {

	/*****************************
	 * Class member variables 
	 *****************************/
	
	// Filename
	String fileName = null;
	
	// Is the file parsed?
	boolean parsed = false;

	// Array of word/count pairs
	WordCountPair[] countedWords;
		
	/**
	 * Constructor: builds a VectorTextFile from a string
	 * @param str Name of file containing text.
	 */
	public VectorTextFile(String str)
	{
		fileName = str;
	}

	
	
	/*****************************
	 * Public Class Methods     *
	 *****************************/
	
	/**
	 * Norm: Returns the norm of the vector.
	 * 
	 * Methodology: the norm of a vector X is defined to be the square-root of
	 * the dot product (x, x).
	 *  
	 * @return The norm of the vector.
	 */
	public double Norm()
	{
		int dot = VectorTextFile.DotProduct(this, this);
		return Math.sqrt(dot);
	}
	
	/*****************************
	 * Static Class Methods     *
	 *****************************/
	
	/**
	 * DotProduct: Calculates the dot-product of two VectorTextFiles.
	 * @param A first VectorTextFile
	 * @param B second VectorTextFile
	 * @return the dot-product of the two vectors
	 * 
	 * Note on errors: if either vector is null, or cannot be properly parsed, then
	 * this method return -1, which indicates an error.  Otherwise, it
	 * always returns an output >= 0.
	 * 
	 * Methodology: The dot-product is calculated by summing the product of 
	 * the individual vector components. That is, if D(w,X) is the number of 
	 * times word w appears in vector X, then the dot-product of A and B is 
	 * defined as: Sum_{every w in A and B) [D(w,A)*D(w,B)]
	 */
	public static int DotProduct(VectorTextFile A, VectorTextFile B)
	{
		// Check input
		if ((A == null) || (B == null)) {
			return -1;
		}
		
		// Check initialized properly
		if (!A.parsed) {
			A.ParseFile();
		}
		if (!B.parsed){
			B.ParseFile();
		}
		
		// If there was an error parsing A or B, then return -1.
		if (!A.parsed || !B.parsed) 
			return -1;
		
		// The sum is initially zero
		int sum = 0;
		
		// We begin with word/count pair zero
		int Aindex = 0;
		
		// We begin with word/count pair zero
		int Bindex = 0;
		
		// We iterate through all the word/count pairs, looking for words that 
		// appear in both A and B.  We continue until we run out of words in 
		// either A or B.
		while ((A.countedWords[Aindex]!=null) && (B.countedWords[Bindex]!=null))
		{
			// First, get the word associated with Aindex in A
			WordCountPair Awordpair = A.countedWords[Aindex];
			String Aword = Awordpair.getWord();
			
			// Next, get the word associated with Bindex in B
			WordCountPair Bwordpair = B.countedWords[Bindex];
			String Bword = Bwordpair.getWord();
			
			// If Aword==Bword, then we have found a word in both vector A and B
			if (Aword.equals(Bword))
			{
				// Add the product of the counts to the sum.
				sum += (Awordpair.getCount()*Bwordpair.getCount());
				
				// Next, increment both Aindex and Bincex to consider the next
				// word in both vectors.
				Aindex++;
				Bindex++;
			}
			else if (Aword.compareTo(Bword) > 0)
			{
				// Otherwise, if (Aword > Bword) in alphabetic order, we
				// increment Bindex, going to the next WordCountPair in B.
				Bindex++;
			}
			else
			{
				// Otherwise, (Bword > Aword) in alphabetic order.  We
				// increment Aindex, going to the next WordCountPair in A.
				Aindex++;
			}
		}
		
		// Finally, we return the dot-product.
		return sum;
	}
	
	/**
	 * Angle: Calculates the angle between two TextFile vectors.
	 * @param A First VectorTextFile
	 * @param B Second VectorTextFile
	 * @return the angle between the two vector, always between [0, pi/2]
	 * 
	 * Methodology: The angle is calculated as per the "Cosine Formula" which 
	 * states that: Theta(A,B) = arccos((AB)/(|A|*|B|))
	 * where (AB) is the vector dot product of A and B, |A| is the norm of the 
	 * vector A, and |B| is the norm of vector B.
	 */
	public static double Angle(VectorTextFile A, VectorTextFile B)
	{
		// First calculate the dot product of the vectors A and B
		int dot = VectorTextFile.DotProduct(A, B);
		
		// Second, calculate the norm of the two vectors
		double Anorm = A.Norm();
		double Bnorm = B.Norm();
		
		// Third, calculate (AB)/(|A|*|B|)
		double result = dot/(Anorm*Bnorm);
		
		// Lastly, take the arccos of the result
		double theta = Math.acos(result);
		
		// Return the angle
		return theta;			
	}
	
	
	/*******************************************
	 * Protected and Private Class Methods     *
	 *******************************************/
	
	//-----------------------------------------------------------------------
		// Constructor: Reads and parses the specified file
		//
		// Input: String containing a filename
		// Assumptions: fileName is a text file that exists on disk.
		// Properties: On completion, m_WordList contains a sorted array of all the
		// words in the text file, m_FileWordCount is the number of words in the
		// text file, m_CountedWords contains a sorted array of word/count pairs
		// with one entry for every distinct word in the text file, m_WordPairCount
		// is the number of word/count pairs, and the flag m_Sorted is true.
		// Characters in the file are treated in the following manner:
		// (a) Every letter is made lower-case.
		// (b) All punctuation is removed.
		// (c) Each end-of-line marker ('\n') is replaced with a space.
		// (d) All (other) non-letters and non-spaces are removed.
		//-----------------------------------------------------------------------	
	/**
	 * ParseFile: Reads, parses, and sorts the words from the specified file.
	 * 
	 * Methodology: this method relies on three other methods to parse a file.  
	 * First, it calls ReadWords to read in the words from the file.  Then, it 
	 * calls SortWords to sort the words.  Finally, it calls CountWordFrequencies
	 * to count how many times each word shows up.
	 * 
	 * On completion: the variable countedWords is an array containing, for each
	 * word in the initial textfile, a pair consisting of the word itself and the
	 * number of times it appears in the file.  This array is sorted in the order
	 * of the words.  All letters in all words are lower-case, and all 
	 * punctuation/non-letters are removed.
	 * 
	 * @throws IllegalArgumentException if there is a problem with the file.
	 */
	protected void ParseFile()
	{
		// Ensure that this is the very first time
		// that the file is being parsed.  We should
		// only parse each file once.
		assert(parsed == false);
			
		// First, initialize variables to null.
		String[] wordList = null;
		countedWords = null;
		
		// Next, read in the file and parse it into words.
		wordList = ReadWords(fileName);
		
		// Check for errors:
		if ((wordList == null) || (wordList.length < 1))
		{
			throw new IllegalArgumentException("Reading the file failed.");
		}
					
		// Next, sort the words.
		SortWords(wordList);
		
		// Check if list is sorted?
		VerifySort(wordList);
		
		// Finally, count the number of times each word appears in the file.
		countedWords = CountWordFrequencies(wordList);
		
		// Check for errors:
		if ((countedWords == null) || (countedWords.length < 1))
		{
			throw new IllegalArgumentException("Counting the word frequencies failed.");
		}
		
		// Everything worked correctly:
		parsed = true;
	}	
	

	/**
	 * ReadWords: Reads a text file and parses it into a list of words
	 * @param fileName
	 * @return An array of strings.  Each word in the specified file is included
	 * in the array.  Each word appears the same number of times in the list as
	 * it does in the original file. All letters in all words are lower-case, and all 
	 * punctuation/non-letters are removed.
	 */
	protected String[] ReadWords(String fileName)
	{
		// First, read the file into a single long string.
		String strTextFile = ReadFile(fileName);
		
		// Next, divide the string into words.
		return SplitString(strTextFile);
	}
		
	/**
	 * ReadFile reads the specified file from disk
	 * @param fileName name of file to read
	 * @return string containing all the text in the file
	 * 
	 * Properties: The string returned contains every character in the 
	 * specified file, in the same order, except:
	 * 		(a) Every letter is lower-case.
	 * 		(b) All punctuation is removed.
	 * 		(c) Each end-of-line marker ('\n') is replaced with a space.
	 * 		(d) All (other) non-letters and non-spaces are removed.
	 * 		(e) Words are separated by exactly one space.
	 */
	protected String ReadFile(String fileName)
	{		
		// Declare and initialize variables 
		FileInputStream inputStream = null;		
		String textFile = "";
		int size = 0;				
											
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			inputStream = new FileInputStream(fileName);
			
			// Determine the size of the file, in bytes
			size = inputStream.available();
						
			// Read in the file, one character at a time.
			// For each character, normalize it, removing punctuation and capitalization.
			for (int i=0; i<size; i++)
			{
				// Read a character
				char c = (char)inputStream.read();
				
				// Ensure that the character is lowercase
				c = Character.toLowerCase(c);
				
				// Check if the character is a letter
				if (Character.isLetter(c))
				{					
					textFile = textFile + c;			
				}
				// Check if the character is a space or an end-of-line marker
				else if ((c == ' ') || (c == '\n'))
				{
					// In this case, we add a space to the string.
					// Note: only add a space if the previous character 
					// is not also a space.  This prevents adding two spaces in a row.
					if (!textFile.endsWith(" "))
					{
							// Add a space:
							textFile = textFile + ' ';												
					}					
				}
				else
				{
					// Do nothing: skip this character.
				}							
			} // end of for loop	

			inputStream.close();
		}   // end of try block 
		catch(IOException e){
			System.out.println("Could not open file:" + e);
		}
		
		// Return the string representing the text file
		return textFile;
	}
		
	/**
	 * SplitString: Takes a string representing a file and parses it into 
	 * an array of words. 
	 * @param textFile string containing a file
	 * @return array of strings, with one word per string
	 * 
	 * Assumptions: The input string is assumed to consist of a set of words 
	 * separated by exactly one space.  
	 * 
	 * Properties: On completion, the array returned stores every word in 
	 * the initial string, one word per slot in the array. 
	 */
	private static String[] SplitString(String textFile)
	{
		// Initialize local variables:
		int stringSize = textFile.length();
		int wordCount = 0;
		String word = "";
		
		// Initialize an array of words.
		// Note: the length of the string is an overestimate of the number of words in the String.
		// As a result, we allocate too much space, wasting memory.
		// It would be better to allocate space more efficiently.
		String[] wordList = new String[stringSize];
		
		// Iterate through the string, examining every character.
		// Accumulate characters in words, detecting word breaks.
		for (int i=0; i<stringSize; i++)
		{
			// Read a character
			char c = textFile.charAt(i);
			
			// Check if the character is part of a word, or a word break.
			if ((Character.isLetter(c)) && (c != ' '))
			{
				// Here, the character is part of a word, so add it to the word.
				word += c;
			}
			else
			{
				// Otherwise, the character is not part of a word.
				// If we have found a non-empty word, add it to the list of words.
				if (word != "")
				{
					// Add the word to the list of words
					wordList[wordCount] = word;
				
					// Increment the number of words discovered.
					wordCount++;

					// Reinitialize the word to the empty string
					word = "";
				}
			}
		}
		
		// Check if there is any leftover word, once the string is complete.
		// If so, add the word to the word list, and increment the word count.
		if (word != "")
		{
			wordList[wordCount] = word;			
			wordCount++;
		}
		
		return wordList;
		
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
	 * Methodology: The sorting is accomplished via `insertion sort.'
	 */
	protected void SortWords(String[] wordList)
	{	
		// index stores the slot in the array that we are trying to fill
		int index = 0;
		// strMin stores the word we are currently sorting into place
		String sortString = null;
		// iMax stores the index of the largest sorted word
		int maxSorted = 0;
		
		// Check for errors
		if ((wordList==null) || (wordList.length==0))
		{
			throw new IllegalArgumentException("Failed in InsertionSortWords: no words to sort.");
		}

		// Iterate through every index in the array.
		// Precondition: At the beginning of each iteration of the loop, we have sorted
		// the elements in the prefix wordList[0..maxSorted].
		// The goal of the iteration is to find the proper slot for the string
		// wordList[maxSorted+1].
		// Postcondition: At the end of each iteration of the loop, we have sorted the 
		// prefix of the array wordList[0..maxSorted+1].
		for (maxSorted = 0; wordList[maxSorted+1]!= null; maxSorted++)
		{
			// First, fix the string we are going to sort into place
			sortString = wordList[maxSorted+1];
			
			// We need to find where SortString fits in the array [1..iMaxSorted+1]
			index = maxSorted+1;
			while (index > 0 && sortString.compareTo(wordList[index-1]) < 0)
			{
				wordList[index] = wordList[index-1];
				index--;
			}
			
			// Now that we have found where SortString goes,  
			// move it into place.
			wordList[index] = sortString;				
		}
	}
	
	/**
	 * CountWordFrequencies: Counts the frequency with which each word 
	 * appears in wordList. 
	 * @param wordList
	 * @return sorted array of WordCountPair, one entry per word
	 * 
	 * Assumptions: wordList holds a sorted, non-empty set of words, and
	 *
	 * Properties: On completion, the array returned is a sorted array of 
	 * word/count pairs containing one entry for every distinct word in
	 * wordList.  The count associated with each word is the number of
     * times that word appears in wordList.  
	 */
	private static WordCountPair[] CountWordFrequencies(String[] wordList)
	{
		// Check for errors:		
		if ((wordList==null) || (wordList.length<1))
		{
			throw new IllegalArgumentException("Failed in InsertionSortWords: no words to sort.");
		}
		
		// Initialize the countedWords array.
		// We use the length of the wordList as a safe estimate of the number of distinct 
		// words in the wordList.  Notice that this is an inefficient use of space,
		// as countedWords will likely be much smaller than wordList.
		WordCountPair[] countedWords = new WordCountPair[wordList.length];
		
		// Initialize the number of count/value pairs to zero.
		int numPairs = 0;
		
		// Initialize the first word to be the first word in the word list.		
		String word = wordList[0];
		// Initialize the count to be one.
		int count = 1;
		
		// Iterate through every word in m_WordList
		for (int i=1; wordList[i] != null; i++)
		{
			// If we find another copy of the word:
			if (wordList[i].equals(word))
			{
				// Then increment the count.
				count++;
			}
			else
			{
				// Otherwise, we have found a new word.
				// Store the old word and its count as new WordCountPair.
				countedWords[numPairs] = new WordCountPair(word, count);
				// Increment the number of word/count pairs that we have discovered.
				numPairs++;
				
				// Re-initialize word with the newly found word.
				word = wordList[i];
				// Re-initialize the count to be 1.
				count = 1;
			}
		}
		return countedWords;
	}
	
	/**
	 * VerifySort: Verifies that the word list is sorted.
	 * @param wordList array of words in sorted order 
	 * @throws IllegalArgumentException if the array of words is not sorted
	 * 
	 * Note: assumes that the list terminates with the first null string.
	 */
	private static void VerifySort(String[] wordList) 
	{
		// Check if there are any words to be sorted:
		if ((wordList == null) || (wordList.length < 1))
		{
			throw new IllegalArgumentException("VerifySort fails: list does not contain any words.");
		}
		
		// Iterate through the list of words and make sure that they 
		// are in properly sorted order.
		for (int i=0; wordList[i+1] != null; i++)
		{
			if (wordList[i].compareTo(wordList[i+1]) > 0)
			{
				throw new IllegalArgumentException("VerifySort fails: list badly sorted.");
			}
		}		
	}
	
}
