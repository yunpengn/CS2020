package cs2020;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Framework code for the CA (Cellular Automaton) class for
 * the CS2020 Coding Quiz, 2014
 * @author gilbert
 *
 */
public class CA implements ICA {

	// Stores the state of the CA.  Each entry should be a 0 or a 1.
	int[] m_state;
	
	// The rule for updating the CA.  This should be an array of 
	// size 8, where each entry is a 0 or a 1.
	int[] m_rule;
	
	// The size of the CA, initialized in the constructor.
	int m_size = 0;
	
	// Boolean flag indicating whether the CA is initialized.
	boolean m_initialized = false;
	
	/**
	 * Constructor
	 * @param size
	 */
	CA(int size){
		// Create a new array of the specified size.
		// Notice that we create an array with (size+2) entries.
		// m_state[0] and m_state[n-1] are dummy values marking
		// the boundary of the CA.
		m_state = new int[size+2];
		// Update the size.
		m_size = size;
		// Note that the CA has not yet been initialized
		m_initialized = false;
	}
	
	/**
	 * Method: Assigns rule to m_rule and resets m_state
	 * @param rule
	 */
	@Override
	public void initialize(int[] rule){
		
		//If rule is not of length 8 throw Exception
		if (rule.length != 8) {
			throw new IllegalArgumentException();
		} else {
			
			//Assign m_rule to the given rule
			m_rule = rule;
			
			//Resetting m_state (assign all 0s first)
			for (int i=0; i<m_state.length; i++) {
				m_state[i] = 0;
			}
			
			//if even array, both middle elements become 1
			if (m_state.length % 2 == 0) {
				m_state[m_state.length/2] = 1;
				m_state[m_state.length/2 + 1] = 1;
			
			//if odd array, middle element becomes 1
			} else {
				m_state[m_state.length/2] = 1;
			}
			
			//After assigning rule and resetting m_state
			m_initialized = true;
    	
		}

	}
	
	/**
	 * Method: Assigns m_state to new state based on given rule
	 */
	@Override
	public void step(){
		
		//If CA is not initialized, throw Exception
		if (!m_initialized) {
			try {
				throw new NotInitializedException();
			} catch (NotInitializedException e) {
				e.printStackTrace();
			}
		} else {
			
			//Temp array to store new values
			int[] tempArray = Arrays.copyOf(m_state, m_state.length);
			
			//Start from index 1 to index n-2 (since index 0 and n-1 are dummy 0s)
			for (int k=1; k<m_state.length-1; k++) {
				
				//Surrounding values of i in m_state
				int i_minus_1 = m_state[k-1];
				int i = m_state[k];
				int i_plus_1 = m_state[k+1];
				
				//Calculate rule
				int rule = (i_minus_1 * 4) +
							(i * 2) +
							(i_plus_1 * 1);
				
				//Place m_rule in
				tempArray[k] = m_rule[rule];
			} 
			//After computing all new values, assign m_state to tempArray
			m_state = tempArray;
		}
		

	}
	
	/**
	 * Class: CAITerator
	 * Provides iterator for CA class
	 * @author a0110917
	 *
	 */
	private class CAIterator implements Iterator<String> {
		
		//the CA that this iterator will iterate through
		CA myCA;
		
		/**
		 * Constructor
		 * @param myCA
		 */
		CAIterator(CA myCA) {
			this.myCA = myCA;
		}
		
		//The CA always has next because no matter the values in m_state, rule can always be calculated
		@Override
		public boolean hasNext() {
			return true;
		}
		
		@Override
		public String next() {
			String m_stateString = myCA.toString();
			
			//step next
			myCA.step();
			
			return m_stateString;
		}
		
		//Not provided for this implementation
		@Override
		public void remove() {}
		
	}
	
	/**
	 * Method: Iterator returns String representation of current state
	 * and all next steps of the CA
	 * Note: all 0s become empty spaces
	 * @return iterator
	 */
	@Override
	public Iterator<String> iterator() {
		return new CAIterator(this);
	}

	/**
	 * toString
	 * Transforms the state of the CA into a string.
	 */
	@Override
	public String toString(){
		String answer = "";
		for (int i=1; i<m_size+1; i++){
			if (m_state[i] > 0){
				answer += '1';
			}
			else {
				answer += ' ';
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args){
		
		// Create a new cellular automaton of size 31
		CA example = new CA(31);
		
		// Create a new rule
		int[] rule = {0,1,0,0,1,0,0,0};
		
		// Initialize the CA with the rule
		example.initialize(rule);
		
		// Get an iterator
		Iterator<String> iterator = example.iterator();
		
		// Run the cellular automaton for 17 steps
		for (int i=0; i<17; i++){
			if (iterator.hasNext()){
				String s = iterator.next();
				System.out.println(s);
			}
		}
	}
}
