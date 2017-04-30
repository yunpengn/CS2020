package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CellularTestCases {
	@Test
	public void testCase1(){
		CoverageCalculator calc = new CoverageCalculator(100);
	    calc.addTower(20, 5);
	    calc.addTower(10, 5);
	    assertEquals(20, calc.getCoverage());
	}
	
	@Test
	public void testCase2(){
		CoverageCalculator calc = new CoverageCalculator(100);
	    calc.addTower(20, 5);
	    calc.addTower(10, 5);
	    calc.addTower(30, 2);
	    calc.addTower(16, 10);
	    assertEquals(25, calc.getCoverage());
	}
	
	@Test
	public void testCase3(){
		CoverageCalculator calc = new CoverageCalculator(100);
	    calc.addTower(20, 5);
	    calc.addTower(10, 5);
	    calc.addTower(30, 2);
	    calc.addTower(16, 10);
	    calc.addTower(5, 10);
	    calc.addTower(18, 2);
	    assertEquals(30, calc.getCoverage());
	}
	
	@Test
	public void testCase4(){
		CoverageCalculator calc = new CoverageCalculator(100);
		calc.addTower(20, 0);
		calc.addTower(40, 0);
		calc.addTower(60, 0);
	    assertEquals(0, calc.getCoverage());
	}
	
	@Test
	public void testCase5(){
		CoverageCalculator calc = new CoverageCalculator(100);
		try{
			calc.addTower(110, 30);
		} catch (Exception e) {
			assert (true);
		}

	    assertEquals(0, calc.getCoverage());
	}
	
	@Test
	public void testCase6() {
		CoverageCalculator calc = new CoverageCalculator(100);
		calc.addTower(5, 2);
		calc.addTower(9, 1);
		calc.addTower(10, 8);
		assertEquals(16, calc.getCoverage());
	}

	@Test
	public void testCase7() {
		CoverageCalculator calc = new CoverageCalculator(200);
		calc.addTower(166, 2);
		calc.addTower(34, 19);
		calc.addTower(54, 21);
		calc.addTower(110, 15);
		calc.addTower(124, 7);
		assertEquals(100, calc.getCoverage());
	}
}
