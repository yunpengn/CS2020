package cs2020;
import java.util.*;

/**
 * Sample solution for Coding Quiz 2014 P2
 * 
 * @author Khor Shi-Jie
 * 
 */
public class CA implements ICA {

	// Stores the state of the CA. Each entry should be a 0 or a 1.
	int[] m_state;

	// The rule for updating the CA. This should be an array of
	// size 8, where each entry is a 0 or a 1.
	int[] m_rule;

	// The size of the CA, initialized in the constructor.
	int m_size = 0;

	// Boolean flag indicating whether the CA is initialized.
	boolean m_initialized = false;

	/**
	 * Constructor
	 * 
	 * @param size
	 */
	CA(int size) {
		// Create a new array of the specified size.
		// Notice that we create an array with (size+2) entries.
		// m_state[0] and m_state[n-1] are dummy values marking
		// the boundary of the CA.
		m_state = new int[size + 2];
		// Update the size.
		m_size = size;
		// Note that the CA has not yet been initialized
		m_initialized = false;
	}

	public void initialize(int[] rule){
		if (rule == null || rule.length != 8 || m_size <= 0) {
			System.out.println("Bad Rule!");
			return;
		}

		m_rule = Arrays.copyOf(rule, 8);
		Arrays.fill(m_state, 0);

		// Place 1 in the middle of the state
		if (m_size % 2 == 1) {
			m_state[m_size / 2 + 1] = 1;
		} else {
			m_state[m_size / 2 + 1] = m_state[m_size / 2] = 1;
		}

		m_initialized = true;
	}

	public void step() {
		if (!m_initialized) {
			System.out.println("CA not initialised!");
			return;
		}

		int[] newState = new int[m_size + 2];
		newState[0] = 0;
		newState[m_size + 1] = 0;
		for (int i = 0; i < m_size; i++) {
			// Binary number conversion
			int index = m_state[i + 2] + 2 * m_state[i + 1] + 4 * m_state[i];
			newState[i + 1] = m_rule[index];
		}
		m_state = Arrays.copyOf(newState, m_size + 2);
	}

	public Iterator<String> iterator() {
		if (!m_initialized) {
			System.out.println("CA not initialised!");
			return null;
		} else {
			return new CAIterator(this);
		}
	}

	public class CAIterator implements Iterator<String> {

		CA m_ca;
		boolean started;

		CAIterator(CA ca) {
			m_ca = ca;
			started = false;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public String next() {
			// Only call step if the initial state has already been iterated
			if (!started) {
				started = true;
			} else {
				m_ca.step();
			}
			return m_ca.toString();
		}

		@Override
		public void remove() {
		}

	}

	/**
	 * toString Transforms the state of the CA into a string.
	 */
	@Override
	public String toString() {
		String answer = "";
		for (int i = 1; i < m_size + 1; i++) {
			if (m_state[i] > 0) {
				answer += '1';
			} else {
				answer += ' ';
			}
		}

		return answer;
	}

	public static void main(String[] args) {

		CA testCA = new CA(99);
		int[] rule = {1,0,0,1,0,0,0,1};
		testCA.initialize(rule);
		StringBuilder sb = new StringBuilder();
		sb.append(testCA.toString());
		for(int i = 0; i < 100; i++){
			testCA.step();
			sb.append("\n");
			sb.append(testCA.toString());
		}
		System.out.println(sb);
	}
}
