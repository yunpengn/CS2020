/**
 * Class: BetterVectorTextFile
 * 
 * Purpose: Represents a text file as a vector, i.e., 
 * as a sorted array of word/count pairs that appear 
 * in the text file.
 * 
 * Inherits from: VectorTextFile
 * 
 * Constructor: BetterVectorTextFile(String fileName)
 * 
 * All the functionality is equivalent to the VectorTextFile,
 * except for reading in a file, which is performed much more
 * efficiently.
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

// This class uses the following two packages (for manipulating files)
import java.io.FileInputStream;
import java.io.IOException;

// The new class is declared here, and it extends VectorTextFile
public class BetterVectorTextFile extends VectorTextFile {
	
	/**
	 * Constructor: BetterVectorTextFile
	 * @param name filename with which to create this vector
	 */
	BetterVectorTextFile(String name) {
		// Call the parent class constructor
		super(name);
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
	 * 
	 * Unlike the original VectorTextFile ReadFile implementation, this
	 * version runs in O(n) times.
	 */
	protected String ReadFile(String fileName) 
	{
		// Declare and initialize local variables 
		FileInputStream inputStream = null;		
		int size = 0;		
		char[] charBuffer = null;		
		int charCount = 0;
								
		// Begin a block of code that handles exceptions
		try{
			// Open the file as a stream
			inputStream = new FileInputStream(fileName);
			
			// Determine the size of the file, in bytes
			size = inputStream.available();
			
			// Initialize the char buffer to be arrays of the appropriate size.									
			charBuffer = new char[size];			
			
			// Read in the file, one character at a time.
			// For each character, normalize it, removing punctuation and capitalization.
			for (int i=0; i<size; i++)
			{
				// Read a character
				char c = (char)inputStream.read();
				
				// Ensure that the character is lower-case
				c = Character.toLowerCase(c);
				
				// Check if the character is a letter, or whitespace, or a new line
				if (Character.isLetter(c))
				{					
					charBuffer[charCount] = c;	
					charCount++;
				}
				else if ((c == ' ') || (c == '\n'))
				{
					// In this case, we add a space to the string.
					// Note: only add a space if the previous character 
					// is not also a space.  This prevents adding two spaces in a row.
					if ((charCount > 0) && (charBuffer[charCount-1] != ' '))
					{
						charBuffer[charCount] = ' ';	
						charCount++;
					}					
				}
				else
				{
					// do nothing
				}
			} // end of for loop
			inputStream.close();
		}   // end of try block 
		catch(IOException e){
			System.out.println("Could not open file: " + e);
		}
		
		// Return a string built out of the character buffer
		return new String(charBuffer);
	}
}
