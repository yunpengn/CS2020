package cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Here we test the findSmallestRange functionality of the highway.
 */
public class HighwayTestPartTwo {

	@Test
	public void testFigure1() {
		Highway highway = new Highway(10, 32);
		highway.addCustomer(12);
		highway.addCustomer(14);
		highway.addCustomer(21);
		highway.addCustomer(26);
		highway.addCustomer(27);
		
		int numThree = highway.findSmallestRange(3);
		assertEquals("3 stations require range 1.", 1, numThree);
		
		int numTwo = highway.findSmallestRange(2);
		assertEquals("2 stations require range 3.", 3, numTwo);
		
		int numOne = highway.findSmallestRange(1);
		assertEquals("1 station require range 8.", 8, numOne);							
	}
	
	@Test
	public void testOddSlots() {
		Highway highway = new Highway(1, 99);
		for (int i=1; i<100; i+=2){
			highway.addCustomer(i);
		}
					
		int numSeventeen = highway.findSmallestRange(17);		
		assertEquals("17 stations need range 2.", 2, numSeventeen);
		
		int numTwentyFive = highway.findSmallestRange(25);			
		assertEquals("25 stations need range 1.", 1, numTwentyFive);
		
		int numFive = highway.findSmallestRange(5);					
		assertEquals("5 stations need range 9.", 9, numFive);
		
		assertEquals("Checking that 5 stations need range 9:", 5, highway.numberOfStations(numFive));
		assertEquals("Checking that 5 stations need range 9:", 6, highway.numberOfStations(numFive-1));
				
	}
	
	@Test
	public void testSparseHighway() {
		Highway highway = new Highway(1, 99);
		highway.addCustomer(12);
		highway.addCustomer(98);
		assertEquals("2 stations needs 0 range.", 0, highway.findSmallestRange(2));
		assertEquals("1 station needs 43 range.", 43, highway.findSmallestRange(1));
		assertEquals("100 stations need 0 range.", 0, highway.findSmallestRange(100));
	}
		
}
