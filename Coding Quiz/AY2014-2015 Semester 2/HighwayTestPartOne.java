package cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * Here we test the numberOfStations functionality of the highway.
 */
public class HighwayTestPartOne {

	@Test
	public void testFigure1() {
		Highway highway = new Highway(10, 32);
		highway.addCustomer(12);
		highway.addCustomer(14);
		highway.addCustomer(21);
		highway.addCustomer(26);
		highway.addCustomer(27);
		
		int numThree = highway.numberOfStations(3);
		assertEquals("Range 3 yields 2 stations.", 2, numThree);
		
		int numTwo = highway.numberOfStations(2);
		assertEquals("Range 2 yields 3 stations.", 3, numTwo);
		
		int numOne = highway.numberOfStations(1);
		assertEquals("Range 1 yields 3 stations.", 3, numOne);
		
		int numZero = highway.numberOfStations(0);
		assertEquals("Range 0 yields 5 stations.", 5, numZero);				
	}
	
	@Test
	public void testOddSlots() {
		Highway highway = new Highway(1, 99);
		for (int i=1; i<100; i+=2){
			highway.addCustomer(i);
		}
					
		int numTwo = highway.numberOfStations(2);		
		assertEquals("Range 2 yields 17 stations.", 17, numTwo);
		
		int numOne = highway.numberOfStations(1);		
		assertEquals("Range 1 yields 25 stations.", 25, numOne);
		
		int numTen = highway.numberOfStations(10);			
		assertEquals("Range 1 yields 5 stations.", 5, numTen);
				
	}
	
	@Test
	public void testSparseHighway() {
		Highway highway = new Highway(1, 99);
		highway.addCustomer(12);
		highway.addCustomer(98);
		assertEquals("Range 20 yields 2 stations.", 2, highway.numberOfStations(20));
		assertEquals("Range 60 yields 1 stations.", 1, highway.numberOfStations(60));
		assertEquals("Range 300 yields 1 stations.", 1, highway.numberOfStations(300));
	}
	
	@Test
	public void testError() {
		Highway highway = new Highway(1, 99);
		boolean caught = false;
		try {
			highway.addCustomer(100);
		}
		catch(IllegalArgumentException e){
			caught = true;			
		}
		assertEquals("Caught exception.", true, caught);
	}

}
