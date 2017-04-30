package sg.edu.nus.cs2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class: DataAnalysisSafe
 * 
 * Description: This class is used to check the number of identical pairs of
 * entries in a database. Two entries (strings) are identical if one is a
 * permutation of the other.
 * 
 * @author Niu Yunpeng
 */
public class DataAnalysisSafe {
	// The number of entries in the file
	private long numOfEntry = 0;

	// Reader and buffer for disk file input operation.
	private FileReader reader = null;
	private BufferedReader buffer = null;

	// Optimal ratio for the size of initial hashMap from experiences.
	private double sizeRatio = 0.8;

	// Specified-designed hashMap for this problem.
	private HashMap<Long, Integer> table = null;

	// The result of analysis: number of identical pairs of entries
	private long count = -1;

	private int[] encryptTable = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
			83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
			197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
			317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443,
			449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
			593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719 };

	/**
	 * Public Constructor: DataAnalysis(int)
	 * 
	 * Description: This constructor reads in the file and determines the number
	 * of entries in the database. Related initialization is done.
	 * 
	 * @param fileName
	 *            is the path to the database file.
	 */
	public DataAnalysisSafe(String fileName) {
		// Throws an exception if the fileName format is wrong.
		if (fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException();
		}

		// Initialize the fileReader for the given database.
		try {
			reader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the file according to its name");
		}

		// Initialize the bufferedReader for the given database.
		buffer = new BufferedReader(reader);

		// Read the first line of the file to determine the number of entries.
		try {
			String num = buffer.readLine();
			numOfEntry = Long.parseLong(num);
		} catch (IOException e) {
			System.err.println("Cannot read the number of entries.");
		}

		// Initialize the special hashMap with a certain size.
		int actualSize = (int) (numOfEntry * sizeRatio);
		table = new HashMap<Long, Integer>(actualSize);
	}

	/**
	 * Public Method: long analyze()
	 * 
	 * Description: This method returns the number of identical pairs of entries
	 * in the database. For each line of entry, it will calculate the special
	 * hashCode for this entry and checks whether the current value exists in
	 * the hashMap previously.
	 * 
	 * @return the number of identical pairs of entries in the database.
	 */
	public long analyze() {
		// If analysis for the current database has been done before, return the
		// result of analysis directly.
		if (count != -1) {
			return count;
		}

		// Initialize the value of count to be 0.
		count = 0;

		// The special hashCode of the current entry
		long hashCode = 0;

		// The original value of this key in the hashMap
		Integer origin = null;

		// FingerPrint hashTable may become possible for this problem as long as
		// you use an extremely good hash function.
		for (long i = 0; i < numOfEntry; i++) {
			hashCode = getHashCode();
			origin = table.get(hashCode);

			if (origin == null) {
				// Change the value to be 1 if this hashCode does not exist in
				// the hashMap before.
				table.put(hashCode, 1);
			} else {
				// If the original value corresponding to this key is n, then
				// there are n more identical pairs after this new entry is
				// inserted. Then, update the value by incrementing.
				count = count + origin;
				table.put(hashCode, origin + 1);
			}
		}

		return count;
	}

	/**
	 * Private Method: long getHashCode();
	 * 
	 * Description: This method reads in the next entry in the database. Then,
	 * it calculates the hashCode of this entry. After that, it checks whether
	 * this value of hashCode exists in the database.
	 * 
	 * @return the hashCode of the current line defined by MPQ algorithm.
	 */
	private long getHashCode() {
		String line = "";

		try {
			line = buffer.readLine();
		} catch (IOException e) {
			System.err.println("Cannot read the current line of entry");
		}

		long total = 1;
		int now;
		int size = line.length();

		for (int i = 0; i < size; i++) {
			now = (int) line.charAt(i);
			total *= encryptTable[now];
		}

		return total;
	}
}
