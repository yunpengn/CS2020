package sg.edu.nus.cs2020;

/**
 * Class: Record
 * 
 * Description: Stores a single record in the HerbertLog. The name is person who
 * hired Herbert. The wages are the total paid to Herbert up to and including
 * the minute recorded in this record.
 */
public class Record {

	// Name of person who hired Herbert for the current job
	String m_name;

	// Amount paid to Herbert up to and including the current minute
	long m_wages;

	// Constructor: Creates a new Record with the specified name/wages
	Record(String name, long wages){
		m_name = name;
		m_wages = wages;
	}

	// Constructor: Creates a new record that is a copy of the other record
	Record(Record r){
		m_name = r.getName();
		m_wages = r.getWages();
	}

	// Returns the name associated with this record
	String getName(){
		return m_name;
	}

	// Returns the wages associated with this record
	long getWages(){
		return m_wages;
	}

}
