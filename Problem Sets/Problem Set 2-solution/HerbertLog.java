package sg.edu.nus.cs2020;

//  Import file handling classes
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Solutions for PS2 Qn 2
 * @author Goh Chun Teck
 */

public class HerbertLog {
		
	/**
	 *  Public static final constants
	 */
	
	// Separator character used in the database file
	public static final String SEP = ":";
	// Length of each record in the database
	public static final int rLength = 18;
	// Padding character for database file
	public static final char PADDING = '.';

	/**
	 * Private state for the HerbertLog
	 */
	// Filename where the database can be found
	private String m_name = null;
	// Variable that points to the database, once opened
	private File m_file = null;
	// Variable for reading from the database file
	private RandomAccessFile m_inRAM = null;
	// Size of the database: number of available records
	private int m_numMinutes = 0;
	
	/**
	 * Debugging information
	 */
	// Number of "get" operations performed on the database
	// Note this is primarily for debugging.
	protected int m_numGets = 0;
	
	/**
	 * Constructor 
	 * @param filename File where the database can be found.
	 * The specified file must exist, and must contain records
	 * in the proper format. 
	 **/
	HerbertLog(String fileName){
		// Save the filename
		m_name = fileName;
		// Next, we open the file
		try {
			// Open the file
			m_file = new File(m_name);
			m_inRAM = new RandomAccessFile(m_file, "r");
			
			// Calculate the number of records in the database by
			// dividing the number of characters by the length of each record
			long numChars = m_inRAM.length();
			m_numMinutes = (int) (numChars/rLength);
		} catch (IOException e) {
			System.out.println("Error opening file: " + e);
		}
	}
	
	/**
	 * size
	 * @return the number of records in the database
	 */
	public int numMinutes(){
		return m_numMinutes;
	}

    /**
	 * numGets : primarily for debugging
     * @return number of times get has been called
     */
    public int numGets(){
	    return m_numGets;
    }
    
    /**
	 * resetGets : primarily for debugging
     * @return resets the number of times get is called back to 0
     */
    public void resetGets(){
    	m_numGets = 0;
    }

    
	/**
	 * get
	 * @param i specified the record number to retrieve, starting from 0
	 * @return the specified record, if it exists, or null otherwise
	 */
	public Record get(int i){
		
		// Increment the number of "get" operations
		m_numGets++;
		
		// Check for errors: if i is too large or too small, fail
		if (i > numMinutes()) return null;
		if (i < 0) return null;
		
		// Retrieve the proper record
		try {
			// First, calculate the offset into the file, and seek to that location
			int numChars = i*rLength;			
			m_inRAM.seek(numChars);
			
			// Next, read in rLength bytes
			// Recall that rLength is the length of one record
			byte[] entry = new byte[rLength];
			m_inRAM.read(entry);
			
			// Now, convert the string to a record.
			// Convert it to a string...
			String line = new String(entry);
			// .. parse the string using the record separator
			String[] tokens = line.split(SEP);
			// Every record should have 2 or 3 components
			assert(tokens.length==2 || tokens.length==3);
			// The first token is the name
			String name = tokens[0];
			// The second token is the height
			int height = Integer.parseInt(tokens[1]);
			return new Record(name, height);
			
		} catch (IOException e) {
			System.out.println("Error getting data from file: " + e);
		}
		// If the record wasn't found, for any reason, return null
		return null;
	}
	
	
    
/*
 * --------------------Solution for Qn 2a ------------------------------------
 */

	/**
	 * This is the simple method of calculating the salary. It goes through the
	 * entire log line by line.
	 */
	public int simpleCalculateSalary(int start, int end){
		int salary = 0;
		Record next;
		Record current = get(start);
		for (int i = start; i < end; i++){
			next = get(i + 1);
			if (current.getName().compareTo(next.getName()) != 0){
				salary += current.getWages();
			}
			current = next;
		}
		salary += current.getWages();
		
		return salary;
		
	}
	
	/**
	 * This is a faster way of calculating the salary. It halves the log at each
	 * step, and attempts to calculate the salary for each half. This is done
	 * recursively.
	 * 
	 */	
	public int fastCalculateSalary(int start, int end){
		Record startRecord = get(start);
		Record endRecord = get(end);
		
		int lastWages = endRecord.getWages();
		return lastWages + fastCalculateSalary(start, startRecord, end, endRecord);
	}
	
	/**
	 * Actual recursion for calculation of salary is performed here.
	 * 
	 * Note that what we actually want to do is add up the salary of each name.
	 * The salary of each name is found on the line where the name appears last
	 * in the log.
	 * 
	 * Base cases for recursion:
	 * 1. Recursion stops when you reach a segment of the log where the start and
	 *    end name is the same. At this point, there is no need to continue recursion
	 *    for this particular segment. We return 0
	 * 2. We are down to a segment of 2, where the startName and endName is 
	 *    different. We get the salary of the start name (since the name appears
	 *    last in the log). Note that it is not possible for startName and endName
	 *    to be the same, this is already covered in base case 1
	 *  
	 * 
	 * If we are not at the base cases, we would have to halve the segment
	 * recursively. 
	 * 
	 */
	public int fastCalculateSalary(int start, Record startRecord, int end, Record endRecord){
		//If the segment we are in only has a single name, we are done
		if(startRecord.getName().compareTo(endRecord.getName()) == 0){
			return 0;
		}

		// If we are down to a segment of 2:
		if (end == start+1){
			// return the final cost from the startName.
			return startRecord.getWages();
		}
		
		// Find the middle point
		int middle = (start + end) / 2;
		Record middleRecord = get(middle);
		
		//Recursively calculate the salary. Remember to add extra as well
		return fastCalculateSalary(start, startRecord, middle, middleRecord) + 
				fastCalculateSalary(middle, middleRecord, end, endRecord);
	}
	
	
	/**
	 * Performs the calculation of salary
	 */
	public int calculateSalary(){
		//Simple Calculation
		//return simpleCalculateSalary(0, numMinutes() - 1);
		//Fast Calculation
		return fastCalculateSalary(0, numMinutes() - 1);
	}
	
/*
 * -------------------End Solution for Qn 2a----------------------------------
 */
	
	
	
/*
 * --------------------Solution for Qn 2b ------------------------------------
 */	
	/**
	 * To determine the minimum minutes, we start from 1 minute, calculate
	 * the salary, and check if it reaches the goalIncome. If it does not, we
	 * keep incrementing the number of minutes by 1, and calculate until we
	 * determine the minimum number of minutes that is sufficient to reach the
	 * goalIncome.
	 * 
	 * Can we do better?
	 * 
	 * We can choose to binary search the answer! 
	 */
	public int calculateMinimumWork(int goalIncome){
		
		int maxWages = calculateSalary();
		
		// Too unrealistic goalIncome! Herbert can never earn this much =(
		if (goalIncome > maxWages){
			return -1;
		}
		
		//Begin by checking 1 minute. Double the limit until we reach the goalIncome
		int limit = 1;
		int actualWages = calculateWagesWithLimit(limit);
		while (actualWages < goalIncome){
			limit *= 2;
			actualWages = calculateWagesWithLimit(limit);
		}

		//Now we have the upper limit (limit) and the lower limit (limit / 2)
		//Begin searching for the ideal number of minutes
		int start = limit / 2;
		int end = limit;
		int middle = 0;
		while (start < end - 1){
			middle = (start + end) / 2;
			actualWages = calculateWagesWithLimit(middle);
			if (actualWages < goalIncome){
				start = middle;
			}
			else{
				end = middle;
			}
		}
		return end;
	}
	
	/**
	 * This function calculates the maximum wages possible by working [limit] 
	 * number of minutes for each name
	 */
	public int calculateWagesWithLimit(int limit){
		int start = 0;
		int end = numMinutes() - 1;
		Record startRecord = get(start);
		Record endRecord = get(end);
		
		int firstWages = findWagesOfNameWithLimit(start, limit);
		return firstWages + calculateSalaryWithLimit(start, startRecord, end, endRecord, limit);
	}
	
	/**
	 * Finds the wages startName would have to pay based on the limit
	 */
	private int findWagesOfNameWithLimit(int start, int limit){
		Record startRecord = get(start);
		
		int begin = start;
		int end = start + limit - 1;
		if (end >= numMinutes()) {
			end = numMinutes() - 1;
		}
		Record endRecord = get(end);
		
		// First check if (start + limit - 1) has the same name.
		// If so, we are done and just return the salary at that point
		// This means that startName has hired Herbert for more than [limit] minutes
		if (startRecord.getName().compareTo(endRecord.getName())==0){
			return endRecord.getWages();
		}
		
		// Otherwise, we need to do a binary search to find the ending point.
		// This means that startName has hired Herbert for less than [limit] minutes		
		int middle = begin;
		Record middleRecord;
		while (begin < end - 1){
			middle = (begin + end) / 2;
			middleRecord = get(middle);
			if (startRecord.getName().compareTo(middleRecord.getName()) == 0) {
				begin = middle;
			}
			else {
				end = middle;
			}
		}
		return get(begin).getWages();
	}
	
	/**
	 * This is very similar to fastCalculateSalary in Qn 2a. It follows the same 
	 * concept.
	 */
	private int calculateSalaryWithLimit(int start, Record startRecord, int end, Record endRecord, int limit){
		//If the segment we are in only has a single name, we are done
		if (startRecord.getName().compareTo(endRecord.getName()) == 0){
			return 0;
		}
		
		// Find the middle point
		int middle = (start + end) / 2;
		Record middleRecord = get(middle);
		Record middleRecordPlus = get(middle + 1);
		
		// If we are down to a segment of 2:
		if (end == start + 1){
			//return the limit cost from the new segment
			return findWagesOfNameWithLimit(end, limit);
		}
		
		// If you halve the log segment at a boundary, add the salary
		int extra = 0;
		if (middleRecord.getName().compareTo(middleRecordPlus.getName()) != 0){
			extra = findWagesOfNameWithLimit(middle + 1, limit);
		}
		
		//Recursively calculate the salary. Remember to add extra as well
		return calculateSalaryWithLimit(start, startRecord, middle, middleRecord, limit) + 
		calculateSalaryWithLimit(middle + 1, middleRecordPlus, end, endRecord, limit) + extra;	
	}
	
	
/*
 * -------------------End Solution for Qn 2b----------------------------------
 */
	
	
	public static void main(String[] args){
		HerbertLog log = new HerbertLog("shortNamesHerbert.txt");
		
		log.resetGets();
		System.out.println("Calculation of Salary: $" + log.calculateSalary());
		System.out.println("Number of Gets: " + log.numGets());
		
		log.resetGets();
		System.out.println("Minimum minutes needed to get $300: " + log.calculateMinimumWork(300));
		System.out.println("Number of Gets: " + log.numGets());
		
	}
}
