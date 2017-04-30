package coding_quiz;

import java.util.Iterator;

/**
 * Public Class: CA
 * 
 * Description: This class implements a one-dimensional cellular automation. It
 * implements the ICA interface.
 * 
 * @author Niu Yunpeng
 * based on 
 * @author gilbert
 */
public class CA implements ICA {
	// Stores the state of the CA. Each entry should be a 0 or a 1.
	int[] m_state;
	
	// An array of size 8 storing the rules for updating CA.
	int[] m_rule;
	
	// The size of the CA, initialized in the constructor.
	int m_size = 0;
	
	// Boolean flag indicating whether the CA has been initialized.
	boolean m_initialized = false;
	
	/**
	 * Public Constructor: CA(int)
	 * 
	 * Description: This constructor initialize the cells to be of a certain
	 * size. All cells are initialized to be 0. In fact, we will create an array
	 * of size + 2 entries for convenience.
	 * 
	 * @param size
	 */
	public CA(int size) {
		// m_state[0] and m_state[n-1] are dummy values for two boundaries.
		m_state = new int[size+2];

		// Update the size.
		m_size = size;

		// Note that the CA has not yet been initialized
		m_initialized = false;
	}
	
	/**
	 * Public Method: void initialize(int[])
	 * 
	 * Description: This method initiaizes the rules for updating cells.
	 */
	public void initialize(int[] rule) throws IllegalArgumentException {
		if (rule == null || rule.length != 8) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < 8; i++) {
			if (rule[i] != 0 && rule[i] != 1) {
				throw new IllegalArgumentException();
			}
		}

		m_rule = new int[8];
		System.arraycopy(rule, 0, m_rule, 0, 8);
		
		for(int i = 0; i< m_size + 2; i++) {
			m_state[i] = 0;
		}

		if (m_size % 2 == 1) {
			m_state[(m_size + 1) / 2] = 1;
		} else {
			m_state[m_size / 2] = 1;
			m_state[m_size / 2 + 1] = 1;
		}

		m_initialized = true;
	}
		
	/**
	 * Public Method: void step()
	 * 
	 * Description: Calculates the next state of all cells based on the rules
	 * given. If no rules have been specified, it will throw an exception.
	 */
	public void step() throws UninitializedException {
		if (!m_initialized) {
			throw new UninitializedException();
		}

		// Create a temporary array for storing states
		int[] temp = new int[m_size + 2];
		System.arraycopy(m_state, 0, temp, 0, m_size + 2);

		// Condition for the current cell corresponding to index for rule used.
		int condition = 0;
		
		for (int i = 1; i <= m_size; i++) {
			condition = m_state[i - 1] * 4 + m_state[i] * 2 + m_state[i + 1];
			temp[i] = m_rule[condition];
		}

		System.arraycopy(temp, 0, m_state, 0, m_size + 2);
	}
	
	/**
	 * Public Method: Iterator<String> iterator()
	 * 
	 * @return an iterator for the collection of cells.
	 */
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			boolean ultimate = false;
			
			// The cells will not change anymore if all of them die.
			public boolean hasNext() {
				if (!m_initialized || ultimate) {
					return false;
				}

				for (int i = 1; i <= m_size; i++) {
					if (m_state[i] != 0) {
						return true;
					}
				}

				ultimate = true;
				return true;
			}

			// Calculates the next step and print the state of all cells.
			public String next() {
				String state = CA.this.toString();

				// Calculates the next step of state of all cells.
				try {
					step();
				} catch (UninitializedException e) {
					;
				}

				return state;
			}
		};
	}

	/**
	 * Public Method: string toString()
	 * 
	 * @return a string representing the states of all cells.
	 */
	public String toString() {
		String answer = "";

		for (int i = 1; i <= m_size; i++) {
			if (m_state[i] > 0){
				answer += '1';
			}
			else {
				answer += ' ';
			}
		}
		
		return answer;
	}
	
	/**
	 * Public Static Method: void main(String[])
	 * 
	 * Description: Main method for debugging purpose.
	 */
	public static void main(String[] args){
		// Create a new cellular automaton of size 31
		CA example = new CA(31);
		
		// Create a new rule
		int[] rule = {0, 1, 0, 0, 1, 0, 0, 0};
		
		// Initialize the CA with the rule
		example.initialize(rule);
		
		// Get an iterator
		Iterator<String> iterator = example.iterator();
		
		// Run the cellular automaton for 17 steps
		for (int i = 0; i < 17; i++) {
			if (iterator.hasNext()) {
				String s = iterator.next();
				System.out.println(s);
			}
		}
	}
}
