package sg.edu.nus.cs2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class: DataAnalysis
 * 
 * Description: This class is used to check the number of identical pairs of
 * entries in a database. Two entries (strings) are identical if one is a
 * permutation of the other.
 * 
 * @author Niu Yunpeng
 */
public class DataAnalysis {
	// The name of the file
	private String fileName = "";

	// The number of entries in the file
	private long numOfEntry = 0;

	// Reader and buffer for disk file input operation.
	private FileReader reader = null;
	private BufferedReader buffer = null;

	// Optimal ratio for the size of initial hashMap from experiences.
	private double sizeRatio = 1;

	// Specified-designed hashMap for this problem.
	private HashMap<Long, Integer> table = null;

	// The result of analysis: number of identical pairs of entries
	private long count = -1;

	/**
	 * Public Constructor: DataAnalysis(int)
	 * 
	 * Description: This constructor reads in the file and determines the number
	 * of entries in the database. Related initialization is done.
	 * 
	 * @param fileName
	 *            is the path to the database file.
	 */
	public DataAnalysis(String fileName) {
		this.fileName = fileName;

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

		long total = 0;
        long now;

        for (int i = 0; i < line.length(); i++) {
            now = (long) line.charAt(i);
            total += (31 << now) ^ now;
        }

		return (total * line.length()) ^ line.length();
	}

	// Getter for the current database name. Mainly for debugging purpose.
	public String getFileName() {
		return fileName;
	}
}
