/**
 * Class: ShiftRegisterTest
 * 
 * Purpose: This class includes a set of tests for a shift register implementation.
 * 
 * @author Niu Yunpeng
 * based on
 * @author dcsslg
 */

// This class is part of the cs2020 package.
package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShiftRegisterTest {

	/**
	 * Description: To test a ShiftRegister, update this function to instantiate
	 * the shift register.
	 * 
	 * @param size
	 * @param tap
	 * @return a new ShiftRegister
	 */
	ILFShiftRegister getRegister(int size, int tap){
		return new ShiftRegister(size, tap);
	}
	
	/**
	 * Test shift with simple example
	 */
	@Test
	public void testShift1() {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
		r.setSeed(seed);
		int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		for (int i=0; i<10; i++){
			assertEquals("ShiftTest", expected[i], r.shift());
		}	
	}
	
	/**
	 * Test generate with simple example
	 */
	@Test
	public void testGenerate1() {
		ILFShiftRegister r = getRegister(9, 7);		
		int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
		r.setSeed(seed);
		int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}	
	}
		
	/**
	 * Test register of length 1
	 */
	@Test
	public void testOneLength() {
		ILFShiftRegister r = getRegister(1, 0);		
		int[] seed = {1};
		r.setSeed(seed);
		int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i=0; i<10; i++){
			assertEquals("GenerateTest", expected[i], r.generate(3));
		}
	}

	/**
	 * Test an input array longer than what we need.
	 */
	@Test
	public void testLongerInput() {
		ILFShiftRegister r = getRegister(4, 1);		
		int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
		r.setSeed(seed);
		int[] expected = { 4, 5, 1, 4, 5, 1, 4, 5, 1, 4 };

		for (int i = 0; i < 10; i++) {
			assertEquals("GenerateTest", expected[i], r.generate(4));
		}
	}

	/**
	 * Test an input array shorter than what we need.
	 */
	@Test
	public void testShorterInput() {
		ILFShiftRegister r = getRegister(9, 7);
		int[] seed = { 1, 0, 1, 0, 1, 0 };
		r.setSeed(seed);
		int[] expected = { 1, 15, 9, 0, 5, 8, 7, 4, 4, 14 };

		for (int i = 0; i < 10; i++) {
			assertEquals("GenerateTest", expected[i], r.generate(4));
		}
	}

	/**
	 * Test an input array with bad values of element.
	 */
	@Test
	public void testBadElementValue() {
		ILFShiftRegister r = getRegister(9, 7);
		int[] seed = { 0, 3, 0, 1, 1, -5, 1, 0, 128 };
		r.setSeed(seed);
		int[] expected = { 12, 7, 10, 4, 7, 6, 4, 13, 6, 11 };

		for (int i = 0; i < 10; i++) {
			assertEquals("GenerateTest", expected[i], r.generate(4));
		}
	}

	/**
	 * Test negative value of size.
	 */
	@Test
	public void testBadSize() {
		ILFShiftRegister r = getRegister(-6, 2);
		int[] seed = { 0, 3, 0, 1, 1, -5, 1, 0, 128 };
		r.setSeed(seed);
		int[] expected = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

		for (int i = 0; i < 10; i++) {
			assertEquals("GenerateTest", expected[i], r.generate(4));
		}
	}

	/**
	 * Test tap value greater than or equal to size value
	 */
	@Test
	public void testBadTap() {
		ILFShiftRegister r = getRegister(9, 12);
		int[] seed = { 0, 3, 0, 1, 1, -5, 1, 0, 128 };
		r.setSeed(seed);
		int[] expected = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

		for (int i = 0; i < 10; i++) {
			assertEquals("GenerateTest", expected[i], r.generate(4));
		}
	}
}
