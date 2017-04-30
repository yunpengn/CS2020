package sg.edu.nus.cs2020;

/*
 * 
 * @author gilbert
 * Interface: ILFShiftRegister
 * Description: a linear feedback shift register based on XOR with one tap
 * 
 */

public interface ILFShiftRegister {
	
	// Sets the value of the shift register to the specified seed.
	public void setSeed(int[] seed);
	
	// Shifts the register one time, returning the low-order bit.
	public int shift();
	
	// Shifts the register k times, returning a k-bit integer.
	public int generate(int k);

}

