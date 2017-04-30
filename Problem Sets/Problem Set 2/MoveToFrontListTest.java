package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Class: MoveToFrontListTest
 * 
 * Description: A set of tests for the MoveToFrontList implementation.
 * 
 * Note: This test framework uses a method toString implemented in the class
 * FixedLengthList. Note: This test framework is based on method 2 of
 * MoveToFrontList's search
 * 
 * @author Niu Yunpeng
 * based on
 * @author Goh Chun Teck
 */
public class MoveToFrontListTest {

	/**
	 * Public Method: getMTFList
	 * 
	 * @param length
	 *            - length of the list to create
	 * 
	 * @return a MoveToFrontList object to test
	 */
	MoveToFrontList getMTFList(int length) {
		return new MoveToFrontList(length);
	}

	/**
	 * Test add method with simple example
	 */
	@Test
	public void testAdd()
	{
		MoveToFrontList testObj = getMTFList(3);
		testObj.add(10);
		assertEquals("AddTest", "10", testObj.toString());
		testObj.add(12);
		assertEquals("AddTest", "10 12", testObj.toString());
	}

	/**
	 * Test search method with an item not in the list
	 */
	@Test
	public void searchTest1()
	{
		MoveToFrontList testList = getMTFList(5);
		testList.add(0);
		testList.add(100);
		testList.add(1000);
		testList.add(13);
		testList.add(4);

		boolean result = testList.search(2500);

		String expected = "0 100 1000 13 4";
		assertEquals("SearchTest1", false, result);
		assertEquals("SearchTest1", expected, testList.toString());
	}

	/**
	 * Test search method with an item in the list
	 */
	@Test
	public void searchTest2()
	{
		MoveToFrontList testList = getMTFList(5);
		testList.add(3);
		testList.add(15);
		testList.add(25);
		testList.add(1000);
		testList.add(1500);

		boolean result = testList.search(1500);
		String expected1 = "1500 3 15 25 1000";
		assertEquals("SearchTest2", true, result);
		assertEquals("SearchTest2", expected1, testList.toString());

		boolean result2 = testList.search(15);
		String expected2 = "15 1500 3 25 1000";
		assertEquals("SearchTest2", true, result2);
		assertEquals("SearchTest2", expected2, testList.toString());

		boolean result3 = testList.search(1500);
		String expected3 = "1500 15 3 25 1000";
		assertEquals("SearchTest3", true, result3);
		assertEquals("SearchTest3", expected3, testList.toString());
	}

	/**
	 * Test errors
	 */
	@Test
	public void errorTest1()
	{
		try {
			MoveToFrontList testList = getMTFList(1);
			testList.add(0);
			testList.add(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(false);
		} catch (LengthExceedException e) {
			// A customized kind of exception - LengthExceedException is thrown.
			assertTrue(true);
		}
	}
}
