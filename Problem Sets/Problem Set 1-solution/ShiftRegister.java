/**
 * Problem set 1, Problem 3
 * Sample code for a Linear Feedback Shift Register
 * CS2020 2012
 */
package cs2020;

/**
 * Class: ShiftRegister implements a linear feedback shift register
 * Implements: ILFShiftRegister interface
 * @author dcsslg
 */
public class ShiftRegister implements ILFShiftRegister {

	// Size of the shift register
	int m_size = -1;
	// Tap of the shift register
	int m_tap = -1;
	// Integer array for storing the shift register bits
	int[] m_register = null;
		
	/**
	 * Constructor sets the size and the tap
	 * @param size of the shift register
	 * @param tap of the shift register
	 */
	ShiftRegister(int size, int tap){		
		m_size = size;
		m_tap = tap;
		if ((tap >= 0) && (tap < size) && (size > 0)){
			m_register = new int[m_size];
			// Initialize the shift register to zero
			for (int i=0; i<m_size; i++){
				m_register[i] = 0;
			}	
		}
		else{
			System.out.println("Error: invalid tap.");
		}
	}
	
	/**
	 * setSeed
	 * @param value the initial seed for the shift register
	 * Description: initializes the shift register to the specified value.
	 * Note: the value should be specified such that value[0] is the least-significant-bit,
	 * while value[m_size-1] is the most-significant-bit.
	 */
	public void setSeed(int[] value) 
	{
		// Check that the input array is of the right size.
		if (value.length != m_size){
			System.out.println("Error: bad seed.");
		}
		else if (m_register == null){
			System.out.println("Error: badly initialized register");
		}
		else{
			// Copy the register into the shift-register.
			// Notice that it is good practice to copy the value, rather than
			// to simple set "m_register=value;"
			for (int i=0; i<m_size; i++){
				/*
				// Check for bad input
				if ((value[i] < 0) || (value[i] > 1)){
					System.out.println("Error: Bad seed.");
					// Reinitialize register and return
					for (int j=0; j<m_size; j++){
						m_register[i] = 0;
					}
					return;
				}
				*/
				m_register[i] = value[i];
			}
		}
	}
	
	/**
	 * shift 
	 * @return new value of the least-significant-bit
	 * Description: shifts the shift register one bit 
	 */
	public int shift(){
		// Check for errors
		if (m_register == null){
			System.out.println("Error: Badly initialized register.");
			return -1;
		}
		// Calculate the new bit
		int newBit = (m_register[m_tap] ^ m_register[m_size-1]);
		
		// Shift the register one slot
		for (int i=m_size-1; i>0; i--){
			m_register[i] = m_register[i-1];
		}
		
		// Set the new bit and return
		m_register[0] = newBit;
		return newBit;
	}
	
	/**
	 * generate
	 * @param k number of bits to generate
	 * @return integer value of the k bits
	 * Description: shifts the register k times, returning the integer
	 * value of the k bits returned
	 */
	public int generate(int k){
		// Check for errors
		if (m_register == null){
			System.out.println("Error: Badly initialized register.");
			return -1;
		}
		
		// Accumulate the answer in returnInt
		int returnInt = 0;
		
		// Shift the register k times
		for (int i=0; i<k; i++){
			// Each time, double the accumulator
			returnInt *= 2;
			// Shift and get the new bit
			int newBit = shift();
			// Add the new bit to the accumulator
			returnInt += newBit;			
		}
		return returnInt;
	}
	
	/**
	 * toStrong
	 * @return string representation of the shift register
	 * Description: converts the shift register to a string of 0's and 1's
	 */
	public String toString(){
		String out = "";
		// For each element in the shift register:
		for (int i=0; i<m_size; i++){
			// If the cell is a 1, add a '1' to the string
			// If the cell is a 0, add a '0' to the string
			if (m_register[i] == 1){
				out = '1' + out;
			} 
			else{
				out = '0' + out;
			}
		}
		return out;
	}
	
	static public void main(String[] args){
		ILFShiftRegister r = new ShiftRegister(13, 5);		
		int[] seed = {1,1,0,0,1,0,0,0,0,1,0,0,1};
		r.setSeed(seed);
		for (int i=0; i<1000; i++){
			System.out.print(r.generate(10)+",");
		}
	}
}
