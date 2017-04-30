package sg.edu.nus.cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrecisionTest {

	@Test
	public void samplePrecisionTest() {
		Precision p = new Precision();
		assertTrue("0.2".equals(p.calculateInverse(5)));
		assertTrue("0.[142857]".equals(p.calculateInverse(7)));
		assertTrue("0.08[3]".equals(p.calculateInverse(12)));
	}

}
