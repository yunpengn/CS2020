package sg.edu.nus.cs2020;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchTest {

	@Test
	public void testSearch() {
		int[] testArray = {1, 2, 3, 3, 3};
		int x = 3;
		assertEquals("Basic Search Test", 2, BinarySearch.search(testArray, x));
	}

	@Test
	public void testSearchUnsorted() {
		int[] testArray = {1, 4, 2, 3, 3, 5};
		int x = 3;
		assertEquals("Search Test - Unosrted Array", 2, BinarySearch.search(testArray, x));
	}

	@Test
	public void testSearchNotFound() {
		int[] testArray = { 1, 2, 3, 3, 4, 5 };
		int x = 6;
		assertEquals("Search Test - Not Found", -1, BinarySearch.search(testArray, x));
	}
	
	@Test
	public void testSearchSizeOne() {
		int[] testArray = {1};
		int x = 1;
		assertEquals("Search Test - Size One", 0, BinarySearch.search(testArray, x));
	}

	@Test
	public void testSearchSizeZero() {
		int[] testArray = {};
		int x = 1;
		assertEquals("Search Test - Size Zero", -1, BinarySearch.search(testArray, x));
	}

	@Test
	public void testSearchRange(){
		int[] testArray = {1, 2, 3, 3, 3};
		int x = 3;
		int[] expected = {2, 4};
		assertArrayEquals("Basic Search Range Test", expected, BinarySearch.searchRange(testArray, x));
	}
	
	@Test
	public void testSearchAll(){
		int[] testArray = {3, 3, 3, 3, 3};
		int x = 3;
		int[] expected = {0, 4};
		assertArrayEquals("Search Range Test - All", expected, BinarySearch.searchRange(testArray, x));
	}
	
	@Test
	public void testSearchRangeOnlyOnce() {
		int[] testArray = { 1, 2, 3, 4, 5 };
		int x = 3;
		int[] expected = { 2, 2 };
		assertArrayEquals("Search Range Test - Appear Only Once", expected, BinarySearch.searchRange(testArray, x));
	}

	@Test
	public void testSearchRangeNotFound() {
		int[] testArray = { 1, 2, 3, 3, 5 };
		int x = 7;
		int[] expected = { -1, -1 };
		assertArrayEquals("Search Range Test - Not Found", expected, BinarySearch.searchRange(testArray, x));
	}

	@Test
	public void testSearchRangeAtLast() {
		int[] testArray = { 1, 2, 3, 4, 5 };
		int x = 5;
		int[] expected = { 4, 4 };
		assertArrayEquals("Search Range Test - Last Element", expected, BinarySearch.searchRange(testArray, x));
	}
}
