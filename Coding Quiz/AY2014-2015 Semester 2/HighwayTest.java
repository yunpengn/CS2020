package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

/**
 * Public Class: HighwayTest
 * 
 * Description: Java Unit Test cases for the Highway class.
 * 
 * @author Niu Yunpeng
 */
public class HighwayTest {
	// Generator for random numbers
	private Random generator = new Random();

	@Test
	public void numberOfStationsTest1() {
		Highway road = new Highway(10, 32);

		road.addCustomer(12);
		road.addCustomer(14);
		road.addCustomer(21);
		road.addCustomer(26);
		road.addCustomer(27);

		int result = road.numberOfStations(3);
		
		assertEquals("Number of Stations Test 1", 2, result);
	}

	@Test
	public void numberOfStationsTest2() {
		Highway road = new Highway(10, 32);

		road.addCustomer(12);
		road.addCustomer(14);
		road.addCustomer(16);
		road.addCustomer(18);
		road.addCustomer(21);
		road.addCustomer(23);
		road.addCustomer(25);
		road.addCustomer(27);
		road.addCustomer(29);
		road.addCustomer(31);

		int result1 = road.numberOfStations(2);
		int result2 = road.numberOfStations(3);

		assertEquals("Number of Stations Test 2 - result1", 4, result1);
		assertEquals("Number of Stations Test 2 - result2", 3, result2);
	}

	@Test
	public void numberOfStationsTest3() {
		Highway road = new Highway(10, 32);

		road.addCustomer(12);
		road.addCustomer(12);
		road.addCustomer(12);
		road.addCustomer(16);
		road.addCustomer(16);
		road.addCustomer(16);
		road.addCustomer(23);
		road.addCustomer(23);
		road.addCustomer(25);
		road.addCustomer(27);
		road.addCustomer(27);
		road.addCustomer(27);

		int result = road.numberOfStations(2);

		assertEquals("Number of Stations Test 3", 2, result);
	}

	@Test
	public void smallestRangeTest1() {
		Highway road = new Highway(10, 32);

		road.addCustomer(12);
		road.addCustomer(14);
		road.addCustomer(21);
		road.addCustomer(26);
		road.addCustomer(27);

		int result1 = road.findSmallestRange(1);
		int result2 = road.findSmallestRange(2);
		int result3 = road.findSmallestRange(3);

		assertEquals("Smallest Range Test 1 - 1", 8, result1);
		assertEquals("Smallest Range Test 1 - 2", 3, result2);
		assertEquals("Smallest Range Test 1 - 3", 1, result3);
	}

	@Test
	public void smallestRangeTest2() {
		Highway road = new Highway(10, 32);

		road.addCustomer(12);
		road.addCustomer(14);
		road.addCustomer(16);
		road.addCustomer(18);
		road.addCustomer(21);
		road.addCustomer(23);
		road.addCustomer(25);
		road.addCustomer(27);
		road.addCustomer(29);
		road.addCustomer(31);

		int result1 = road.findSmallestRange(3);
		int result2 = road.findSmallestRange(4);

		assertEquals("Smallest Range Test 2 - 1", 3, result1);
		assertEquals("Smallest Range Test 2 - 2", 2, result2);
	}

	@Test
	public void smallestRangeTest3() {
		Highway road = new Highway(10, 110);
		final int numCustomers = 50;

		for (int i = 0; i < numCustomers; i++) {
			road.addCustomer(10 + generator.nextInt(101));
		}

		for (int i = 1; i <= numCustomers; i++) {
			int result = road.findSmallestRange(i);

			assertEquals("Smallest range Test 3", road.numberOfStations(result), i);
		}
	}
}
