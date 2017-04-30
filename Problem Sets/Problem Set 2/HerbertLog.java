package sg.edu.nus.cs2020;

//  Import file handling classes
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class: HerbertLog
 * 
 * Description: The HerbertLog class records the jobs worked by Herbert, and the
 * wages paid to Herbert, over the last period of employment. The constructor
 * opens the specified log-file, and get method returns records from the file.
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
	protected long m_numMinutes = 0;

	/**
	 * Debugging information
	 */
	// Number of "get" operations performed on the database
	// Note this is primarily for debugging.
	protected long m_numGets = 0;

	/**
	 * Constructor: HerbertLog(String fileName)
	 * 
	 * @param fileName
	 *            File where the database can be found. The specified file must
	 *            exist, and must contain records in the proper format.
	 **/
	public HerbertLog(String fileName) {
		// Save the filename
		m_name = fileName;

		// Next, we open the file
		try {
			// Open the file
			m_file = new File(m_name);
			m_inRAM = new RandomAccessFile(m_file, "r");

			// Calculate the number of records in the database by dividing the
			// number of characters by the length of each record.
			long numChars = m_inRAM.length();
			m_numMinutes = numChars / rLength;
		} catch (IOException e) {
			System.out.println("Error opening file: " + e);
		}
	}

	/**
	 * Public Method: long numMinutes()
	 * 
	 * @return the number of records in the database
	 */
	public long numMinutes(){
		return m_numMinutes;
	}

	/**
	 * Public Method: long numGets()
	 * 
	 * @return number of times get has been called
	 */
	public long numGets(){
		return m_numGets;
	}

	/**
	 * Public Method: Record get(long i)
	 * 
	 * @param i
	 *            specified the record number to retrieve, starting from 0
	 * 
	 * @return the specified record, if it exists, or null otherwise
	 */
	public Record get(long i){
		// Increment the number of "get" operations
		m_numGets++;

		// Check for errors: if i is too large or too small, fail
		if (i < 0 || i >= numMinutes()) {
			return null;
		}

		// Retrieve the proper record
		try {
			// Calculate the offset into the file, and seek to that location.
			long numChars = i * rLength;
			m_inRAM.seek(numChars);

			// Next, read in rLength (the length of one record) bytes
			byte[] entry = new byte[rLength];
			m_inRAM.read(entry);

			// Convert it to a string...
			String line = new String(entry);
			// .. parse the string using the record separator
			String[] tokens = line.split(SEP);

			// Every record should have 2 or 3 components
			assert (tokens.length == 2 || tokens.length == 3);

			// The first token is the name
			String name = tokens[0];
			// The second token is the height
			long height = Integer.parseInt(tokens[1]);

			return new Record(name, height);
		} catch (IOException e) {
			System.out.println("Error getting data from file: " + e);
		}

		// If the record wasn't found, for any reason, return null
		return null;
	}

	/**
	 * Public Method: RecordFast getFast(long i)
	 * 
	 * @return an object of type RecordFast instead of Record.
	 */
	public RecordFast getFast(long i) {
		Record temp = get(i);
		RecordFast result = new RecordFast(i, temp.getName(), temp.getWages());

		return result;
	}

	public long calculateSalary() {
		int sum = 0;
		Record last = get(0);
		Record now = null;

		for (long i = 1; i < m_numMinutes; i++) {
			now = get(i);

			// If the name in this line is different from the last line, a new
			// employer has started. So the wages on the last line is the final
			// total wage from the last customer.
			if (!now.getName().equals(last.getName())) {
				sum += last.getWages();
			}
			
			last = now;
		}
		// The wage from the last customer has not been added to the sum.
		sum += now.getWages();

		return sum;
	}

	/**
	 * Public Method: long calculateMinimumWork(long goalIncome)
	 * 
	 * Description: This method calculates the minimum time (in minutes) such
	 * that Herbert can earn at least goalIncome if he works for each employer
	 * no more than this time limit.
	 * 
	 * @param goalIncome
	 *            The minimum amount of income that Herbert needs to achieve
	 *            under certain working time limit.
	 * 
	 * @return The minimum working time limit (in minutes) to earn at least a
	 *         certain amount of money. If Herbert cannot earn that much, then
	 *         returns -1.
	 */
	public long calculateMinimumWork(long goalIncome) {
		// This array stores the amount of salary Herbert will receive for his
		// corresponding working time limit. E.g. salary[0] means the salary he
		// will receive if he works at most 1 minute for each employer.
		// Notice: The length of this array is so long, which makes the program
		// quite inefficient.
		// We may use a linked list to avoid this.
		long[] salary = new long[(int) m_numMinutes];
		
		// Stores the information of the current employer.
		int nowMinutes = 0;
		String nowName = "";
		long lastMinuteWage = 0;
		
		// Stores the corresponding values of amounts of salaries into array.
		for (long i = 0; i < m_numMinutes; i++) {
			Record this1 = get(i);

			if (this1.getName().equals(nowName)) {
				salary[nowMinutes] += (this1.getWages() - lastMinuteWage);
				nowMinutes++;
			} else {
				nowName = this1.getName();
				salary[0] += this1.getWages();
				nowMinutes = 1;
			}
			lastMinuteWage = this1.getWages();
		}

		// Checks which is the minimum working time limit.
		for (int i = 0, total = 0; i < m_numMinutes; i++) {
			total+=salary[i];

			if (total >= goalIncome) {
				return i + 1;
			}
		}

		return -1;
	}
}
